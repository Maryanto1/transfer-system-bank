package id.co.com.transfer_system.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
public class PaymentTransferResDto implements Serializable {
    private static final long serialVersionUID = 2221512887557778928L;

    private String amount;
    private String transmissionDateTime;
    private String stan;
    private String retrievalNumber;
    private String clientTerminalId;
    private String sourceAccount;
    private String destinationAccount;
    private String responseDescription;
    private String destinationName;
    private String responseCode;
    private String privateReservedCore;

    public boolean isSuspect(){
        return "68".equals(responseCode);
    }

    public boolean isSuccess(){
        return "00".equals(responseCode);
    }

}

