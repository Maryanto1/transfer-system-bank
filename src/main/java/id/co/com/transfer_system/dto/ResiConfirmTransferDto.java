package id.co.com.transfer_system.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResiConfirmTransferDto implements Serializable {

    private static final long serialVersionUID = -1786724277712679736L;

    private String nominalStr;
    private Long nominal;
    private String beneficiaryAcctNumber;
    private String destinationAccount;
    private String beneficiaryBankCode;
    private String fromAccountName;
    private String notes;
    private ArrayList<Object> transferDetail;
}
