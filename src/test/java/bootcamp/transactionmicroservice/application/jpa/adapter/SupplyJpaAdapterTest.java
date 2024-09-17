package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.application.jpa.entity.SupplyEntity;
import bootcamp.transactionmicroservice.application.jpa.mapper.ISupplyEntityMapper;
import bootcamp.transactionmicroservice.application.jpa.repository.ISupplyRepository;
import bootcamp.transactionmicroservice.domain.model.Supply;
import bootcamp.transactionmicroservice.domain.exceptions.SupplyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SupplyJpaAdapterTest {

    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @InjectMocks
    private SupplyJpaAdapter supplyJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSupplyWithValidSupply() {
        Supply supply = new Supply();
        SupplyEntity supplyEntity = new SupplyEntity();

        when(supplyEntityMapper.toEntity(supply)).thenReturn(supplyEntity);

        supplyJpaAdapter.saveSupply(supply);

        verify(supplyRepository, times(1)).save(supplyEntity);
    }

    @Test
    void findBySupplyWithExistingSupply() {
        Supply supply = new Supply();
        SupplyEntity supplyEntity = new SupplyEntity();

        when(supplyEntityMapper.toEntity(supply)).thenReturn(supplyEntity);
        when(supplyRepository.findByProductIdAndQuantityAndSupplierIdAndSupplierAndDateAndStatus(
                supplyEntity.getProductId(), supplyEntity.getQuantity(), supplyEntity.getSupplierId(),
                supplyEntity.getSupplier(), supplyEntity.getDate(), supplyEntity.getStatus()))
                .thenReturn(supplyEntity);
        when(supplyEntityMapper.toModel(supplyEntity)).thenReturn(supply);

       supplyJpaAdapter.findBySupply(supply);

        verify(supplyRepository, times(1)).findByProductIdAndQuantityAndSupplierIdAndSupplierAndDateAndStatus(
                supplyEntity.getProductId(), supplyEntity.getQuantity(), supplyEntity.getSupplierId(),
                supplyEntity.getSupplier(), supplyEntity.getDate(), supplyEntity.getStatus());
        verify(supplyEntityMapper, times(1)).toModel(supplyEntity);
    }

    @Test
    void updateSupplyWithValidSupply() {
        Supply newSupply = new Supply();

        supplyJpaAdapter.updateSupply(newSupply);

        verify(supplyRepository, times(1)).updateBySupply(
                newSupply.getId(), newSupply.getProductId(), newSupply.getQuantity(), newSupply.getSupplierId(),
                newSupply.getSupplier(), newSupply.getDate(), newSupply.getStatus());
    }
}