package online.mizak.rplsupplier;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

@Slf4j
abstract class FileDownloader {

    /**
     * Converts file size in bytes to a human-readable format.
     *
     * @param bytes the size in bytes
     * @return the human-readable file size
     */
    public static String humanReadableFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String unit = "KMGTPE".charAt(exp - 1) + "B";
        return String.format("%.1f %s", bytes / Math.pow(1024, exp), unit);
    }

    /**
     * Downloads a file from the given URL and saves it to a temporary directory.
     *
     * @param fileURL the URL of the file to download
     * @return a File object representing the downloaded file in the temporary directory
     * @throws IOException if an I/O error occurs
     */
    public static File downloadFileToTemp(String fileURL) throws IOException {
        byte[] fileData = downloadFileToMemory(fileURL);
        File tempFile = Files.createTempFile("downloaded_file", null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(fileData);
        }
        log.info("File downloaded successfully to temporary file: " + tempFile.getAbsolutePath());
        return tempFile;
    }

    /**
     * Downloads a file from the given URL and saves it to a specified local path.
     *
     * @param fileURL the URL of the file to download
     * @param savePath the local path where the file will be saved
     * @throws IOException if an I/O error occurs
     */
    public static void downloadFileToPath(String fileURL, String savePath) throws IOException {
        byte[] fileData = downloadFileToMemory(fileURL);
        try (FileOutputStream fos = new FileOutputStream(savePath)) {
            fos.write(fileData);
        }
        log.info("File downloaded successfully to: " + savePath);
    }

    /**
     * Downloads a file from the given URL and returns it as a byte array.
     *
     * @param fileURL the URL of the file to download
     * @return a byte array containing the file's data
     * @throws IOException if an I/O error occurs
     */
    public static byte[] downloadFileToMemory(String fileURL) throws IOException {
        HttpURLConnection httpConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            // Open connection to the URL
            URL url = new URL(fileURL);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Check for a valid HTTP response code
            int responseCode = httpConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to download file. HTTP response code: " + responseCode);
            }

            // Get the input stream from the connection
            inputStream = new BufferedInputStream(httpConnection.getInputStream());

            // Create an output stream to store the file in memory
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Buffer for reading data
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read and write data from input stream to memory
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            log.info("File downloaded successfully to memory.");
            return byteArrayOutputStream.toByteArray();

        } finally {
            // Close all streams and connections
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }

}

