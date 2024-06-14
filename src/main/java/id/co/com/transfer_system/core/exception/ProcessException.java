package id.co.com.transfer_system.core.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ProcessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 8951512887557038928L;

    private String code;
    private String message;

    ProcessException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
