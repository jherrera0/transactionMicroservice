package bootcamp.transactionmicroservice.infrastructure.exceptionhandler;

import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeignExceptionHandlerTest {

    private FeignExceptionHandler feignExceptionHandler;
    private Response response;
    private Response.Body responseBody;
    private Request request;
    @Mock
    private ErrorDecoder defaultErrorDecoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feignExceptionHandler = new FeignExceptionHandler();
        response = mock(Response.class);
        responseBody = mock(Response.Body.class);
        request = mock(Request.class);
    }

    @Test
    void decode_shouldReturnBadRequestException_whenStatusIs400() throws Exception {
        when(response.status()).thenReturn(400);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Bad Request".getBytes(StandardCharsets.UTF_8)));
        when(response.request()).thenReturn(request);

        Exception exception = feignExceptionHandler.decode("methodKey", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.BAD_REQUEST, ((ResponseStatusException) exception).getStatusCode());
        assertEquals("Bad Request", ((ResponseStatusException) exception).getReason());
    }

    @Test
    void decode_shouldReturnNotFoundException_whenStatusIs404() throws Exception {
        when(response.status()).thenReturn(404);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Not Found".getBytes(StandardCharsets.UTF_8)));
        when(response.request()).thenReturn(request);

        Exception exception = feignExceptionHandler.decode("methodKey", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.NOT_FOUND, ((ResponseStatusException) exception).getStatusCode());
        assertEquals("Not Found", ((ResponseStatusException) exception).getReason());
    }

    @Test
    void decode_shouldReturnInternalServerErrorException_whenStatusIs500() throws Exception {
        when(response.status()).thenReturn(500);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Internal Server Error".getBytes(StandardCharsets.UTF_8)));
        when(response.request()).thenReturn(request);

        Exception exception = feignExceptionHandler.decode("methodKey", response);

        assertTrue(exception instanceof ResponseStatusException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ((ResponseStatusException) exception).getStatusCode());
        assertEquals("Internal Server Error", ((ResponseStatusException) exception).getReason());
    }

    @Test
    void decode_shouldReturnDefaultException_whenStatusIsOther() throws Exception {
        when(response.status()).thenReturn(403);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Forbidden".getBytes(StandardCharsets.UTF_8)));
        when(response.request()).thenReturn(request);

        Exception exception = feignExceptionHandler.decode("methodKey", response);

        assertFalse(exception instanceof ResponseStatusException);
    }

    @Test
    void getErrorMessage_shouldReturnBodyContent_whenBodyIsNotNull() throws Exception {
        when(response.body()).thenReturn(responseBody);
        when(responseBody.asInputStream()).thenReturn(new ByteArrayInputStream("Error Message".getBytes(StandardCharsets.UTF_8)));

        String errorMessage = feignExceptionHandler.getErrorMessage(response);

        assertEquals("Error Message", errorMessage);
    }


}