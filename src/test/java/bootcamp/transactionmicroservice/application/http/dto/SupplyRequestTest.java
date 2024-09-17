package bootcamp.transactionmicroservice.application.http.dto;

import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SupplyRequestTest {

    private final Validator validator;

    public SupplyRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validSupplyRequest() {
        SupplyRequest request = new SupplyRequest(1L, 10L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void productIdIsNull() {
        SupplyRequest request = new SupplyRequest(null, 10L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void quantityIsNull() {
        SupplyRequest request = new SupplyRequest(1L, null);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void productIdIsNegative() {
        SupplyRequest request = new SupplyRequest(-1L, 10L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void quantityIsNegative() {
        SupplyRequest request = new SupplyRequest(1L, -10L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void productIdIsZero() {
        SupplyRequest request = new SupplyRequest(0L, 10L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void quantityIsZero() {
        SupplyRequest request = new SupplyRequest(1L, 0L);
        Set<ConstraintViolation<SupplyRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
}