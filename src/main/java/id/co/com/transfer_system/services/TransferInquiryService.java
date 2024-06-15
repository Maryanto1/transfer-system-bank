package id.co.com.transfer_system.services;

import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.TransferInquiryBaseRequestDto;
import id.co.com.transfer_system.dto.TransferInquiryBaseResponseDto;
import id.co.com.transfer_system.services.Base.TransferInquiryBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferInquiryService extends TransferInquiryBaseService<TransferInquiryBaseRequestDto, TransferInquiryBaseResponseDto> {
    @Override
    protected void checkParamValid(TransferInquiryBaseRequestDto request) throws ProcessException {

    }

    @Override
    protected TransferInquiryBaseResponseDto constructRespon(TransferInquiryBaseRequestDto request) throws ProcessException {
        return null;
    }
}
