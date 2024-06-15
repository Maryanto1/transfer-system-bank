package id.co.com.transfer_system.integration;

import id.co.com.transfer_system.dto.PaymentTransferReqDto;
import id.co.com.transfer_system.dto.PaymentTransferResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "transferIntegration", url = "${url.tranfer.domain}", configuration = FeignClientTransferConfig.class)
public interface TransferIntegration {

    @PostMapping(value = "/posting",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    PaymentTransferResDto jurnalCasa(@RequestBody PaymentTransferReqDto req);

}
