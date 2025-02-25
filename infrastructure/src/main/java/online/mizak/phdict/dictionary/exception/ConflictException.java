package online.mizak.phdict.dictionary.exception;

public class ConflictException extends PhDictException {
    public ConflictException(String message, Enum<?> code) {
        super(message, code);
    }

    public ConflictException(String message) {
        super(message);
    }
}
