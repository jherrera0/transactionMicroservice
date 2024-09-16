package bootcamp.transactionMicroservice.application.feign;

import bootcamp.transactionMicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionMicroservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "stock-service", url = "http://localhost:8082", configuration = FeignClientsConfig.class)
public interface IStockFeignClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/article/update")
    void updateArticleStock(@RequestBody SupplyRequest supplyRequest);

}
