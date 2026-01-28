package io.github.arthursilva.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); //maximo de conexão liberadas
        config.setMinimumIdle(1); //tamanho incial do poll (conexões abertar logo quandoa apliação começa a rodar)
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); //600mil milisegundos, depois de 10min ela morre e é criada uma nova
        config.setConnectionTimeout(60000);
        config.setConnectionTestQuery("select 1"); //querry de teste pra ver se a coneção ta ok

        return new HikariDataSource(config);
    }


}
