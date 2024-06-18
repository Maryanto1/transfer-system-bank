package id.co.com.transfer_system.core.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class InvalidSessionException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 2221512887557038928L;

    private String code;
    private String message;

    public InvalidSessionException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
