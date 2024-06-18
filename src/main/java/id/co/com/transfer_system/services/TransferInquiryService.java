package id.co.com.transfer_system.services;

import id.co.com.transfer_system.constant.TransferConstant;
import id.co.com.transfer_system.constant.TransferStatusEnum;
import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.ResiConfirmTransferDto;
import id.co.com.transfer_system.dto.TransferInquiryBaseRequestDto;
import id.co.com.transfer_system.dto.TransferInquiryBaseResponseDto;
import id.co.com.transfer_system.services.Base.TransferInquiryBaseService;
import id.co.com.transfer_system.util.AmountUtil;
import id.co.com.transfer_system.util.CacheUtil;
import id.co.com.transfer_system.util.MaskingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TransferInquiryService extends TransferInquiryBaseService<TransferInquiryBaseRequestDto, TransferInquiryBaseResponseDto> {

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    protected void checkParamValid(TransferInquiryBaseRequestDto request) throws ProcessException {
        if (StringUtils.isBlank(request.getDestinationAccountNumber()) ||
        StringUtils.isBlank(request.getDestinationBankCode()) ||
        StringUtils.isBlank(request.getDestinationBankName()) ||
        StringUtils.isBlank(request.getSourceAccountNumber()) ||
        StringUtils.isBlank(request.getBiFastCode()) ||
        StringUtils.isBlank(request.getAmount().toString()) ||
        StringUtils.isBlank(request.getTransferType())) {
            throw new ProcessException(TransferConstant.PARAM_INVALID_CODE, TransferConstant.PARAM_INVALID_DESC);
        }

        if (request.getAmount() <= TransferConstant.MINIMAL_AMOUNT_TRANSFER){
            throw new ProcessException(TransferConstant.INVALID_MINIMAL_AMOUNT_TRANSFER, TransferConstant.INVALID_MINIMAL_AMOUNT_DESC);
        }
    }

    @Override
    protected TransferInquiryBaseResponseDto constructRespon(TransferInquiryBaseRequestDto request) throws ProcessException {

        var nominal = AmountUtil.formatAmountWithCurrency(request.getAmount(), AmountUtil.COMMA_DELIMITER_WITHOUT_DECIMAL, "IDR");
        ArrayList<Object> resi = new ArrayList<>();
        Map<String, String> mapResi = new HashMap<>();
        mapResi.put("Nama Pengirim", "Mr. X");
        mapResi.put("Sumber Dana", MaskingUtil.maskAccount(request.getSourceAccountNumber()));
        mapResi.put("Metode Transfer", "Online");
        mapResi.put("Status", TransferStatusEnum.SUCCESS.getStatus());
        mapResi.put("Nominal", nominal);
        mapResi.put("Biaya Admin", AmountUtil.formatAmountWithCurrency(TransferConstant.FEE_TRANSFER, AmountUtil.COMMA_DELIMITER_WITHOUT_DECIMAL, "IDR"));
        mapResi.put("Total",AmountUtil.formatAmountWithCurrency(TransferConstant.FEE_TRANSFER + request.getAmount(), AmountUtil.COMMA_DELIMITER_WITHOUT_DECIMAL, "IDR") );
        resi.add(mapResi);

        ResiConfirmTransferDto inqSes =  ResiConfirmTransferDto
                .builder()
                .nominalStr(nominal)
                .nominal(request.getAmount())
                .destinationAccount(request.getDestinationAccountNumber())
                .beneficiaryAcctNumber(request.getSourceAccountNumber())
                .beneficiaryBankCode(request.getDestinationBankCode())
                .notes(request.getNotes())
                .fromAccountName("Mr. X")
                .transferDetail(resi)
                .build();

        return TransferInquiryBaseResponseDto
                .builder()
                .resi(inqSes)
                .build();
    }

    @Override
    protected void storeSession(String name, Object key, Object value, long time) throws ProcessException {
        cacheUtil.putCache(name, key, value, time);
    }
}
