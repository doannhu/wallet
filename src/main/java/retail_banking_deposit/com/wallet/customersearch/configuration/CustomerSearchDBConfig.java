package retail_banking_deposit.com.wallet.customersearch.configuration;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;

import retail_banking_deposit.com.wallet.customersearch.entity.CustomerSearch;

@Configuration
@EnableJpaRepositories(
        basePackages = "retail_banking_deposit.com.wallet.customersearch.repository",  // Secondary repository package
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class CustomerSearchDBConfig {

    @Value("${spring.datasource.secondary.url}")
    private String url;
	
	@Value("${spring.datasource.secondary.username}")
    private String username;
	
	@Value("${spring.datasource.secondary.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Bean(name = "secondaryDbDataSource")
    public DataSource secondaryDbDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driver)
                .build();
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDbDataSource())
                .packages("retail_banking_deposit.com.wallet.customersearch.entity")  // Entities for secondary DB
                .persistenceUnit("secondary")
                .build();
    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
