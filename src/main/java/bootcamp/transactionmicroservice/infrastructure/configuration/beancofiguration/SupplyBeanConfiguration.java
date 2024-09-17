package bootcamp.transactionmicroservice.infrastructure.configuration.beancofiguration;

import bootcamp.transactionmicroservice.application.feign.IStockFeignClient;
import bootcamp.transactionmicroservice.application.jpa.adapter.ArticleJpaAdapter;
import bootcamp.transactionmicroservice.application.jpa.adapter.JwtJpaAdapter;
import bootcamp.transactionmicroservice.application.jpa.adapter.SupplyJpaAdapter;
import bootcamp.transactionmicroservice.application.jpa.mapper.ISupplyEntityMapper;
import bootcamp.transactionmicroservice.application.jpa.repository.ISupplyRepository;
import bootcamp.transactionmicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionmicroservice.domain.spi.IArticlePersistencePort;
import bootcamp.transactionmicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionmicroservice.domain.spi.ISupplyPersistencePort;
import bootcamp.transactionmicroservice.domain.usecase.SupplyCase;
import bootcamp.transactionmicroservice.infrastructure.configuration.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SupplyBeanConfiguration{

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;
    private final JwtService jwtService;
    private final IStockFeignClient stockFeignClient;

    @Bean
    public ISupplyServicePort supplyService(){
        return new SupplyCase(jwtPersistencePort(), supplyPersistence(),articlePersistence());
    }

    @Bean
    public IArticlePersistencePort articlePersistence(){
        return new ArticleJpaAdapter(stockFeignClient);
    }

    @Bean
    public ISupplyPersistencePort supplyPersistence(){
        return new SupplyJpaAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public IJwtPersistencePort jwtPersistencePort(){
        return new JwtJpaAdapter(jwtService);
    }
}
