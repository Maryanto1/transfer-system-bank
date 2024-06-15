package id.co.com.transfer_system.core.config;

import id.co.com.transfer_system.core.filter.HttpRequestWrapper;
import id.co.com.transfer_system.core.filter.HttpResponseWrapper;
import id.co.com.transfer_system.util.ISTHttpUtil;
import jakarta.servlet.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CryptoFilter implements Filter {

    @Value("${is.encrypt:null}")
    String masterKey;

    private final String[] EXLUDE_CRYPTO = {"/webjars", "/v2/api-docs", "/qr/issuer/refund", "/qr/issuer/refundOnUs", "/actuator", "/swagger-resources", "/swagger-ui.html" , "/cardless/offus/flaging", "/cardless/offus/V2/flaging", "/cardRequestOnBranch/updateStatus", "/retry/userOpenAccount","/test2", "/V2/transfer/notification","/ayoconnect/dkilink/inquiry/callback","/ayoconnect/dkilink/payment/callback","/generate/username/noUserAuth","/reload","/qrcpts/dkilink/validation/ott","/qrcpts/dkilink/refund","/omniChannel/dkilink/inquiryCallback","/qrcpm/dkilink/payment","/qrcpm/dkilink/status","/qrcpm/dkilink/refund","/qrcpm/dkilink/reversal"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Logger log = LoggerFactory.getLogger(CryptoFilter.class);
        HttpRequestWrapper requestX = ISTHttpUtil.wrapRequest(servletRequest);
        HttpResponseWrapper responseX = ISTHttpUtil.wrapResponse(servletResponse);
        String path = requestX.getRequestURI();
        log.debug("Path : "+path);
        for (String excludeLog : EXLUDE_CRYPTO) {
            if (StringUtils.contains(path, excludeLog)) {
                filterChain.doFilter(requestX, responseX);
                responseX.flush();
                return;
            }
        }
        String chiperResp = "";
        final String chiper = requestX.getBodyAsString();
        try {
            if (StringUtils.isEmpty(masterKey)||"no".equalsIgnoreCase(masterKey)) {
                //no encrypt
                filterChain.doFilter(requestX, responseX);
                responseX.flush();
                return;
            }

            log.debug("Request Encrypt :" + chiper);

            String requestPlain = decryptBody(chiper, masterKey);
            requestX.setBody(requestPlain);

            filterChain.doFilter(requestX, responseX);

            String clearResp = responseX.getBodyAsString();
            try {
                chiperResp = encryptBody(clearResp, masterKey);
            } catch (Exception e) {
                e.printStackTrace();
                responseX.setStatus(HttpStatus.UNAUTHORIZED.value());
                responseX.flush();
            }

            responseX.reset();
            log.debug("Response Encrypt :" + chiperResp);
            responseX.setBody(chiperResp);
            responseX.flush();
        } catch (Exception e) {
            log.info("Invalid request body encrypt");
            responseX.setStatus(HttpStatus.UNAUTHORIZED.value());
            responseX.flush();
        }
    }

    private static String decryptBody(String cipherValue, String masterKey){
        String decodeCipher = new String(Base64Utils.decodeFromString(cipherValue));
        String cipherKey = StringUtils.substring(decodeCipher, decodeCipher.length() - 44);
        String key = AESCrypto.decrypt(cipherKey, masterKey);

        String cipherBody = StringUtils.substring(decodeCipher, 0, decodeCipher.length() - 44);
        return AESCrypto.decrypt(cipherBody, key);
    }

    private static String encryptBody(String plainValue, String masterKey){
        String key = RandomStringUtils.randomAlphanumeric(16);
        String cipher =  AESCrypto.encrypt(plainValue, key);
        String keyCipher = AESCrypto.encrypt(key, masterKey);
        return Base64Utils.encodeToString((cipher+keyCipher).getBytes(StandardCharsets.UTF_8));
    }

}
