package ru.itis.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "ru.itis.task")
@EnableJpaRepositories("ru.itis.task.repository")
@EntityScan("ru.itis.task.model")
public class TaskApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String PROP_DB_DRIVER_CLASS = "spring.datasource.driver-class-name";
    private static final String PROP_DB_URL = "spring.datasource.url";
    private static final String PROP_DB_USER = "spring.datasource.username";
    private static final String PROP_DB_PASS = "spring.datasource.password";

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty(PROP_DB_DRIVER_CLASS)));
        dataSource.setUrl(env.getProperty(PROP_DB_URL));
        dataSource.setUsername(env.getProperty(PROP_DB_USER));
        dataSource.setPassword(env.getProperty(PROP_DB_PASS));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ru.itis.task.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.put("hibernate.allow_update_outside_transaction", "true");
        return properties;
    }


    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

}
