package br.com.spedroza.PaymentHub;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource){
		System.out.println("Getting entityManagerFactory...");
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		// database connection details
		factoryBean.setDataSource(datasource);
		
		// hibernate properties
		factoryBean.setJpaProperties(additionalProperties());
				
		//models
		factoryBean.setPackagesToScan("br.com.spedroza.PaymentHub.model");
		return factoryBean;
	}

	
	@Bean
	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("spedroza");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost:3306/financials?useSSL=false&serverTimezone=UTC");
		return dataSource;
	}
	*/
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFac){
		System.out.println("Getting transactionManager...");
		return new JpaTransactionManager(entityManagerFac);
	}
}
