package takehomeexam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionMessage {
    @Getter
    @Setter
    private String errorCode;

    @Getter
    @Setter
    private String message;

    public ExceptionMessage(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
