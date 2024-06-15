package id.co.com.transfer_system.core.exception;

import feign.FeignException;
import id.co.com.transfer_system.dto.BaseDto;
import id.co.com.transfer_system.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProcessException.class)
    public ResponseEntity<? extends BaseResponse> commonException(ProcessException e){
        log.info("Exception is, message : {}", e.getMessage());
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;

        ResponseEntity<?> responseEntity = new ResponseEntity<>(BaseResponse
                .builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build(), httpStatus);
        return (ResponseEntity<? extends BaseResponse>) responseEntity;

    }

    @ExceptionHandler(value = {FeignException.class})
    public ResponseEntity<? extends BaseResponse> feignException(FeignException e) {

        log.info("Exception is feignException, message : {}", e.getMessage());

        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;

        ResponseEntity<?> responseEntity = new ResponseEntity<>(BaseResponse
                .builder()
                .code("444")
                .message(e.getMessage())
                .build(), httpStatus);
        return (ResponseEntity<? extends BaseResponse>) responseEntity;
    }

}
