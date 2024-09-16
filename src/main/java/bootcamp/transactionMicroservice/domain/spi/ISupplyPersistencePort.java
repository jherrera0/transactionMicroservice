package bootcamp.transactionMicroservice.domain.spi;

import bootcamp.transactionMicroservice.domain.model.Supply;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);

    Supply findBySupply(Supply supply);

    void updateSupply(Supply newSupply);
}
