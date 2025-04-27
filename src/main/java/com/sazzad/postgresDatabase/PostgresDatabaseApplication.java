package com.sazzad.postgresDatabase;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class PostgresDatabaseApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public PostgresDatabaseApplication(final DataSource dataSource){
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(PostgresDatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Datasource= " + dataSource );
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");
	}
}
