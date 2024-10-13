package retail_banking_deposit.com.wallet.configuration;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Primary;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;

import retail_banking_deposit.com.wallet.entity.CustomerAccount;

@Configuration
@EnableJpaRepositories(
        basePackages = "retail_banking_deposit.com.wallet.repository",  // Primary repository package
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class CustomerAccountDBConfig {

    @Value("${spring.datasource.primary.url}")
    private String url;
	
	@Value("${spring.datasource.primary.username}")
    private String username;
	
	@Value("${spring.datasource.primary.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driver;
   
    @Bean(name = "primaryDbDataSource")
    @Primary
    public DataSource primaryDbDataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driver)
                .build();
    }
  
    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(primaryDbDataSource())
                .packages("retail_banking_deposit.com.wallet.entity")  // Entities for primary DB
                .persistenceUnit("primary")
                .build();
    }
    
    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
