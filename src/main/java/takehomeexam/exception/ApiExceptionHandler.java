package takehomeexam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import takehomeexam.model.ApiError;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleValidationError(SQLIntegrityConstraintViolationException ex){
        String defaultMessage = ex.getLocalizedMessage();
        return new ApiError("VALIDATION_FAILED", defaultMessage);
    }
}
