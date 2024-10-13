# Projeto de API de Votação

Este projeto é uma API RESTful para gerenciar candidatos, eleitores e realizar votações. A API permite CRUD (criação, leitura, atualização e exclusão) de candidatos, eleitores e cargos. Além disso, é possível registrar votos e obter relatórios de votação com base nos cargos.

## Funcionalidades

- **CRUD para Candidatos**: Gerenciamento candidatos através dos endpoints da API.
- **CRUD para Eleitores**: Gerenciamento eleitores e seus documentos de identificação.
- **CRUD para Cargos**: Gerenciamento cargos como "Presidente", "Prefeito", etc.
- **Registrar Voto**: Um eleitor pode votar em um candidato, com validação para impedir múltiplos votos para o mesmo cargo.
- **Relatório de Votos**: Obtenha o vencedor de cada cargo com o número total de votos.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.0**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL** 
- **Liquibase** (para versionamento do banco de dados)
- **Swagger/OpenAPI** 
- **Maven** 

## Requisitos para Execução

- **Java 17+**: Certifique-se de que o Java 17 está instalado.
- **PostgreSQL**: O banco de dados PostgreSQL deve estar rodando localmente ou em um servidor.
- **Maven**: Necessário para construir e executar o projeto.

### Dependências

As dependências principais estão definidas no arquivo `pom.xml`, incluindo:
- Spring Boot
- Spring Data JPA
- PostgreSQL driver
- HikariCP para conexão com banco de dados
- Liquibase para versionamento do banco

### Configurações do Banco de Dados

No arquivo `application.properties` , é necessário configurar os dados de acesso ao banco de dados.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/elections
spring.datasource.username=postgres
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.liquibase.change-log=classpath:/db/changelog/db.changelog-master.yaml
```
- Preste atenção em 'spring.jpa.hivernate.ddl-auto', pois se estiver em update, ele atualizará automaticamente o banco de dados, baseado no seu código. para não atualizar, coloque 'none'
### Passos para Execução

1. **Clone o Repositório**
   ```bash
   git clone https://github.com/patocoding/chall-backend-lifters.git
   cd projeto-votacao
   ```

2. **Configure o Postgre**

3. **Rode o Projeto com Maven**
    ```
    mvn clean install
    mvn spring-boot:run
    ```
4. **Documentação da API**
   ```
   http://localhost:8080/swagger-ui.html
   ```

### Principais endpoints
  **Candidatos**
- GET /api/candidatos: Lista todos os candidatos.
- POST /api/candidatos: Cria um novo candidato.
- GET /api/candidatos/{id}: Retorna um candidato por ID.
- PUT /api/candidatos/{id}: Atualiza os dados de um candidato.
- DELETE /api/candidatos/{id}: Remove um candidato.


  **Eleitores**
- GET /api/voters: Lista todos os eleitores.
 - POST /api/voters: Cria um novo eleitor.
 - GET /api/voters/{id}: Retorna um eleitor por ID.
 - PUT /api/voters/{id}: Atualiza um eleitor.
 - DELETE /api/voters/{id}: Exclui um eleitor.


**Votação**
- POST /api/voters/{id}/vote: Registra um voto de um eleitor para um candidato.
- Body da requisição:
    ```
    {
      "candidateId": 1
    }
    ```

**Relatório de votação**

- GET /api/candidatos/report: Retorna um relatório com o número de votos por cargo e o candidato vencedor de cada um.

### Melhorias Futuras

- Implementação de autenticação e autorização para proteger a votação.
- Otimização de consultas com cache.
- Implementar filas para lidar com grandes volumes de votos simultâneos.

```
madeBy: {
name: "Cayo Alves",
message: "Thank You Lifters <3"
}
```