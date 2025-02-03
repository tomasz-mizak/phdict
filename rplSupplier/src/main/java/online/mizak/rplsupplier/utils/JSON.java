package online.mizak.rplsupplier.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Utility class for serializing objects into formatted JSON.
 * <p>
 * Uses the Jackson library to convert objects into indented JSON format.
 * Supports date and time classes from {@code java.time}.
 * </p>
 *
 * <h3>Usage examples:</h3>
 * <pre>
 *     MyObject obj = new MyObject("Toma", 30);
 *
 *     // Print JSON to the console
 *     JSON.pretty(obj);
 *
 *     // Get JSON as a String
 *     String jsonString = JSON.toString(obj);
 * </pre>
 *
 * <p>This class is abstract because it only contains static methods.</p>
 *
 * @author Toma
 */
public abstract class JSON {

    /**
     * Private instance of {@link ObjectMapper} for object serialization.
     * <p>
     * Configuration:
     * <ul>
     *     <li>Registers the {@code JavaTimeModule} (support for {@code java.time})</li>
     *     <li>Disables serialization of dates as timestamps</li>
     *     <li>Enables formatted (pretty-printed) JSON output</li>
     * </ul>
     * </p>
     */
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private JSON() {}

    /**
     * Converts the given object into a formatted JSON {@code String}.
     * <p>
     * The returned JSON string is indented for better readability.
     * </p>
     *
     * @param object the object to be serialized
     * @return a formatted JSON string
     */
    public static String stringify(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.err.printf("Cannot serialize object to JSON.\n%s", e.getMessage());
        }
        return null;
    }

}
