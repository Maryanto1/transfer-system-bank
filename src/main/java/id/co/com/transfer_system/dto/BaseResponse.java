package id.co.com.transfer_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> extends BaseDto implements Serializable {
    private static final long serialVersionUID = 5429074432850885171L;
    private T result;

}
