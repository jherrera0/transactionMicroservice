package bootcamp.transactionmicroservice.domain.until;

public class JwtConst {
    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";
    public static final Integer SUB_STRING_INDEX = 7;

    public static final String HAS_AUTHORITY_AUX_WAREHOUSE = "hasAuthority('AUX_WAREHOUSE')";
    public static final String RUTE_TRANSACTION = "/transaction";
    public static final String RUTE_TRANSACTION_ADD_SUPPLY = "/addSupply";

    public static final String ROLE = "Role";
    public static final String USER_ID = "Id";
    public static final String EMPTYSTRING = "";

    private JwtConst() {
        throw new IllegalStateException("Utility class");
    }
}
