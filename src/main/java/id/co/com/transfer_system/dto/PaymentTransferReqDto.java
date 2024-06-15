package id.co.com.transfer_system.dto;

import lombok.Data;

@Data
public class PaymentTransferReqDto {
    private String processingCode;
    private String pan;
    private String amount;
    private String transmissionDateTime;
    private String stan;
    private String dateTime;
    private String transTime;
    private String channelId;
    private String acqId;
    private String fwdId;
    private String retrievalNumber;
    private String clientTerminalId;
    private String caId;
    private String locationName;
    private String productCode;
    private String currentYear;
    private String sourceAccount;
    private String destinationAccount;
    private String descriptionJurnal;
}
