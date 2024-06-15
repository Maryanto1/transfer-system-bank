package id.co.com.transfer_system.dto;

import lombok.Data;

@Data
public class PaymentTransferResDto {
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
    private String responseDescription;
    private String responseCode;
    private String privateReservedCore;

    public boolean isSuspect(){
        return "68".equals(responseCode);
    }

    public boolean isSuccess(){
        return "00".equals(responseCode);
    }
}
