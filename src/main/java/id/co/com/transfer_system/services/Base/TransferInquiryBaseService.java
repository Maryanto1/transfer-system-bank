package id.co.com.transfer_system.services.Base;

import id.co.com.transfer_system.constant.TransferConstant;
import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.BaseResponse;
import id.co.com.transfer_system.dto.TransferInquiryBaseResponseDto;
import id.co.com.transfer_system.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class TransferInquiryBaseService<R,T> {

    protected abstract void checkParamValid(R request) throws ProcessException;

    protected abstract T constructRespon(R request) throws ProcessException;

    protected abstract void storeSession(String name, Object key, Object value, long timeToLive) throws ProcessException;

    public BaseResponse<T> executeInquiryTransfer(R requestBase) throws ProcessException {

        var request = requestBase;
        //validated account
        log.debug("Cek Param Begin");
        checkParamValid((R) request);
        log.debug("Cek Param End");

        log.debug("Construct Respon begin");
//        var constructRespon = constructRespon((R) requestBase);
        TransferInquiryBaseResponseDto constructRespon = (TransferInquiryBaseResponseDto) constructRespon((R) requestBase);
        log.debug("Construct Respon end");

        log.debug("Store to session");
        storeSession(TransferConstant.INQ_SESS, TransferConstant.USER_PROFILE_ID, constructRespon, 100);
        log.debug("Store to session end");

        BaseResponse<T> ret = new BaseResponse<>();
        ret.setCode("00");
        ret.setMessage("Success");
        ret.setResult((T) constructRespon);
        return ret;
    }

}
