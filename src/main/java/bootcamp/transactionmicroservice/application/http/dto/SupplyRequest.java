package bootcamp.transactionmicroservice.application.http.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SupplyRequest {

    @Positive
    @NotNull
    @NotBlank
    @NotEmpty
    private Long productId;

    @Positive
    @NotNull
    @NotBlank
    @NotEmpty
    private Long quantity;

    public SupplyRequest(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public @Positive @NotNull @NotBlank @NotEmpty Long getProductId() {
        return productId;
    }

    public void setProductId(@Positive @NotNull @NotBlank @NotEmpty Long productId) {
        this.productId = productId;
    }

    public @Positive @NotNull @NotBlank @NotEmpty Long getQuantity() {
        return quantity;
    }

    public void setQuantity(@Positive @NotNull @NotBlank @NotEmpty Long quantity) {
        this.quantity = quantity;
    }
}
