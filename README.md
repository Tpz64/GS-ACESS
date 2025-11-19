# 🔑 ACESS (Accessible Competency Enhancement System & Support)

## 📝 Descrição do Projeto

O projeto **A.C.E.S.S. (Accessible Competency Enhancement System & Support)** é uma plataforma integrada de requalificação digital inclusiva, concebida para enfrentar a nova barreira de exclusão criada pela Quarta Revolução Industrial e pela automação.

### O Desafio Central e a Visão

O problema central que o A.C.E.S.S. busca resolver é o fato de que as novas ferramentas e plataformas de treinamento, exigidas pela requalificação digital, não são projetadas para incluir Pessoas com Deficiência (PCDs).

A **Visão A.C.E.S.S.** inverte a lógica: em vez de forçar o trabalhador a se adaptar a ferramentas inacessíveis, a plataforma adapta o trabalho ao trabalhador. O objetivo é tornar tarefas complexas acessíveis em tempo real, promovendo autonomia e acesso a cargos técnicos de maior qualificação.

-----

## 👥 Integrantes

  * Preencha com os nomes dos membros da equipe:
      * André Neves - RM 553515
      * Thaís Leoncio - RM 553892
-----

## 💻 Tecnologias e Dependências

Este projeto foi construído usando o ecossistema Spring Boot para a API RESTful.

| Tecnologia | Versão | Descrição |
| :--- | :--- | :--- |
| **Java** | 21+ | Linguagem principal do projeto. |
| **Spring Boot** | 3.x | Framework para simplificar a criação da API. |
| **Spring Web** | - | Para construção dos *endpoints* RESTful. |
| **Spring Data JPA** | - | Para persistência de dados (H2, PostgreSQL). |
| **Lombok** | - | Para reduzir o código *boilerplate* (getters/setters/construtores). |
| **WebClient** | - | Cliente HTTP reativo e não bloqueante para consumo da API ViaCEP. |

-----

## 🛠️ Configuração e Instalação

Siga os passos abaixo para configurar e rodar o projeto localmente.

### Pré-requisitos

1.  **JDK (Java Development Kit)**: Versão 21 ou superior.
2.  **Maven**: Ferramenta de gerenciamento de dependências.
3.  **IDE**: IntelliJ IDEA, Eclipse ou VS Code.
4.  **Postman/cURL**: Para testar os *endpoints*.

### Passos para Execução

1.  **Clone o Repositório:**
    ⚠️ **ATENÇÃO:** Substitua o placeholder abaixo pela URL real do seu repositório Git.

    ```bash
    git clone [INSIRA A URL REAL DO SEU REPOSITÓRIO GIT AQUI]
    cd nome-do-projeto
    ```

2.  **Configuração do Banco de Dados:**

      * Este projeto utiliza um banco de dados **H2** em memória por padrão (ideal para desenvolvimento).
      * Para persistência em produção, ajuste as configurações no `application.properties`.

3.  **Compilar e Rodar o Projeto:**

      * Utilizando o Maven:
        ```bash
        # Compila e baixa as dependências
        mvn clean install
        # Roda a aplicação
        mvn spring-boot:run
        ```
      * Alternativamente, inicie a classe principal (`Application.java`) diretamente pela sua IDE.

4.  **Verificação:**

      * A aplicação estará disponível em: `http://localhost:8080`.

-----

## 🌐 Módulos e Integração ViaCEP

### Estrutura de Comunicação Externa

O projeto utiliza o **Spring WebClient** para realizar a comunicação com o serviço **ViaCEP**.

  * **Cliente:** `ViaCepClient.java` (Responsável por construir a requisição e tratar a resposta da API externa).
  * **Serviço:** `FuncionarioService.java` (Orquestra a regra de negócio: chama o `ViaCepClient` e, com os dados de endereço, persiste o `Funcionario`).

### Tratamento de Erros

A lógica de tratamento de erros garante a robustez do sistema, especialmente na integração:

1.  **Erro de Negócio (CEP Inválido):** Se a ViaCEP retorna o *payload* `{"erro": true}`, o `FuncionarioService` lança a **`CepNotFoundException`**.
2.  **Mapeamento de Status:** A classe **`GlobalExceptionHandler`** intercepta a `CepNotFoundException` e a mapeia para o *status* HTTP **`400 Bad Request`**, garantindo uma resposta amigável ao usuário.

-----

## ⚙️ Endpoints da API (CRUD)

O mapeamento básico de recursos para gerenciamento de funcionários é feito sob a rota `/v1/funcionarios`.

| Método | Endpoint | Descrição | Status de Sucesso |
| :--- | :--- | :--- | :--- |
| **`POST`** | `/v1/funcionarios` | Cria um novo funcionário, validando e buscando o endereço via ViaCEP. | `201 Created` |
| **`GET`** | `/v1/funcionarios/{id}` | Busca um funcionário específico pelo ID. | `200 OK` |
| **`GET`** | `/v1/funcionarios` | Lista todos os funcionários registrados. | `200 OK` |
| **`PUT`** | `/v1/funcionarios/{id}` | Atualiza os dados de um funcionário existente (realizando nova consulta CEP se o campo for alterado). | `200 OK` |
| **`DELETE`** | `/v1/funcionarios/{id}` | Remove um funcionário do banco de dados. | `204 No Content` |

-----

 
