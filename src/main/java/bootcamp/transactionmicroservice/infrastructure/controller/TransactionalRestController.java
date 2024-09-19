package bootcamp.transactionmicroservice.infrastructure.controller;

import bootcamp.transactionmicroservice.application.http.dto.SupplyRequest;
import bootcamp.transactionmicroservice.application.http.handler.interfaces.ITransactionHandler;
import bootcamp.transactionmicroservice.domain.until.DocumentationConsts;
import bootcamp.transactionmicroservice.domain.until.JwtConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController(JwtConst.RUTE_TRANSACTION)
@RequiredArgsConstructor
@Tag(name = DocumentationConsts.TRANSACTION_CONTROLLER_NAME, description = DocumentationConsts.TRANSACTION_CONTROLLER_DESCRIPTION)
public class TransactionalRestController {
    private final ITransactionHandler transactionHandler;


    @Operation(summary = DocumentationConsts.TRANSACTION_CONTROLLER_ADD_SUPPLY_NAME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = DocumentationConsts.CODE_STATUS_201, description = DocumentationConsts.DESCRIPTION_STATUS_201_TRANSACTIONAL, content = @Content),
            @ApiResponse(responseCode = DocumentationConsts.CODE_STATUS_401, description = DocumentationConsts.DESCRIPTION_STATUS_401_TRANSACTIONAL, content = @Content),
            @ApiResponse(responseCode = DocumentationConsts.CODE_STATUS_403, description = DocumentationConsts.DESCRIPTION_STATUS_403_TRANSACTIONAL, content = @Content)
    })

    @PreAuthorize(JwtConst.HAS_AUTHORITY_AUX_WAREHOUSE)
    @PostMapping(JwtConst.RUTE_TRANSACTION_ADD_SUPPLY)
    public void addSupply(@RequestHeader(JwtConst.AUTHORIZATION) String token, @RequestBody SupplyRequest supplyRequest) {
        transactionHandler.addSupply(token,supplyRequest);
    }

}
