package bootcamp.transactionMicroservice.domain.usecase;

import bootcamp.transactionMicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionMicroservice.domain.model.Supply;
import bootcamp.transactionMicroservice.domain.spi.IArticlePersistencePort;
import bootcamp.transactionMicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionMicroservice.domain.spi.ISupplyPersistencePort;
import bootcamp.transactionMicroservice.domain.until.SupplyConst;
import bootcamp.transactionMicroservice.domain.until.TokenHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SupplyCase implements ISupplyServicePort{
    private final IJwtPersistencePort jwtPersistencePort;
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IArticlePersistencePort articlePersistencePort;

    public SupplyCase(IJwtPersistencePort jwtPersistencePort, ISupplyPersistencePort supplyPersistencePort, IArticlePersistencePort articlePersistencePort){
        this.jwtPersistencePort = jwtPersistencePort;
        this.supplyPersistencePort = supplyPersistencePort;
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        String token = TokenHolder.getToken().replace("Bearer ", "");
        supply.setSupplier(jwtPersistencePort.getUserName(token));
        supply.setSupplierId(jwtPersistencePort.getUserId(token));
        supply.setDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        supply.setStatus(String.valueOf(SupplyConst.IN_PROGRESS));
        supplyPersistencePort.saveSupply(supply);

        try {
            Supply newSupply =  new Supply(supplyPersistencePort.findBySupply(supply));
            articlePersistencePort.addSupply(supply.getProductId(), supply.getQuantity());
            newSupply.setStatus(String.valueOf(SupplyConst.COMPLETED));
            supplyPersistencePort.updateSupply(newSupply);

        }catch (Exception e){
            System.out.println(e.getMessage());
            Supply newSupply =  new Supply(supplyPersistencePort.findBySupply(supply));
            newSupply.setStatus(String.valueOf(SupplyConst.FAILED));
            supplyPersistencePort.updateSupply(newSupply);
        }

    }
}
