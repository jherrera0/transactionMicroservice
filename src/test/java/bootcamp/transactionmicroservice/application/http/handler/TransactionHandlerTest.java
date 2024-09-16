package bootcamp.transactionmicroservice.application.http.handler;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.application.http.mapper.ISupplyRequestMapper;
import bootcamp.transactionmicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionmicroservice.domain.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TransactionHandlerTest {

    @Mock
    private ISupplyServicePort supplyServicePort;

    @Mock
    private ISupplyRequestMapper supplyRequestMapper;

    @InjectMocks
    private TransactionHandler transactionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSupplyWithValidRequest() {
        String jwt = "valid.jwt.token";
        SupplyRequest supplyRequest = new SupplyRequest(1L, 10L);
        Supply supply = new Supply();

        when(supplyRequestMapper.toDomain(supplyRequest)).thenReturn(supply);

        transactionHandler.addSupply(jwt, supplyRequest);

        verify(supplyRequestMapper, times(1)).toDomain(supplyRequest);
        verify(supplyServicePort, times(1)).addSupply(supply);
    }

    @Test
    void addSupplyWithNullJwt() {
        String jwt = null;
        SupplyRequest supplyRequest = new SupplyRequest(1L, 10L);
        Supply supply = new Supply();

        when(supplyRequestMapper.toDomain(supplyRequest)).thenReturn(supply);

        transactionHandler.addSupply(jwt, supplyRequest);

        verify(supplyRequestMapper, times(1)).toDomain(supplyRequest);
        verify(supplyServicePort, times(1)).addSupply(supply);
    }
}