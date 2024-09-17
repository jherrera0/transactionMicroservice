package bootcamp.transactionmicroservice.domain.spi;

import bootcamp.transactionmicroservice.domain.model.Supply;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);

    Supply findBySupply(Supply supply);

    void updateSupply(Supply newSupply);
}
