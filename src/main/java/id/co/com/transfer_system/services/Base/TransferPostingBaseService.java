package id.co.com.transfer_system.services.Base;

import id.co.com.transfer_system.core.exception.ProcessException;
import id.co.com.transfer_system.dto.BaseResponse;
import id.co.com.transfer_system.dto.PaymentTransferResDto;
import id.co.com.transfer_system.dto.TransferPostBaseResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TransferPostingBaseService<R,T> {

    protected abstract void checkParamValid(R request) throws ProcessException;

    protected abstract void checkSession()throws ProcessException;

    protected abstract void checkMpin() throws ProcessException;

    protected abstract PaymentTransferResDto doPayment()throws ProcessException;

    protected abstract void   saveTranhisInbox(PaymentTransferResDto resPayment) throws ProcessException;

    protected abstract T constructRespon(PaymentTransferResDto request) throws ProcessException;

    public BaseResponse<T> executePosting(R requestBase) throws ProcessException {

        var request = requestBase;
        //validated account
        log.debug("Cek Param Begin");
        checkParamValid((R) request);
        log.debug("Cek Param End");

        log.debug("Cek Session");
        checkSession();
        log.debug("Cek Session End");

        log.debug("Cek Mpin");
        checkMpin();
        log.debug("Cek Mpin End");

        log.debug("Do Payment");
        PaymentTransferResDto resPayment =  doPayment();
        log.debug("Do Payment End");

        log.debug("Construct Respon begin");
        var constructRespon = constructRespon(resPayment);
        log.debug("Construct Respon end");

        //store to session
        BaseResponse<T> ret = new BaseResponse<>();
        ret.setCode("00");
        ret.setMessage("Success");
        ret.setResult(constructRespon);
        return ret;
    }

}
