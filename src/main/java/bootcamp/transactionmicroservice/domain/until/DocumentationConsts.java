package bootcamp.transactionmicroservice.domain.until;

public class DocumentationConsts {
    public static final String TRANSACTION_CONTROLLER_NAME = "Transaction Controller";
    public static final String TRANSACTION_CONTROLLER_DESCRIPTION = "This controller is responsible for handling all the transaction related operations.";
    public static final String TRANSACTION_CONTROLLER_ADD_SUPPLY_NAME = "Add Supply";
    public static final String CODE_STATUS_201 = "201";
    public static final String CODE_STATUS_401 = "401";
    public static final String CODE_STATUS_403 = "403";
    public static final String DESCRIPTION_STATUS_201_TRANSACTIONAL = "The transaction was successfully created.";
    public static final String DESCRIPTION_STATUS_401_TRANSACTIONAL = "The user is not authenticated.";
    public static final String DESCRIPTION_STATUS_403_TRANSACTIONAL = "The user is not authorized to perform this operation.";

    private DocumentationConsts() {
    }
}

