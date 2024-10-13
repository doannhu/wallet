package retail_banking_deposit.com.wallet.mapper;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import retail_banking_deposit.com.wallet.dto.CustomerDTO;

public class CustomerMapper {

    public static CustomerDTO customerDTOMapper(CustomerAccount customerAccount) {
        return new CustomerDTO( customerAccount.getFirstName(),
                                customerAccount.getLastName(),
                                customerAccount.getCustomerNumber());
    }
}