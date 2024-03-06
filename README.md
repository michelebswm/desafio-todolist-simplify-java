## Desafio sistema de gerenciamento de tarefas (To-Do List)

API para gerenciamento de tarefas (CRUD) que faz parte do [desafio](https://github.com/simplify-tec/desafio-junior-backend-simplify) para pessoas desenvolvedoras backend júnior, que se candidatam para a Simplify.

### Descrição
Desenvolva uma aplicação web utilizando uma linguagem de programação e um framework de sua escolha. A aplicação deve consistir em um sistema de gerenciamento de tarefas, onde os usuários podem criar, visualizar, editar e excluir tarefas.

### Requisitos
- Usar banco de dados
- Campos mínimos da entidade de tarefa 
  - Nome
  - Descrição
  - Realizado
  - Prioridade
- Criar CRUD de tarefas

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)

## Práticas adotadas

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/michelebswm/desafio-todolist-simplify-java.git 
```
2. Instale as dependências do Maven.

## Uso

1. Inicie o aplicativo com Maven

2. A API estará acessível em http://localhost:8080

3. O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints
A API fornece os seguintes endpoints:

**API TASK**
```markdown
GET /api/tasks/{id} - Recupera uma task
PUT /api/tasks/{id} - Altera uma task
DELETE /api/tasks/{id} - Exclui uma task
POST /api/tasks - Cria uma task
GET /api/tasks - Recupera todas as tasks
GET /api/tasks/titlesearch - Recupera as tasks com parte do título informado.
GET /api/tasks/priority - Recupera as tasks com priority especificada
GET /api/tasks/completed - recupera as tasks completas ou em aberto 
```

**BODY**
```json
{
  "title": "string",
  "description": "string",
  "priority": "string",
  "taskStatus": "string",
  "done": true
}
```

**ENUMS**

priority:
- Este enum representa a prioridade da task
- Os valores possíveis são: HIGH, AVERAGE, LOW 

taskStatus:
- Este enum representa o status da task
- Os valores possíveis são: TODO, DOING, DONE, CANCELLED 
