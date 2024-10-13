package retail_banking_deposit.com.wallet.product;

public interface CalculateBalance {
    Double calculateCompoundInterest(Double principle, Double interestRate, Double timeDeposit);
    Double calculateServiceCharge();
}
