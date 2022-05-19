package ru.itis.nationalbankru.configs;

import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.itis.nationalbankru.repositories")
public class FlyWayConfig implements WebMvcConfigurer {

    @Value("${SPRING_DATASOURCE_URL}")
    String DB_URL;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    String DB_USERNAME;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    String DB_PASSWORD;

    // Доступ к ресурсам через web
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DB_URL != null ? DB_URL : "jdbc:postgresql://localhost:5432/bankru");
        dataSource.setUsername(DB_USERNAME != null ? DB_USERNAME : "postgres");
        dataSource.setPassword(DB_PASSWORD != null ? DB_PASSWORD : "post");
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(dataSource);
        flyway.setTable("rs_schema_version");
        flyway.setSchemas("public");
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // none, update
        jpaProperties.setProperty("hibernate.show_sql", "true"); // ddl-auto
        em.setJpaProperties(jpaProperties);
        em.setPersistenceUnitName("test");
        em.setPackagesToScan("ru.itis.nationalbankru.entity");

        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
