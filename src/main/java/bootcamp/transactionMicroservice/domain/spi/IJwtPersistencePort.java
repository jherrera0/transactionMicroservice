package bootcamp.transactionMicroservice.domain.spi;

public interface IJwtPersistencePort {
    Long getUserId(String jwt);
    String getUserName(String jwt);
}
