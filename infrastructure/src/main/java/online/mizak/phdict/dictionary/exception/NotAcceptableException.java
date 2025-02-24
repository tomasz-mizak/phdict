package online.mizak.phdict.dictionary.exception;

public class NotAcceptableException extends PhDictException {
    public NotAcceptableException(String message, Enum<?> code) {
        super(message, code);
    }
}
