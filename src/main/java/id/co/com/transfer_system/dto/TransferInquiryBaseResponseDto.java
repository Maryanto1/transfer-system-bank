package id.co.com.transfer_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferInquiryBaseResponseDto {
    private ResiConfirmTransferDto resi;
}
