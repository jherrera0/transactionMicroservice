package bootcamp.transactionmicroservice.domain.api;

import bootcamp.transactionmicroservice.domain.model.Supply;

public interface ISupplyServicePort {
    void addSupply(Supply supply);
}
