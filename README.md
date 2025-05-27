# Sistema de Gestão de Funcionários API

API RESTful desenvolvida com Java e Spring Boot para realizar o gerenciamento completo de funcionários de uma organização. Este projeto visa fornecer endpoints robustos e bem documentados para operações de CRUD (Create, Read, Update, Delete) e funcionalidades futuras relacionadas a RH.

---

##  Funcionalidades Implementadas (Atualmente)

* **Gerenciamento de Funcionários:**
    * Cadastro de novos funcionários com dados pessoais, de endereço e profissionais.
    * Listagem de todos os funcionários cadastrados.
    * Busca de um funcionário específico pelo seu ID.
    * Atualização dos dados de um funcionário existente.
    * Remoção (deleção) de um funcionário.
* **Validação de Dados:** Validação dos dados de entrada para garantir a integridade e o formato correto.
* **Tratamento de Exceções:** Respostas de erro padronizadas para exceções comuns (ex: recurso não encontrado, dados inválidos).
* **Documentação da API:** Documentação interativa gerada automaticamente com Springdoc OpenAPI (Swagger UI).

---

##  Tecnologias Utilizadas

* **Backend:**
    * Java (Versão do seu JDK, ex: 17, 21 ou 24)
    * Spring Boot (Versão do seu projeto, ex: 3.4.5)
    * Spring Web (para APIs RESTful)
    * Spring Data JPA (para persistência de dados)
    * Spring Security (para segurança da API - configuração básica inicial)
    * Spring Boot Validation (para validação de DTOs)
    * Hibernate (como implementação JPA)
* **Banco de Dados:**
    * MySQL 8.0 (rodando via Docker)
* **Build & Gerenciamento de Dependências:**
    * Apache Maven
* **Documentação da API:**
    * Springdoc OpenAPI (Swagger UI)
* **Containerização (para o banco de dados):**
    * Docker
* **Outros:**
    * Lombok (para reduzir código boilerplate)

---

##  Pré-requisitos

Antes de começar, garanta que você tem instalado:

* Java JDK (Versão compatível com o projeto, ex: 17+)
* Apache Maven (Versão 3.6+)
* Docker Desktop ou Docker Engine (para rodar o MySQL)
* Uma IDE de sua preferência (ex: IntelliJ IDEA, Eclipse, VS Code)
* Um cliente API como Postman (opcional, pode usar Swagger UI)

---

##  Como Configurar e Rodar o Projeto

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/MaccedoFilho/Gestao-de-Funcionarios.git](https://github.com/MaccedoFilho/Gestao-de-Funcionarios.git)
    cd Gestao-de-Funcionarios
    ```

2.  **Configure e Inicie o Banco de Dados MySQL com Docker:**
    No seu terminal, execute o comando abaixo para iniciar um container MySQL. Lembre-se de substituir `SUA_SENHA_AQUI` pela senha que você deseja usar.
    ```bash
    docker run -d \
        --name mysql-gestao-funcionarios \
        -p 3306:3306 \
        -e MYSQL_ROOT_PASSWORD=SUA_SENHA_AQUI \
        -e MYSQL_DATABASE=sistema_rh_db \
        -v gestao_funcionarios_mysql_data:/var/lib/mysql \
        mysql:8.0
    ```
    * `-d`: Roda em segundo plano.
    * `--name`: Nome do container.
    * `-p`: Mapeia a porta (host:container).
    * `-e MYSQL_ROOT_PASSWORD`: **Defina a senha do root do MySQL.**
    * `-e MYSQL_DATABASE`: Cria o banco de dados automaticamente.
    * `-v`: Cria um volume para persistir os dados do MySQL.
    * `mysql:8.0`: Imagem e versão do MySQL.

3.  **Configure a Aplicação (`application.properties`):**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Certifique-se de que as propriedades de conexão com o banco de dados estão corretas e que a senha corresponde à que você definiu no comando Docker:
        ```properties
        spring.application.name=Gestao-de-funcionarios

        spring.datasource.url=jdbc:mysql://localhost:3306/sistema_rh_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
        spring.datasource.username=root
        spring.datasource.password=SUA_SENHA_AQUI # <--- Use a mesma senha do Docker

        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

        springdoc.swagger-ui.path=/swagger-ui.html
        springdoc.api-docs.path=/v3/api-docs

        logging.level.org.springframework.security=DEBUG # Opcional, para debug de segurança
        logging.level.org.springframework.web=DEBUG    # Opcional, para debug web
        ```

4.  **Execute a Aplicação:**
    * **Pelo Maven (terminal, na raiz do projeto):**
        ```bash
        mvn spring-boot:run
        ```
    * **Pela sua IDE:** Encontre a classe principal `GestaoDeFuncionariosApplication.java` e execute-a.

    A aplicação deve iniciar na porta `8080` (padrão).

---

## 
🌐 Acessando a API

* **URL Base da API:** `http://localhost:8080/api/v1`
* **Endpoints de Funcionários:** `http://localhost:8080/api/v1/funcionarios`

### 📖 Documentação da API (Swagger UI)

Com a aplicação rodando, acesse a documentação interativa da API no seu navegador:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Você poderá ver todos os endpoints disponíveis, seus parâmetros, e testá-los diretamente pela interface do Swagger.

---

##  Próximos Passos (Funcionalidades Futuras Planejadas)

* Gerenciamento de Histórico Profissional dos Funcionários.
* Gerenciamento de Departamentos e Hierarquia Organizacional.
* Controle de Ponto e Monitoramento de Produtividade.
* Geração de Relatórios detalhados sobre Desempenho e Recursos Humanos.
* Refinamento da segurança com autenticação baseada em JWT e autorizações por papéis (roles).

---
