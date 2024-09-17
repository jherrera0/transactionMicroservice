package bootcamp.transactionmicroservice.application.http.handler.interfaces;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;

public interface ITransactionHandler {
    void addSupply(String jwt, SupplyRequest supplyRequest);

}
