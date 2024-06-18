package id.co.com.transfer_system.controller;

import id.co.com.transfer_system.dto.*;
import id.co.com.transfer_system.services.TransferInquiryService;
import id.co.com.transfer_system.services.TransferPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferInquiryService transferInquiryService;

    @Autowired
    private TransferPostService transferPostService;

    @PostMapping("/inquiry")
    @ResponseBody
    public BaseResponse<TransferInquiryBaseResponseDto> doInquiry(@RequestBody TransferInquiryBaseRequestDto param){
        return transferInquiryService.executeInquiryTransfer(param);
    }

    @PostMapping("/posting")
    @ResponseBody
    public BaseResponse<TransferPostBaseResponseDto> doInquiry(@RequestBody TransferPostBaseRequestDto param){
        return transferPostService.executePosting(param);
    }

}
