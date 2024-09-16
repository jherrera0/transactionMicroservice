package bootcamp.transactionMicroservice.application.jpa.adapter;

import bootcamp.transactionMicroservice.application.jpa.entity.SupplyEntity;
import bootcamp.transactionMicroservice.application.jpa.mapper.ISupplyEntityMapper;
import bootcamp.transactionMicroservice.application.jpa.repository.ISupplyRepository;
import bootcamp.transactionMicroservice.domain.model.Supply;
import bootcamp.transactionMicroservice.domain.spi.ISupplyPersistencePort;

import bootcamp.transactionMicroservice.domain.until.ExceptionConsts;
import bootcamp.transactionMicroservice.model.exceptions.SupplyNotFoundException;
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
                        supplyEntity.getSupplier(), supplyEntity.getDate(), supplyEntity.getStatus())
                .orElseThrow(() -> new SupplyNotFoundException(ExceptionConsts.SUPPLY_NOT_FOUND))
        );
    }

    @Override
    @Transactional
    public void updateSupply(Supply newSupply) {
        supplyRepository.updateBySupply(newSupply.getId(),newSupply.getProductId(), newSupply.getQuantity(), newSupply.getSupplierId(),
                newSupply.getSupplier(), newSupply.getDate(), newSupply.getStatus());
    }
}