package retail_banking_deposit.com.wallet.validator;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CustomerValidator {

    // Validate the customer input fields
    public String validateCustomer(CustomerAccount account) {
        StringBuilder errors = new StringBuilder();

        // Check if any field is empty
        if (isEmpty(account.getFirstName())) {
            errors.append("First name cannot be empty.\n");
        }
        if (isEmpty(account.getLastName())) {
            errors.append("Last name cannot be empty.\n");
        }
        if (isEmpty(account.getEmail())) {
            errors.append("Email cannot be empty.\n");
        }
        if (isEmpty(account.getAddress())) {
            errors.append("Address cannot be empty.\n");
        }
        if (isEmpty(account.getPhoneNumber())) {
            errors.append("Phone number cannot be empty.\n");
        }

        // Validate first name (no numbers or special characters)
        if (!isEmpty(account.getFirstName()) && !Pattern.matches("^[a-zA-Z]+$", account.getFirstName())) {
            errors.append("Invalid first name: only alphabetic characters are allowed.\n");
        }

        // Validate last name (no numbers or special characters)
        if (!isEmpty(account.getLastName()) && !Pattern.matches("^[a-zA-Z]+$", account.getLastName())) {
            errors.append("Invalid last name: only alphabetic characters are allowed.\n");
        }

        // Validate email (using a simple email regex)
        if (!isEmpty(account.getEmail()) && !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", account.getEmail())) {
            errors.append("Invalid email: please provide a valid email address.\n");
        }

        // Validate phone number (only numbers, dashes, and parentheses allowed)
        if (!isEmpty(account.getPhoneNumber()) && !Pattern.matches("^[0-9\\-\\(\\)]+$", account.getPhoneNumber())) {
            errors.append("Invalid phone number: only numbers, dashes, and parentheses are allowed.\n");
        }

        return errors.toString();
    }

    // Helper method to check if a string is empty or null
    private boolean isEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
