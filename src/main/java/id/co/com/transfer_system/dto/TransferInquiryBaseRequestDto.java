package id.co.com.transfer_system.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TransferInquiryBaseRequestDto implements Serializable {
    private static final long serialVersionUID = -1786724238012679736L;

    private String destinationAccountNumber;
    private String destinationBankCode;
    private String destinationBankName;
    private String destinationBankType;
    private String sourceAccountNumber;
    private String biFastCode;
    private Long amount;
    private String transferType;
    private Boolean isProxy = false;
}
