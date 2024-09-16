package bootcamp.transactionMicroservice.domain.spi;

public interface IArticlePersistencePort {
    void addSupply(Long productId, Long quantity);
}
