package bootcamp.transactionMicroservice.domain.exceptions;

import bootcamp.transactionMicroservice.domain.until.ExceptionConsts;

public class MalFormJwtException extends RuntimeException {
    public MalFormJwtException() {
        super(ExceptionConsts.MALFORMED_JWT_EXCEPTION);
    }
}
