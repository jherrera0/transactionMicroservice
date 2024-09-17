package bootcamp.transactionmicroservice.infrastructure.controller;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.application.http.handler.TransactionHandler;
import bootcamp.transactionmicroservice.domain.until.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doThrow;

class TransactionalRestControllerTest {
    @Mock
    private  TransactionHandler transactionHandler;

    @Mock
    private TransactionalRestController transactionalRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionalRestController = new TransactionalRestController(transactionHandler);
    }

    SupplyRequest supplyRequest = new SupplyRequest(TestConstants.SUPPLY_REQUEST_ARTICLE_ID, TestConstants.SUPPLY_REQUEST_QUANTITY);

    @Test
    void addSupply_shouldThrowException_whenTokenIsInvalid() {
        String token = "invalidToken";
        doThrow(new SecurityException("Invalid token")).when(transactionHandler).addSupply(token, supplyRequest);

        assertThrows(SecurityException.class, () -> transactionalRestController.addSupply(token, supplyRequest));
    }

    @Test
    void addSupply_shouldThrowException_whenSupplyRequestIsNull() {
        String token = "validToken";
        doThrow(new IllegalArgumentException("SupplyRequest cannot be null")).when(transactionHandler).addSupply(eq(token), isNull());

        assertThrows(IllegalArgumentException.class, () -> transactionalRestController.addSupply(token, null));
    }

}