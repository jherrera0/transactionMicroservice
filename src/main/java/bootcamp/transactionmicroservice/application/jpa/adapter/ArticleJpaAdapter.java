package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.application.feign.IStockFeignClient;
import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.domain.spi.IArticlePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IStockFeignClient stockFeignClient;

    @Override
    public void addSupply(Long productId, Long quantity) {
        stockFeignClient.updateArticleStock(new SupplyRequest (productId, quantity));
    }
}
