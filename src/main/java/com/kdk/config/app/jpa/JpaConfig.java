package com.kdk.config.app.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2025. 3. 1. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
@Configuration
@EnableJpaRepositories(
		basePackages = "com.kdk.app.jpa.repository",
	    entityManagerFactoryRef = "entityManagerFactory",
	    transactionManagerRef = "transactionManager"
)
public class JpaConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(dataSource)
            .packages("com.kdk.app.jpa.entity")
            .persistenceUnit("primary")
            .build();
    }

}
