package bootcamp.transactionmicroservice.application.jpa.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplyEntityTest {

    @Test
    void createSupplyEntityWithAllFields() {
        Long productId = 1L;
        Long quantity = 10L;
        Long supplierId = 2L;
        String supplier = "SupplierName";
        LocalDateTime date = LocalDateTime.now();
        String status = "Available";

        SupplyEntity supplyEntity = new SupplyEntity(productId, quantity, supplierId, supplier, date, status);

        assertEquals(productId, supplyEntity.getProductId());
        assertEquals(quantity, supplyEntity.getQuantity());
        assertEquals(supplierId, supplyEntity.getSupplierId());
        assertEquals(supplier, supplyEntity.getSupplier());
        assertEquals(date, supplyEntity.getDate());
        assertEquals(status, supplyEntity.getStatus());
    }

    @Test
    void createSupplyEntityWithDefaultConstructor() {
        SupplyEntity supplyEntity = new SupplyEntity();

        assertNotNull(supplyEntity);
    }

    @Test
    void setAndGetProductId() {
        SupplyEntity supplyEntity = new SupplyEntity();
        Long productId = 1L;

        supplyEntity.setProductId(productId);

        assertEquals(productId, supplyEntity.getProductId());
    }

    @Test
    void setAndGetQuantity() {
        SupplyEntity supplyEntity = new SupplyEntity();
        Long quantity = 10L;

        supplyEntity.setQuantity(quantity);

        assertEquals(quantity, supplyEntity.getQuantity());
    }

    @Test
    void setAndGetSupplierId() {
        SupplyEntity supplyEntity = new SupplyEntity();
        Long supplierId = 2L;

        supplyEntity.setSupplierId(supplierId);

        assertEquals(supplierId, supplyEntity.getSupplierId());
    }

    @Test
    void setAndGetSupplier() {
        SupplyEntity supplyEntity = new SupplyEntity();
        String supplier = "SupplierName";

        supplyEntity.setSupplier(supplier);

        assertEquals(supplier, supplyEntity.getSupplier());
    }

    @Test
    void setAndGetDate() {
        SupplyEntity supplyEntity = new SupplyEntity();
        LocalDateTime date = LocalDateTime.now();

        supplyEntity.setDate(date);

        assertEquals(date, supplyEntity.getDate());
    }

    @Test
    void setAndGetStatus() {
        SupplyEntity supplyEntity = new SupplyEntity();
        String status = "Available";

        supplyEntity.setStatus(status);

        assertEquals(status, supplyEntity.getStatus());
    }
}