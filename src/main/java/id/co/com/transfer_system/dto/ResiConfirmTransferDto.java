package id.co.com.transfer_system.dto;

import lombok.Builder;

import java.util.ArrayList;

@Builder
public class ResiConfirmTransferDto {
    private String nominal;
    private String beneficiaryName;
    private String beneficiaryAcctNumber;
    private String beneficiaryBankCode;
    private String beneficiaryBankIcon;
    private Boolean fromAccountName;
    private ArrayList<Object> transferDetail;
}
