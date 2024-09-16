package bootcamp.transactionmicroservice.domain.exceptions;

import bootcamp.transactionmicroservice.domain.until.ExceptionConsts;

public class MalFormJwtException extends RuntimeException {
    public MalFormJwtException() {
        super(ExceptionConsts.MALFORMED_JWT_EXCEPTION);
    }
}
