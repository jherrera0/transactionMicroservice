package bootcamp.transactionmicroservice.application.http.handler;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.application.http.handler.interfaces.ITransactionHandler;
import bootcamp.transactionmicroservice.application.http.mapper.ISupplyRequestMapper;
import bootcamp.transactionmicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionmicroservice.domain.model.Supply;
import bootcamp.transactionmicroservice.domain.until.TokenHolder;
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
