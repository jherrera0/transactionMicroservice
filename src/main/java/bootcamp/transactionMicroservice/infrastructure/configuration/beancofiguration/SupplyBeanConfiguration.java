package bootcamp.transactionMicroservice.infrastructure.configuration.beancofiguration;

import bootcamp.transactionMicroservice.application.feign.IStockFeignClient;
import bootcamp.transactionMicroservice.application.jpa.adapter.ArticleJpaAdapter;
import bootcamp.transactionMicroservice.application.jpa.adapter.JwtJpaAdapter;
import bootcamp.transactionMicroservice.application.jpa.adapter.SupplyJpaAdapter;
import bootcamp.transactionMicroservice.application.jpa.mapper.ISupplyEntityMapper;
import bootcamp.transactionMicroservice.application.jpa.repository.ISupplyRepository;
import bootcamp.transactionMicroservice.domain.api.ISupplyServicePort;
import bootcamp.transactionMicroservice.domain.spi.IArticlePersistencePort;
import bootcamp.transactionMicroservice.domain.spi.IJwtPersistencePort;
import bootcamp.transactionMicroservice.domain.spi.ISupplyPersistencePort;
import bootcamp.transactionMicroservice.domain.usecase.SupplyCase;
import bootcamp.transactionMicroservice.infrastructure.configuration.security.JwtService;
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
