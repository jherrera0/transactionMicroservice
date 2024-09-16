package bootcamp.transactionMicroservice.model.exceptions;

public class SupplyNotFoundException extends RuntimeException {
    public SupplyNotFoundException( String message) {
        super(message);
    }
}
