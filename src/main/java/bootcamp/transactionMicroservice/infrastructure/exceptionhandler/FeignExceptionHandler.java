package bootcamp.transactionMicroservice.infrastructure.exceptionhandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class FeignExceptionHandler implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();
    @Override
    public Exception decode(String s, Response response) {
        String errorMessage = getErrorMessage(response);
        switch (response.status()) {
            case 400:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage);
            case 404:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
            case 500:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
            default:
                return defaultErrorDecoder.decode(s, response);
        }
    }

    private String getErrorMessage(Response response) {
        try {
            if (response.body() != null) {
                return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            return getErrorMessage(response);
        }
        return Optional.ofNullable(response.reason()).orElse(getErrorMessage(response));
    }
}