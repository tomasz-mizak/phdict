package online.mizak.phdict.dictionary.exception;

public class PhDictException extends RuntimeException {

    private final Enum<?> code;

    public PhDictException(String message, Enum<?> code) {
        super(message);
        this.code = code;
    }

    public Enum<?> getCode() {
        return code;
    }

    public String getCodeName() {
        return code.name();
    }

}
