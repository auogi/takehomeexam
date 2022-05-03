package takehomeexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import takehomeexam.model.ExceptionMessage;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ExceptionMessage("REQUIRED_FIELD_VALIDATION", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(CustomDBException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleValidationExceptions(CustomDBException ex) {
        return new ExceptionMessage("DATABASE_VALIDATION", ex.getMessage());
    }
}
