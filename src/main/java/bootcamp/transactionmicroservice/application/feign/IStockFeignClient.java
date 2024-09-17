package bootcamp.transactionmicroservice.application.feign;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "stock-service", url = "http://localhost:8082", configuration = FeignClientsConfig.class)
public interface IStockFeignClient {
    @PutMapping("/article/update")
    void updateArticleStock(@RequestBody SupplyRequest supplyRequest);

}
