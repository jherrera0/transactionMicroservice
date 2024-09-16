package bootcamp.transactionMicroservice.infrastructure.configuration.feign;

import bootcamp.transactionMicroservice.domain.until.JwtConst;
import bootcamp.transactionMicroservice.domain.until.TokenHolder;
import bootcamp.transactionMicroservice.infrastructure.exceptionhandler.FeignExceptionHandler;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header(JwtConst.AUTHORIZATION, token);
            }
        };
    }

    @Bean
    public ErrorDecoder feignErrorDecoder() {
        return new FeignExceptionHandler();
    }

}
