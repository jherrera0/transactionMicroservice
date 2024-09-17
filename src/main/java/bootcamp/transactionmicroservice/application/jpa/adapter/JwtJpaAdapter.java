package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionmicroservice.infrastructure.configuration.security.JwtService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtJpaAdapter implements IJwtPersistencePort {
    private final JwtService jwtService;

    @Override
    public Long getUserId(String jwt) {
        return jwtService.extractUserId(jwt);
    }

    @Override
    public String getUserName(String jwt) {
        return jwtService.extractUsername(jwt);
    }
}
