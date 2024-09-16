package bootcamp.transactionMicroservice.application.jpa.adapter;

import bootcamp.transactionMicroservice.application.feign.IStockFeignClient;
import bootcamp.transactionMicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionMicroservice.domain.spi.IArticlePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IStockFeignClient stockFeignClient;

    @Override
    public void addSupply(Long productId, Long quantity) {
        stockFeignClient.updateArticleStock(new SupplyRequest (productId, quantity));
    }
}
