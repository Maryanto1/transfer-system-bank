package id.co.com.transfer_system.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
public class PaymentTransferReqDto implements Serializable {

    private static final long serialVersionUID = -8786724277712679736L;

    private String amount;
    private Date transTime;
    private String channelId;
    private String locationName;
    private String sourceAccount;
    private String destinationAccount;
    private String description;
}
