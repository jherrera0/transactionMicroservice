package bootcamp.transactionmicroservice.application.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long quantity;
    private Long supplierId;
    private String supplier;
    private LocalDateTime date;
    private String status;

    public SupplyEntity() {
    }

    public SupplyEntity(Long productId, Long quantity, Long supplierId, String supplier, LocalDateTime date, String status) {
        this.productId = productId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.supplier = supplier;
        this.date = date;
        this.status = status;
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

    public void setSupplierId(Long userId) {
        this.supplierId = userId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String userEmail) {
        this.supplier = userEmail;
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
