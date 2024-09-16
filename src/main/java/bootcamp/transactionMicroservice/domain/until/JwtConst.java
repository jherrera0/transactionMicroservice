package bootcamp.transactionMicroservice.domain.until;

import jakarta.persistence.Id;

public class JwtConst {
    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";
    public static final Integer SUB_STRING_INDEX = 7;

    public static final String HAS_AUTHORITY_AUX_WAREHOUSE = "hasAuthority('AUX_WAREHOUSE')";
    public static final String RUTE_TRANSACTION = "/transaction";
    public static final String RUTE_TRANSACTION_ADD_SUPPLY = "/addSupply";

    public static final String ROLE = "Role";
    public static final String USER_ID = "Id";

    private JwtConst() {
        throw new IllegalStateException("Utility class");
    }
}
