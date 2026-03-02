# Library API - Sistema de Gerenciamento de Biblioteca

> ⚠️ **Status do Projeto**: Este projeto está em fase de desenvolvimento e ainda não foi finalizado. Novas funcionalidades e melhorias estão sendo implementadas gradualmente.

## Descrição do Projeto

A **Library API** é uma solução backend desenvolvida com **Spring Boot 3** para o gerenciamento completo de uma biblioteca. A API permite o controle de autores e livros, oferecendo funcionalidades de cadastro, consulta, atualização e exclusão (CRUD), além de recursos avançados de pesquisa e validação de dados.

O projeto demonstra a aplicação de conceitos modernos de desenvolvimento Java, como mapeamento DTO, tratamento global de exceções e persistência de dados com JPA/Hibernate.

## Tecnologias Utilizadas

*   **Java 21**: Linguagem base utilizando os recursos mais recentes.
*   **Spring Boot 4.0.2**: Framework para construção da API REST.
*   **Spring Data JPA**: Abstração para persistência de dados.
*   **PostgreSQL**: Banco de dados relacional para armazenamento de informações.
*   **MapStruct**: Para conversão automática e performática entre Entidades e DTOs.
*   **Lombok**: Para redução de código repetitivo.
*   **Bean Validation**: Para validação de regras de negócio nos dados de entrada.
*   **Maven**: Automação de build e gerenciamento de dependências.

## Funcionalidades (Em Desenvolvimento)

*   **Gestão de Autores**:
    *   Cadastro de autores com validação de dados.
    *   Consulta detalhada e listagem de autores.
    *   Atualização de informações e exclusão lógica/física.
*   **Gestão de Livros**:
    *   Cadastro de livros vinculados a autores existentes.
    *   Suporte a diferentes gêneros literários (Enum).
    *   Pesquisa avançada de livros por diversos critérios.
*   **Infraestrutura e Segurança**:
    *   Configuração de banco de dados via Docker (PostgreSQL).
    *   Tratamento de erros padronizado com mensagens claras para o cliente da API.
    *   Validações customizadas para evitar registros duplicados e operações inválidas.

## Como Executar

### Pré-requisitos

*   JDK 21.
*   Maven.
*   Docker e Docker Compose (opcional, para o banco de dados).

### Passos para Instalação

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/ArthurSilvagbs/LibraryApi.git
    cd LibraryApi
    ```

2.  **Suba o banco de dados (via Docker):**

    Existem comandos prontos no arquivo `comandos-docker.txt` para facilitar a criação do container PostgreSQL.

3.  **Configure o `application.yml`:**

    Certifique-se de que as credenciais do banco em `src/main/resources/application.yml` estão corretas para o seu ambiente.

4.  **Inicie a aplicação:**

    ```bash
    ./mvnw spring-boot:run
    ```

## Estrutura do Projeto

O código está organizado seguindo as melhores práticas de pacotes:

*   `config`: Configurações gerais da aplicação e banco de dados.
*   `controller`: Camada de exposição dos endpoints REST e DTOs.
*   `model`: Entidades que representam o domínio do sistema.
*   `repository`: Interfaces para comunicação com o banco de dados.
*   `service`: Camada de serviços com a lógica de negócio.
*   `validator`: Componentes de validação customizada.

