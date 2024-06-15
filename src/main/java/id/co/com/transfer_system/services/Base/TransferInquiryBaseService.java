package id.co.com.transfer_system.services.Base;

import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TransferInquiryBaseService<R,T> {

    protected abstract void checkParamValid(R request) throws ProcessException;

    protected abstract T constructRespon(R request) throws ProcessException;

    public BaseResponse<T> executeInquiryTransfer(R requestBase) throws ProcessException {

        var request = requestBase;
        //validated account
        log.debug("Cek Param Begin");
        checkParamValid((R) request);
        log.debug("Cek Param End");

        log.debug("Construct Respon begin");
        var constructRespon = constructRespon((R) requestBase);
        log.debug("Construct Respon end");

        //store to session

        BaseResponse<T> ret = new BaseResponse<>();
        ret.setCode("00");
        ret.setMessage("Success");
        ret.setResult(constructRespon);
        return ret;
    }

}
