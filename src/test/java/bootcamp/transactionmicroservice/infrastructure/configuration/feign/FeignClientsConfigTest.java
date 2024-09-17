package bootcamp.transactionmicroservice.infrastructure.configuration.feign;

import bootcamp.transactionmicroservice.domain.until.JwtConst;
import bootcamp.transactionmicroservice.domain.until.TokenHolder;
import bootcamp.transactionmicroservice.infrastructure.exceptionhandler.FeignExceptionHandler;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeignClientsConfigTest {

    @Mock
    private FeignClientsConfig feignClientsConfig;

    @Mock
    private RequestTemplate requestTemplate = new RequestTemplate();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feignClientsConfig = new FeignClientsConfig();
        feignClientsConfig.requestInterceptor().apply(requestTemplate);
    }

    @Test
    void requestInterceptor_shouldNotAddAuthorizationHeader_whenTokenIsNull() {
        TokenHolder.setToken(null);
        assertFalse(requestTemplate.headers().containsKey(JwtConst.AUTHORIZATION));
    }

    @Test
    void feignErrorDecoder_shouldReturnFeignExceptionHandler() {
        ErrorDecoder errorDecoder = feignClientsConfig.feignErrorDecoder();
        assertTrue(errorDecoder instanceof FeignExceptionHandler);

    }
    @Test
    void requestInterceptor_shouldAddAuthorizationHeader_whenTokenIsPresent() {
        TokenHolder.setToken("validToken");
        when(requestTemplate.headers()).thenReturn(new HashMap<>());
        feignClientsConfig.requestInterceptor().apply(requestTemplate);
        verify(requestTemplate).header(JwtConst.AUTHORIZATION, "validToken");
    }


    @Test
    void feignErrorDecoder_shouldReturnNonNullErrorDecoder() {
        ErrorDecoder errorDecoder = feignClientsConfig.feignErrorDecoder();
        assertNotNull(errorDecoder);
    }
}