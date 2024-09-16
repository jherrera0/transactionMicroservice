package bootcamp.transactionMicroservice.application.http.handler;

import bootcamp.transactionMicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionMicroservice.application.http.handler.interfaces.ITransactionHandler;
import bootcamp.transactionMicroservice.application.http.mapper.ISupplyRequestMapper;
import bootcamp.transactionMicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionMicroservice.domain.model.Supply;
import bootcamp.transactionMicroservice.domain.until.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionHandler implements ITransactionHandler {

    private final ISupplyServicePort supplyServicePort;
    private final ISupplyRequestMapper supplyRequestMapper;

    @Override
    public void addSupply(String jwt, SupplyRequest supplyRequest) {
        TokenHolder.setToken(jwt);
        Supply supply = supplyRequestMapper.toDomain(supplyRequest);
        supplyServicePort.addSupply(supply);
        TokenHolder.clear();
    }
}
