package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.application.feign.IStockFeignClient;
import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {

    @Mock
    private IStockFeignClient stockFeignClient;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSupplyWithValidParameters() {
        Long productId = 1L;
        Long quantity = 10L;

        articleJpaAdapter.addSupply(productId, quantity);

        verify(stockFeignClient, times(1)).updateArticleStock(new SupplyRequest(productId, quantity));
    }

    @Test
    void addSupplyWithNullProductId() {
        Long productId = null;
        Long quantity = 10L;

        articleJpaAdapter.addSupply(productId, quantity);

        verify(stockFeignClient, times(1)).updateArticleStock(new SupplyRequest(productId, quantity));
    }

    @Test
    void addSupplyWithNullQuantity() {
        Long productId = 1L;
        Long quantity = null;

        articleJpaAdapter.addSupply(productId, quantity);

        verify(stockFeignClient, times(1)).updateArticleStock(new SupplyRequest(productId, quantity));
    }

    @Test
    void addSupplyWithNegativeProductId() {
        Long productId = -1L;
        Long quantity = 10L;

        articleJpaAdapter.addSupply(productId, quantity);

        verify(stockFeignClient, times(1)).updateArticleStock(new SupplyRequest(productId, quantity));
    }

    @Test
    void addSupplyWithNegativeQuantity() {
        Long productId = 1L;
        Long quantity = -10L;

        articleJpaAdapter.addSupply(productId, quantity);

        verify(stockFeignClient, times(1)).updateArticleStock(new SupplyRequest(productId, quantity));
    }
}