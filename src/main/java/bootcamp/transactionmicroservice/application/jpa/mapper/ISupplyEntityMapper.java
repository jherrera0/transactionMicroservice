package bootcamp.transactionmicroservice.application.jpa.mapper;

import bootcamp.transactionmicroservice.application.jpa.entity.SupplyEntity;
import bootcamp.transactionmicroservice.domain.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ISupplyEntityMapper {
    @Mapping(target = "id", ignore = true)
    SupplyEntity toEntity(Supply supply);
    Supply toModel(SupplyEntity supplyEntity);
}
