package bootcamp.transactionMicroservice.domain.api;

import bootcamp.transactionMicroservice.domain.model.Supply;

public interface ISupplyServicePort {
    void addSupply(Supply supply);
}
