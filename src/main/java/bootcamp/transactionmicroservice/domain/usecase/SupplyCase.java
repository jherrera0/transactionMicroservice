package bootcamp.transactionmicroservice.domain.usecase;

import bootcamp.transactionmicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionmicroservice.domain.model.Supply;
import bootcamp.transactionmicroservice.domain.spi.IArticlePersistencePort;
import bootcamp.transactionmicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionmicroservice.domain.spi.ISupplyPersistencePort;
import bootcamp.transactionmicroservice.domain.until.ExceptionConsts;
import bootcamp.transactionmicroservice.domain.until.JwtConst;
import bootcamp.transactionmicroservice.domain.until.SupplyConst;
import bootcamp.transactionmicroservice.domain.until.TokenHolder;
import bootcamp.transactionmicroservice.domain.exceptions.SupplyValueException;

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
        if(supply == null){
            throw new SupplyValueException(ExceptionConsts.SUPPLY_VALUE_EXCEPTION);
        }
        String token = TokenHolder.getToken().replace(JwtConst.BEARER, JwtConst.EMPTYSTRING);
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
            Supply newSupply =  new Supply(supplyPersistencePort.findBySupply(supply));
            newSupply.setStatus(String.valueOf(SupplyConst.FAILED));
            supplyPersistencePort.updateSupply(newSupply);
        }

    }
}
