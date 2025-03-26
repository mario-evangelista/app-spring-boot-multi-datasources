package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

// Anotação que marca esta classe como a principal aplicação Spring Boot
// Ela habilita a auto-configuração, o escaneamento de componentes e a configuração adicional
@SpringBootApplication
public class Application {

    // Método principal que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        // Classe SpringApplication é usada para iniciar uma aplicação Spring
        // O método run inicia o contexto do Spring e a aplicação
        SpringApplication.run(Application.class, args);
    }

    // Define um Bean do tipo ApplicationRunner
    // ApplicationRunner é uma interface que permite executar código após o contexto da aplicação ser iniciado
    @Bean
    ApplicationRunner runner(
            // Injeta um JdbcClient chamado 'postsJdbcClient'. Este bean é configurado em DataSourceConfig para o primeiro banco de dados.
            JdbcClient postsJdbcClient,
            // Injeta outro JdbcClient chamado 'commentsJdbcClient'. A anotação @Qualifier é usada para especificar qual bean injetar quando há múltiplos beans do mesmo tipo.
            @Qualifier("commentsJdbcClient") JdbcClient commentsJdbcClient) {
        // Retorna uma implementação funcional (lambda) da interface ApplicationRunner
        return args -> {
            // Executa uma consulta no primeiro banco de dados (configurado para 'posts')
            // Seleciona todos os registros da tabela 'posts' e os mapeia para objetos da classe Post
            System.out.println(postsJdbcClient.sql("SELECT * FROM posts").query(Post.class).list());

            // Executa uma consulta no segundo banco de dados (configurado para 'comments')
            // Seleciona todos os registros da tabela 'comments' e os mapeia para objetos da classe Comment
            System.out.println(commentsJdbcClient.sql("SELECT * FROM comments").query(Comment.class).list());
        };
    }

}