# Aplicação Spring Boot com Múltiplos Data Sources

Este projeto demonstra como configurar e utilizar múltiplos data sources (fontes de dados) em uma aplicação Spring Boot. Ele configura duas conexões separadas com bancos de dados SQL Server, simulando um cenário com um banco de dados on-premise e outro na AWS.

## Estrutura do Projeto

A estrutura principal do projeto é a seguinte:

* **`pom.xml`**: Arquivo de configuração do Maven, contendo as dependências e configurações de build do projeto.
* **`src/main/java/com/example/Application.java`**: Classe principal que inicia a aplicação Spring Boot e demonstra o uso dos múltiplos data sources.
* **`src/main/resources/application.properties`**: Arquivo de configuração da aplicação, contendo as informações de conexão com os bancos de dados.
* **`src/main/java/com/example/DataSourceConfig.java`**: Classe de configuração responsável por definir os beans de DataSource e JdbcClient para cada banco de dados.
* **`src/main/java/com/example/Comment.java`**: Classe record que representa a entidade de comentário.
* **`src/main/java/com/example/Post.java`**: Classe record que representa a entidade de post.
* **`src/main/resources/posts-schema.sql`** (opcional): Arquivo SQL para inicializar o esquema do banco de dados de posts.
* **`src/main/resources/comments-schema.sql`** (opcional): Arquivo SQL para inicializar o esquema do banco de dados de comentários.

## Tecnologias Utilizadas

* Java 21
* Spring Boot 3.4.4
* Maven
* SQL Server

## Pré-requisitos

* Java Development Kit (JDK) 17 ou superior instalado.
* Maven instalado.
* Uma instância do SQL Server disponível para as configurações on-premise e AWS (podem ser instâncias locais para teste).

## Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/mario-evangelista/app-spring-boot-multi-datasources.git
    cd app-spring-boot-multi-datasources
    ```

2.  **Configure as informações de conexão com os bancos de dados:**
    Abra o arquivo `src/main/resources/application.properties` e atualize as seguintes propriedades com as suas credenciais:

    ```properties
    # Configurações do Banco de Dados On-Premise
    app.datasource.onpremise.url=jdbc:sqlserver://localhost;databaseName=databaseName;encrypt=true;trustServerCertificate=true
    app.datasource.onpremise.username=username
    app.datasource.onpremise.password=password

    # Configurações do Banco de Dados AWS
    app.datasource.aws.url=jdbc:sqlserver://localhost:1433;databaseName=databaseName;encrypt=true;trustServerCertificate=true
    app.datasource.aws.username=username
    app.datasource.aws.password=password
    ```

    Certifique-se de substituir `localhost`, `databaseName`, `username` e `password` pelos valores corretos para seus bancos de dados.

3.  **(Opcional) Crie os arquivos de esquema SQL:**
    Se você deseja que o Spring Boot inicialize o esquema dos seus bancos de dados, crie os arquivos `src/main/resources/posts-schema.sql` e `src/main/resources/comments-schema.sql` com os comandos SQL para criar as tabelas `posts` e `comments`, respectivamente. Por exemplo:

    **`src/main/resources/posts-schema.sql`:**
    ```sql
    CREATE TABLE IF NOT EXISTS posts (
        id BIGINT IDENTITY PRIMARY KEY,
        text VARCHAR(255)
    );
    ```

    **`src/main/resources/comments-schema.sql`:**
    ```sql
    CREATE TABLE IF NOT EXISTS comments (
        id BIGINT IDENTITY PRIMARY KEY,
        post_id BIGINT,
        text VARCHAR(255),
        FOREIGN KEY (post_id) REFERENCES posts(id)
    );
    ```

## Executando a Aplicação

Para executar a aplicação Spring Boot, utilize o seguinte comando no diretório raiz do projeto:

```bash
mvn spring-boot:run
