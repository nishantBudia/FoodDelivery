package com.nishant.FoodDelivery.token.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.nishant.FoodDelivery.token.repo",
        entityManagerFactoryRef = "tokenEntityManager",
        transactionManagerRef = "tokenTransactionManager"
)
public class TokenDatabaseConfiguration {

    @Bean(name = "tokenDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.token")
    public DataSource tokenDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tokenEntityManager")
    public LocalContainerEntityManagerFactoryBean tokenEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tokenDataSource") DataSource tokenDataSource){
        HashMap<String,Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto","create");

        return builder
                .dataSource(tokenDataSource)
                .packages("com.nishant.FoodDelivery.token.model")
                .properties(properties)
                .build();
    }

    @Bean(name = "tokenTransactionManager")
    public PlatformTransactionManager tokenTransactionManager(
            @Qualifier("tokenEntityManager") EntityManagerFactory tokenEntityManager){
        return new JpaTransactionManager(tokenEntityManager);
    }
}
