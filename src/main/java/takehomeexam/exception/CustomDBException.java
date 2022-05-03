package takehomeexam.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomDBException extends RuntimeException {
    @Getter
    @Setter
    private String message;

    public CustomDBException(String message){
        super();
        this.message = message;
    }
}
