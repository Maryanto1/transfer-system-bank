package id.co.com.transfer_system.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferInquiryBaseResponseDto implements Serializable {
    private static final long serialVersionUID = -1788824277712679736L;

    private ResiConfirmTransferDto resi;
}
