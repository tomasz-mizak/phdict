package online.mizak.phdict.dictionary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleEntityNotFound(NotFoundException ex) {
        return new ErrorDetails(ex.getMessage(), ex.getCodeName());
    }

    @ExceptionHandler(NotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorDetails handleIllegalArgument(NotAcceptableException ex) {
        return new ErrorDetails(ex.getMessage(), ex.getCodeName());
    }

}
