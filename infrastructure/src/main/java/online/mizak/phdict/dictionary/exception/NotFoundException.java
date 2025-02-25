package online.mizak.phdict.dictionary.exception;

public class NotFoundException extends PhDictException {
    public NotFoundException(String message, Enum<?> code) {
        super(message, code);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
