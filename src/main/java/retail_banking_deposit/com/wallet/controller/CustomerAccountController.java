package retail_banking_deposit.com.wallet.controller;

import retail_banking_deposit.com.wallet.customersearch.entity.CustomerSearch;
import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import retail_banking_deposit.com.wallet.service.CustomerAccountService;
import retail_banking_deposit.com.wallet.validator.CustomerValidator;
import retail_banking_deposit.com.wallet.dto.CustomerDTO;
import retail_banking_deposit.com.wallet.mapper.CustomerMapper;
import retail_banking_deposit.com.wallet.mapper.CustomerSearchMapper;
import retail_banking_deposit.com.wallet.customersearch.service.CustomerSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerAccountController {

    @Autowired
    private CustomerAccountService accountService;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private CustomerSearchService customerSearchService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerAccount account) {
        
        // Validate the input fields using CustomerValidator
        String validationResult = customerValidator.validateCustomer(account);
        if (!validationResult.isEmpty()) {
            return ResponseEntity.badRequest().body(validationResult);
        }
        
        // Generate a unique customer number
        String customerNumber = generateCustomerNumber();

        // Set the generated customer number in the account
        account.setCustomerNumber(customerNumber);

        // Save the customer account
        CustomerAccount createdAccount = accountService.createAccount(account);


        // Create CustomerDTO from mapper
        CustomerDTO customerDTO = CustomerMapper.customerDTOMapper(createdAccount);

        // Prepare response
        String responseMessage = String.format("Customer %s %s has been created with Customer Number: %s",
        customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getCustomerNumber());

        // Add the account to search database
        CustomerSearch customerSearch = CustomerSearchMapper.customerSearchMapper(account);
        customerSearchService.createAccountSearch(customerSearch);

        return ResponseEntity.ok(responseMessage);
    }

    // Helper method to generate a unique customer number
    private String generateCustomerNumber() {
        return UUID.randomUUID().toString();  // Generates a unique random customer number
    }
}
