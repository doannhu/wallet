package retail_banking_deposit.com.wallet.mapper;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import retail_banking_deposit.com.wallet.customersearch.entity.CustomerSearch;

public class CustomerSearchMapper {
    public static CustomerSearch customerSearchMapper(CustomerAccount customerAccount) {

        String customerName = customerAccount.getFirstName() +
                " " +
                customerAccount.getLastName();
        return new CustomerSearch(customerAccount.getCustomerNumber(),
                                    customerName);
    }
}