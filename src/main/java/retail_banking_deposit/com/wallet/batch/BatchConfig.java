package retail_banking_deposit.com.wallet.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import retail_banking_deposit.com.wallet.entity.AccountBalance;
import retail_banking_deposit.com.wallet.entity.ProductInterestRate;
import retail_banking_deposit.com.wallet.product.DepositProduct;
import retail_banking_deposit.com.wallet.repository.AccountBalanceRepository;
import retail_banking_deposit.com.wallet.repository.ProductInterestRateRepository;

import java.math.BigDecimal;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StepBuilder stepBuilder;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private ProductInterestRateRepository productInterestRateRepository;

    // Define Job
    @Bean
    public Job updateAccountBalanceJob(Step step1) {
        return new JobBuilder("updateAccountBalanceJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    // Define Step
    @Bean
    public Step step1(ItemReader<AccountBalance> reader,
                      ItemProcessor<AccountBalance, AccountBalance> processor,
                      ItemWriter<AccountBalance> writer) {
            return new StepBuilder("step1", jobRepository)
                    .<AccountBalance, AccountBalance> chunk(3, transactionManager)
                    .reader(reader)
                    .processor(processor)
                    .writer(writer)
                    .build();
    }

    // Reader: Reads all customer account balance
    @Bean
    public RepositoryItemReader<AccountBalance> reader() {
        return new RepositoryItemReaderBuilder<AccountBalance>()
                .repository(accountBalanceRepository)
                .methodName("findAll")
                .build();
    }

    // Processor: Applies the interest rate from the product table to the balance
    @Bean
    public ItemProcessor<AccountBalance, AccountBalance> processor() {
        return accountBalance -> {
            // Assume all accounts have the same type
            ProductInterestRate productInterestRate = productInterestRateRepository
                                                     .findLatestByProduct(DepositProduct.REGULAR_SAVING);

            // Apply interest: new balance = current balance + (balance * interest rate)
            BigDecimal interestRate = productInterestRate.getInterestRate();
            BigDecimal updatedBalance = accountBalance.getBalance()
                    .add(accountBalance.getBalance().multiply(interestRate));
            accountBalance.setBalance(updatedBalance);
            return accountBalance;
        };
    }

    // Writer: Update the customer accounts with new balances
//    @Bean
//    public ItemWriter<AccountBalance> writer() {
//        return accountBalances -> accountBalanceRepository.saveAll(accountBalances);
//    }
    @Bean
    public RepositoryItemWriter<AccountBalance> writer() {
        return new RepositoryItemWriterBuilder<AccountBalance>()
                .repository(accountBalanceRepository)
                .methodName("saveAll")
                .build();
    }
}
