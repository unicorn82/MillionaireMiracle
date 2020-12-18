package com.millionaire.compound.miracle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@ComponentScan(basePackages = "com.millionaire.compound")
@EntityScan("com.millionaire.compound.hibernate.entity")
@EnableJpaRepositories("com.millionaire.compound.hibernate")
@SpringBootApplication(scanBasePackages = "com.millionaire.compound")
public class MiracleApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiracleApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties("app.datasource")
//	public HikariDataSource dataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123456");
		dataSource.setUrl(
				"jdbc:postgresql://pp-cacl-data.flatironssolutions.com:5432/postgres");

		return dataSource;
	}
//
//	@Bean
//	public CommandLineRunner run(NasdaqRepository nasdaqRepository) throws Exception {
//		return (String[] args) -> {
//			MiracleStock stock1 = new MiracleStock();
//			stock1.setTicker("ticker");
//			stock1.setSectorName("section1");
//			stock1.setSector("sector");
//			stock1.setCompany("company");
//			stock1.setId(1);
//			stock1.setMarketCap("markCap111");
//			stock1.setMarketCapGroup("markcapgroup");
//			nasdaqRepository.save(stock1);
//
//		};
//	}

//	@Bean
//	public DataSource getDataSource() {
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName("org.postgresql.Driver");
//		dataSourceBuilder.url("jdbc:postgresql://pp-cacl-data.flatironssolutions.com:5432/postgres");
//		dataSourceBuilder.username("postgres");
//		dataSourceBuilder.password("123456");
//		return dataSourceBuilder.build();
//	}

//
//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf)   {
//		return hemf.getSessionFactory();
//	}


}
