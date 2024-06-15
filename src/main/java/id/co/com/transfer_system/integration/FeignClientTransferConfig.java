package id.co.com.transfer_system.integration;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class FeignClientTransferConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {


        return requestTemplate -> {
            String endpointUrl = requestTemplate.url();
            String httpMethod = requestTemplate.method();
            String requestBody= new String(requestTemplate.requestBody().asBytes());

            log.debug("request intercept \n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n", endpointUrl, httpMethod, requestBody);

            try {
                requestTemplate.header("X-SIGNATURE", "Signature");
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}
