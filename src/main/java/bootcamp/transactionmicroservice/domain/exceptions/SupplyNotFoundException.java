package bootcamp.transactionmicroservice.domain.exceptions;

public class SupplyNotFoundException extends RuntimeException {
    public SupplyNotFoundException( String message) {
        super(message);
    }
}
