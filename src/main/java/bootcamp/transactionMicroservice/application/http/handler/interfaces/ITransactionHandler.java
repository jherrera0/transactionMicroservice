package bootcamp.transactionMicroservice.application.http.handler.interfaces;

import bootcamp.transactionMicroservice.application.http.dto.SupplyRequest;

public interface ITransactionHandler {
    void addSupply(String jwt, SupplyRequest supplyRequest);

}
