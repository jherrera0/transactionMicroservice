package bootcamp.transactionmicroservice.domain.until;

public class ExceptionConsts {
    public static final String MALFORMED_JWT_EXCEPTION = "Malformed JWT";
    public static final String SUPPLY_NOT_FOUND = "Supply not found";
    public static final String SUPPLY_VALUE_EXCEPTION = "Supply cant be null exception";

    public static final int CODE_404 = 404;
    public static final int CODE_400 = 400;
    public static final int CODE_500 = 500;

    private ExceptionConsts() {
    }
}
