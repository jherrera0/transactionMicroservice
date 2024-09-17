package bootcamp.transactionmicroservice.domain.exceptions;

public class SupplyValueException extends RuntimeException{
    public SupplyValueException(String string) {
        super(string);
    }
}
