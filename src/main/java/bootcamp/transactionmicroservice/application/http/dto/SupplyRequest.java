package bootcamp.transactionmicroservice.application.http.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplyRequest {

    @Positive
    @NotNull
    private Long productId;

    @Positive
    @NotNull
    private Long quantity;

    public SupplyRequest(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public @Positive @NotNull Long getProductId() {
        return productId;
    }

    public void setProductId(@Positive @NotNull Long productId) {
        this.productId = productId;
    }

    public @Positive @NotNull Long getQuantity() {
        return quantity;
    }

    public void setQuantity(@Positive @NotNull Long quantity) {
        this.quantity = quantity;
    }
}
