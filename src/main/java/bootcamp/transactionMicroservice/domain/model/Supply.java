package bootcamp.transactionMicroservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Supply {
    private Long id;
    private Long productId;
    private Long quantity;
    private Long supplierId;
    private String supplier;
    private LocalDateTime date;
    private String status;

    public Supply(Long id, Long productId, Long quantity, Long supplierId, String supplier, LocalDateTime date, String status) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.supplier = supplier;
        this.date = date;
        this.status = status;
    }

    public Supply(Long productId, Long quantity, Long supplierId, String supplier, LocalDateTime date, String status) {
        this.productId = productId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.supplier = supplier;
        this.date = date;
        this.status = status;
    }

    public Supply() {
    }

    public Supply(Supply bySupply) {
        this.id = bySupply.id;
        this.productId = bySupply.productId;
        this.quantity = bySupply.quantity;
        this.supplierId = bySupply.supplierId;
        this.supplier = bySupply.supplier;
        this.date = bySupply.date;
        this.status = bySupply.status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
