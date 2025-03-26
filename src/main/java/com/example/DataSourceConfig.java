package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;
import java.util.List;

// Anotação que indica que esta classe contém definições de beans (configurações) para o Spring
@Configuration
public class DataSourceConfig {

    // Configuração para o primeiro banco de dados (on-premise)
    // A anotação @Primary indica que este é o DataSource padrão se nenhum outro for especificado
    @Primary
    // A anotação @ConfigurationProperties associa as propriedades com o prefixo "app.datasource.onpremise"
    @ConfigurationProperties(prefix = "app.datasource.onpremise")
    // Define um bean do tipo DataSourceProperties para o primeiro banco de dados
    @Bean
    DataSourceProperties postDsProps() {
        return new DataSourceProperties();
    }

    @Primary
    // Define um bean do tipo DataSource para o primeiro banco de dados
    // Utiliza as propriedades configuradas em postDsProps para construir o DataSource
    @Bean
    DataSource postDs(DataSourceProperties dsProps) {
        return dsProps.initializeDataSourceBuilder().build();
    }

    @Primary
    // Define um bean do tipo JdbcClient para interagir com o primeiro banco de dados
    // JdbcClient é uma maneira mais simples de executar operações JDBC com o Spring
    @Bean
    JdbcClient postsJdbcClient(DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }

    @Primary
    // Define um bean para inicializar o esquema do banco de dados para o primeiro banco de dados
    @Bean
    DataSourceScriptDatabaseInitializer postsDbInit(DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        // Define os locais dos arquivos de script SQL para inicializar o esquema
        settings.setSchemaLocations(List.of("classpath:posts-schema.sql"));
        // Define o modo de inicialização para sempre executar os scripts
        settings.setMode(DatabaseInitializationMode.ALWAYS);
        // Cria e retorna o inicializador do banco de dados
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }

    // Configuração para o segundo banco de dados (AWS)
    // A anotação @ConfigurationProperties associa as propriedades com o prefixo "app.datasource.aws"
    @ConfigurationProperties(prefix = "app.datasource.aws")
    // Define um bean do tipo DataSourceProperties para o segundo banco de dados
    @Bean
    DataSourceProperties commentsDsProps() {
        return new DataSourceProperties();
    }

    // Define um bean do tipo DataSource para o segundo banco de dados
    // A anotação @Qualifier("commentsDsProps") é usada para especificar qual DataSourceProperties usar
    @Bean
    DataSource commentsDs(@Qualifier("commentsDsProps") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    // Define um bean do tipo JdbcClient para interagir com o segundo banco de dados
    // A anotação @Qualifier("commentsDs") é usada para especificar qual DataSource usar
    @Bean
    JdbcClient commentsJdbcClient(@Qualifier("commentsDs") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }

    // Define um bean para inicializar o esquema do banco de dados para o segundo banco de dados
    @Bean
    DataSourceScriptDatabaseInitializer commentsDbInit(@Qualifier("commentsDs") DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        // Define os locais dos arquivos de script SQL para inicializar o esquema
        settings.setSchemaLocations(List.of("classpath:comments-schema.sql"));
        // Define o modo de inicialização para sempre executar os scripts
        settings.setMode(DatabaseInitializationMode.ALWAYS);
        // Cria e retorna o inicializador do banco de dados
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }

}