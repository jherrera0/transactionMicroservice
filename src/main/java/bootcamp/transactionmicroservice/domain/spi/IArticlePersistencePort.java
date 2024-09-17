package bootcamp.transactionmicroservice.domain.spi;

public interface IArticlePersistencePort {
    void addSupply(Long productId, Long quantity);
}
