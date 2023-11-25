package ttps.spring.config;


import java.util.Properties;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	private static final String MODEL_PACKAGE = "ttps.spring.model";
	
	@Bean 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan(new String[] {MODEL_PACKAGE});
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(additionalProperties());
		return emf;		
	}
		
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		//driverManagerDataSource.setUsername("usr6");
		//driverManagerDataSource.setPassword("pwd6");
		//driverManagerDataSource.setUrl("jdbc:mysql://mysql.java.linti.unlp.edu.ar/ttps_sala_bd6?useSSL=FALSE");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/cuentas_claras");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return driverManagerDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}
	
	
	private Properties additionalProperties() {
		Properties properties = new Properties();		
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		//properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.default_schema", "stock");
		return properties;
	}
		
}
