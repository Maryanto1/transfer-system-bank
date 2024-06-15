package id.co.com.transfer_system.controller;

import id.co.com.transfer_system.dto.BaseResponse;
import id.co.com.transfer_system.dto.TransferInquiryBaseRequestDto;
import id.co.com.transfer_system.dto.TransferInquiryBaseResponseDto;
import id.co.com.transfer_system.services.TransferInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferInquiryService transferInquiryService;

    @PostMapping("/V2/transfer/inquiry")
    @ResponseBody
    public BaseResponse<TransferInquiryBaseResponseDto> doInquiry(@RequestBody TransferInquiryBaseRequestDto param){
        return transferInquiryService.executeInquiryTransfer(param);
    }
}
