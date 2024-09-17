package bootcamp.transactionmicroservice.domain.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class SupplyTest {

    @Test
    void shouldCreateSupplyWithAllFields() {
        Supply supply = new Supply(1L, 2L, 100L, 3L, "Supplier A", LocalDateTime.now(), "Pending");
        assertNotNull(supply);
        assertEquals(1L, supply.getId());
        assertEquals(2L, supply.getProductId());
        assertEquals(100L, supply.getQuantity());
        assertEquals(3L, supply.getSupplierId());
        assertEquals("Supplier A", supply.getSupplier());
        assertEquals("Pending", supply.getStatus());
    }

    @Test
    void shouldCreateSupplyWithoutId() {
        Supply supply = new Supply(2L, 100L, 3L, "Supplier A", LocalDateTime.now(), "Pending");
        assertNotNull(supply);
        assertNull(supply.getId());
        assertEquals(2L, supply.getProductId());
        assertEquals(100L, supply.getQuantity());
        assertEquals(3L, supply.getSupplierId());
        assertEquals("Supplier A", supply.getSupplier());
        assertEquals("Pending", supply.getStatus());
    }

    @Test
    void shouldCreateSupplyWithDefaultConstructor() {
        Supply supply = new Supply();
        assertNotNull(supply);
    }

    @Test
    void shouldCopySupply() {
        Supply original = new Supply(1L, 2L, 100L, 3L, "Supplier A", LocalDateTime.now(), "Pending");
        Supply copy = new Supply(original);
        assertNotNull(copy);
        assertEquals(original.getId(), copy.getId());
        assertEquals(original.getProductId(), copy.getProductId());
        assertEquals(original.getQuantity(), copy.getQuantity());
        assertEquals(original.getSupplierId(), copy.getSupplierId());
        assertEquals(original.getSupplier(), copy.getSupplier());
        assertEquals(original.getDate(), copy.getDate());
        assertEquals(original.getStatus(), copy.getStatus());
    }

    @Test
    void shouldUpdateSupplyFields() {
        Supply supply = new Supply();
        supply.setId(1L);
        supply.setProductId(2L);
        supply.setQuantity(100L);
        supply.setSupplierId(3L);
        supply.setSupplier("Supplier A");
        supply.setDate(LocalDateTime.now());
        supply.setStatus("Pending");

        assertEquals(1L, supply.getId());
        assertEquals(2L, supply.getProductId());
        assertEquals(100L, supply.getQuantity());
        assertEquals(3L, supply.getSupplierId());
        assertEquals("Supplier A", supply.getSupplier());
        assertEquals("Pending", supply.getStatus());
    }
}