package online.mizak.phdict.dictionary.exception;

public class PhDictException extends RuntimeException {

    enum DefaultCode {
        UNKNOWN
    }

    private final Enum<?> code;

    public PhDictException(String message, Enum<?> code) {
        super(message);
        this.code = code;
    }

    public PhDictException(String message) {
        super(message);
        this.code = DefaultCode.UNKNOWN;
    }

    public Enum<?> getCode() {
        return code;
    }

    public String getCodeName() {
        return code.name();
    }

}
