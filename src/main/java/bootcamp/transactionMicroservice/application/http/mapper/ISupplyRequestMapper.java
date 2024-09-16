package bootcamp.transactionMicroservice.application.http.mapper;

import bootcamp.transactionMicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionMicroservice.domain.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ISupplyRequestMapper {
    Supply toDomain(SupplyRequest supplyRequest);
}
