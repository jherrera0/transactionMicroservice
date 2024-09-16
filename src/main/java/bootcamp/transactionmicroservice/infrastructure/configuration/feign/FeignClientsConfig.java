package bootcamp.transactionmicroservice.infrastructure.configuration.feign;

import bootcamp.transactionmicroservice.domain.until.JwtConst;
import bootcamp.transactionmicroservice.domain.until.TokenHolder;
import bootcamp.transactionmicroservice.infrastructure.exceptionhandler.FeignExceptionHandler;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfig {

    @Bean
    public static RequestInterceptor requestInterceptor() {
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
