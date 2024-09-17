package bootcamp.transactionmicroservice.domain.usecase;

import bootcamp.transactionmicroservice.domain.model.Supply;
import bootcamp.transactionmicroservice.domain.spi.IArticlePersistencePort;
import bootcamp.transactionmicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionmicroservice.domain.spi.ISupplyPersistencePort;
import bootcamp.transactionmicroservice.domain.until.TokenHolder;
import bootcamp.transactionmicroservice.domain.exceptions.SupplyValueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SupplyCaseTest {

    @Mock
    private IJwtPersistencePort jwtPersistencePort;

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @InjectMocks
    private SupplyCase supplyCase;

    private MockedStatic<TokenHolder> mockedTokenHolder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockedTokenHolder = mockStatic(TokenHolder.class);
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn("Bearer validToken");
    }

    @AfterEach
    void tearDown() {
        mockedTokenHolder.close();
    }

    @Test
    void addSupplyWithValidSupply() {
        Supply supply = new Supply();
        supply.setProductId(1L);
        supply.setQuantity(10L);

        when(jwtPersistencePort.getUserName(anyString())).thenReturn("testuser");
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(123L);
        when(supplyPersistencePort.findBySupply(any(Supply.class))).thenReturn(supply);

        supplyCase.addSupply(supply);

        verify(supplyPersistencePort, times(1)).saveSupply(supply);
        verify(articlePersistencePort, times(1)).addSupply(supply.getProductId(), supply.getQuantity());
        verify(supplyPersistencePort, times(1)).updateSupply(any(Supply.class));
    }

    @Test
    void addSupplyWithNullSupply() {
        assertThrows(SupplyValueException.class, () -> supplyCase.addSupply(null));
    }

    @Test
    void addSupplyWithExceptionDuringArticleUpdate() {
        Supply supply = new Supply();
        supply.setProductId(1L);
        supply.setQuantity(10L);

        when(jwtPersistencePort.getUserName(anyString())).thenReturn("testuser");
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(123L);
        when(supplyPersistencePort.findBySupply(any(Supply.class))).thenReturn(supply);
        doThrow(new RuntimeException()).when(articlePersistencePort).addSupply(anyLong(), anyLong());

        supplyCase.addSupply(supply);

        verify(supplyPersistencePort, times(1)).saveSupply(supply);
        verify(articlePersistencePort, times(1)).addSupply(supply.getProductId(), supply.getQuantity());
        verify(supplyPersistencePort, times(1)).updateSupply(any(Supply.class));
    }
}