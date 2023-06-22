package com.tour.kuma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.sql.DataSource;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TourkumaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourkumaApplication.class, args);
    }

}
