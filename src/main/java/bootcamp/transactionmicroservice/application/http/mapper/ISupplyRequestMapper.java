package bootcamp.transactionmicroservice.application.http.mapper;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.domain.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ISupplyRequestMapper {
    Supply toDomain(SupplyRequest supplyRequest);
}
