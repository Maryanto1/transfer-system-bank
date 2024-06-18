package id.co.com.transfer_system.services;

import id.co.com.transfer_system.constant.TransferConstant;
import id.co.com.transfer_system.core.exception.InvalidSessionException;
import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.*;
import id.co.com.transfer_system.integration.TransferIntegration;
import id.co.com.transfer_system.services.Base.TransferPostingBaseService;
import id.co.com.transfer_system.util.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransferPostService extends TransferPostingBaseService<TransferPostBaseRequestDto, TransferPostBaseResponseDto> {

    @Value("${transfer.mode.dev:true}")
    protected Boolean devMode;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private TransferIntegration transferIntegration;

    @Override
    protected void checkParamValid(TransferPostBaseRequestDto request) throws ProcessException {
        if (StringUtils.isBlank(request.getMpin())) {
            throw new ProcessException(TransferConstant.PARAM_INVALID_CODE, TransferConstant.PARAM_INVALID_DESC);
        }
    }

    @Override
    protected void checkSession() throws ProcessException {
        TransferInquiryBaseResponseDto ses1 = (TransferInquiryBaseResponseDto) cacheUtil.getCache(TransferConstant.INQ_SESS, TransferConstant.USER_PROFILE_ID);
        if (ses1 == null) {
            throw new InvalidSessionException(TransferConstant.INVALID_SESSION, TransferConstant.INVALID_SESSION_DESC);
        }
    }

    @Override
    protected void checkMpin() throws ProcessException {

    }

    @Override
    protected void doPayment() throws ProcessException {
        TransferInquiryBaseResponseDto ses1 = (TransferInquiryBaseResponseDto) cacheUtil.getCache(TransferConstant.INQ_SESS, TransferConstant.USER_PROFILE_ID);
        PaymentTransferResDto resTransfer;
        if (devMode){



        }else {
            resTransfer = transferIntegration.doPayment(PaymentTransferReqDto
                    .builder()
                    .amount(String.valueOf(ses1.getResi().getNominal()))
                    .channelId(TransferConstant.CHANNEL_ID)
                    .transTime(new Date())
                    .destinationAccount(ses1.getResi().getDestinationAccount())
                    .sourceAccount(ses1.getResi().getBeneficiaryAcctNumber())
                    .description(ses1.getResi().getNotes())
                    .locationName(TransferConstant.LOCATION_NAME)
                    .build());
        }



    }

    @Override
    protected TransferPostBaseResponseDto constructRespon(TransferPostBaseRequestDto request) throws ProcessException {
        return null;
    }
}
