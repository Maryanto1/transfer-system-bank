package id.co.com.transfer_system.services;

import id.co.com.transfer_system.constant.TransferConstant;
import id.co.com.transfer_system.core.exception.InvalidSessionException;
import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.domain.entity.Inbox;
import id.co.com.transfer_system.domain.entity.TransactionHistory;
import id.co.com.transfer_system.domain.repository.InboxRepository;
import id.co.com.transfer_system.domain.repository.TransactionHistoryRepository;
import id.co.com.transfer_system.dto.*;
import id.co.com.transfer_system.integration.TransferIntegration;
import id.co.com.transfer_system.services.Base.TransferPostingBaseService;
import id.co.com.transfer_system.util.CacheUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransferPostService extends TransferPostingBaseService<TransferPostBaseRequestDto, TransferPostBaseResponseDto> {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private InboxRepository inboxRepository;

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
        // TODO: 18/06/24 validate mpin
        // business flow if wrong mpin, etc
    }

    @Override
    protected PaymentTransferResDto doPayment() throws ProcessException {
        TransferInquiryBaseResponseDto ses1 = (TransferInquiryBaseResponseDto) cacheUtil.getCache(TransferConstant.INQ_SESS, TransferConstant.USER_PROFILE_ID);
        PaymentTransferResDto resTransfer = null;
        if (devMode){

            resTransfer = PaymentTransferResDto
                    .builder()
                    .amount(String.valueOf(ses1.getResi().getNominal()))
                    .transmissionDateTime("2024-06-18 22:22:22")
                    .stan("1erf459yuhk")
                    .retrievalNumber("12498hkjffk")
                    .clientTerminalId("MB-02")
                    .sourceAccount(ses1.getResi().getFromAccountName())
                    .destinationAccount("123498765")
                    .responseDescription(ses1.getResi().getNotes())
                    .responseCode("00")
                    .privateReservedCore("0099554443321")
                    .destinationName("Mr. Y")
                    .build();

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
        return resTransfer;


    }

    @Override
    @Transactional
    protected void saveTranhisInbox(PaymentTransferResDto resPayment) throws ProcessException {
        TransactionHistory tranHis = new TransactionHistory();
        // TODO: 19/06/24 construct tranhis
        transactionHistoryRepository.save(tranHis);

        Inbox inbox = new Inbox();
        // TODO: 19/06/24 construct inbox
        inboxRepository.save(inbox);
    }

    @Override
    protected TransferPostBaseResponseDto constructRespon(PaymentTransferResDto request) throws ProcessException {

        return null;

    }

}
