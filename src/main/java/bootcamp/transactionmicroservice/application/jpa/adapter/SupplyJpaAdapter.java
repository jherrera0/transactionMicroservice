package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.application.jpa.entity.SupplyEntity;
import bootcamp.transactionmicroservice.application.jpa.mapper.ISupplyEntityMapper;
import bootcamp.transactionmicroservice.application.jpa.repository.ISupplyRepository;
import bootcamp.transactionmicroservice.domain.model.Supply;
import bootcamp.transactionmicroservice.domain.spi.ISupplyPersistencePort;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyPersistencePort {
    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;

    @Override
    public void saveSupply(Supply supply) {
        SupplyEntity supplyEntity = supplyEntityMapper.toEntity(supply);
        supplyRepository.save(supplyEntity);
    }

    @Override
    public Supply findBySupply(Supply supply) {
        SupplyEntity supplyEntity = supplyEntityMapper.toEntity(supply);
        return supplyEntityMapper.toModel(
                supplyRepository.findByProductIdAndQuantityAndSupplierIdAndSupplierAndDateAndStatus(
                        supplyEntity.getProductId(), supplyEntity.getQuantity(), supplyEntity.getSupplierId(),
                        supplyEntity.getSupplier(), supplyEntity.getDate(), supplyEntity.getStatus())  //pasar al usecase, cambiar de optional a objet
        );
    }

    @Override
    @Transactional
    public void updateSupply(Supply newSupply) {
        supplyRepository.updateBySupply(newSupply.getId(),newSupply.getProductId(), newSupply.getQuantity(), newSupply.getSupplierId(),
                newSupply.getSupplier(), newSupply.getDate(), newSupply.getStatus());
    }
}