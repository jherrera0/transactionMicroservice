package bootcamp.transactionmicroservice.infrastructure.exceptionhandler;

import bootcamp.transactionmicroservice.domain.exceptions.SupplyNotFoundException;
import bootcamp.transactionmicroservice.domain.exceptions.SupplyValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleSupplyNotFoundException() {
        SupplyNotFoundException exception = new SupplyNotFoundException("Supply not found");
        String response = globalExceptionHandler.handleSupplyNotFoundException(exception);
        assertEquals("Supply not found", response);
    }

    @Test
    void handleSupplyValueException() {
        SupplyValueException exception = new SupplyValueException("Invalid supply value");
        String response = globalExceptionHandler.handleSupplyValueException(exception);
        assertEquals("Invalid supply value", response);
    }

    @Test
    void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        Map<String, Object> errors = new HashMap<>();
        errors.put("field1", "error1");
        errors.put("field2", "error2");

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("objectName", "field1", "error1"),
                new FieldError("objectName", "field2", "error2")
        ));

        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(exception, headers, status, request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, response.getBody());
    }
}