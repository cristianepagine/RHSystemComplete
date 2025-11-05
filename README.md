# Sistema de Recursos Humanos (RH System)

Sistema completo de Recursos Humanos desenvolvido com **Java Spring Boot** no backend e **Angular** no frontend.

## Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot 3.2.0
- Spring Security com JWT
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger/OpenAPI

### Frontend
- Angular 17
- Angular Material
- TypeScript
- RxJS

## Funcionalidades

- Autenticação e autorização com JWT
- Gestão de funcionários (CRUD completo)
- Gestão de departamentos e cargos
- Registro de ponto
- Solicitação e aprovação de férias
- Dashboard com métricas principais
- Controle de acesso por roles (ADMIN, RH, GESTOR, FUNCIONARIO)

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- Java 21 ou superior
- Node.js 18+ e npm
- PostgreSQL 14+
- Maven 3.8+
- Angular CLI 17+

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE rhsystem_db;
```

2. Configure as credenciais no arquivo `backend/src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rhsystem_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Executando o Backend

1. Navegue até o diretório do backend:

```bash
cd backend
```

2. Compile o projeto:

```bash
mvn clean install
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

Ou use o JAR gerado:

```bash
java -jar target/rh-system-1.0.0.jar
```

O backend estará disponível em: `http://localhost:8080`

### Documentação da API (Swagger)

Acesse a documentação interativa da API em:
```
http://localhost:8080/swagger-ui/index.html
```

## Executando o Frontend

1. Navegue até o diretório do frontend:

```bash
cd frontend
```

2. Instale as dependências:

```bash
npm install
```

3. Execute o servidor de desenvolvimento:

```bash
npm start
```

Ou:

```bash
ng serve
```

O frontend estará disponível em: `http://localhost:4200`

## Estrutura do Projeto

### Backend

```
backend/
├── src/main/java/com/rhsystem/
│   ├── config/          # Configurações (Security, Swagger, etc)
│   ├── controller/      # Controllers REST
│   ├── dto/            # Data Transfer Objects
│   ├── exception/      # Exception handlers
│   ├── model/          # Entidades JPA
│   ├── repository/     # Repositories Spring Data
│   ├── security/       # JWT e configurações de segurança
│   └── service/        # Camada de serviços
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   └── application-prod.properties
└── pom.xml
```

### Frontend

```
frontend/
├── src/app/
│   ├── core/
│   │   ├── guards/        # Guards de autenticação
│   │   ├── interceptors/  # Interceptors HTTP
│   │   └── services/      # Services HTTP
│   ├── models/            # Interfaces TypeScript
│   ├── modules/           # Módulos da aplicação
│   │   ├── auth/         # Login
│   │   ├── dashboard/    # Dashboard principal
│   │   ├── funcionarios/ # Gestão de funcionários
│   │   ├── ferias/       # Gestão de férias
│   │   └── ponto/        # Registro de ponto
│   └── shared/           # Componentes compartilhados
├── angular.json
├── package.json
└── tsconfig.json
```

## Usuários Padrão

Após a primeira execução, você precisará criar usuários no banco de dados. Use os seguintes scripts SQL como exemplo:

```sql
-- Senha: admin123 (use BCrypt para hash)
INSERT INTO usuarios (username, password, email, role, ativo)
VALUES ('admin', '$2a$10$...', 'admin@rhsystem.com', 'ADMIN', true);

-- Senha: rh123
INSERT INTO usuarios (username, password, email, role, ativo)
VALUES ('rh', '$2a$10$...', 'rh@rhsystem.com', 'RH', true);
```

## Endpoints Principais

### Autenticação
- `POST /api/auth/login` - Login

### Funcionários
- `GET /api/funcionarios` - Listar todos
- `GET /api/funcionarios/{id}` - Buscar por ID
- `POST /api/funcionarios` - Criar novo
- `PUT /api/funcionarios/{id}` - Atualizar
- `DELETE /api/funcionarios/{id}` - Deletar

### Férias
- `GET /api/ferias` - Listar todas
- `GET /api/ferias/funcionario/{id}` - Buscar por funcionário
- `POST /api/ferias` - Solicitar férias
- `PUT /api/ferias/{id}/aprovar` - Aprovar
- `PUT /api/ferias/{id}/rejeitar` - Rejeitar

### Ponto
- `GET /api/registros-ponto/funcionario/{id}` - Listar registros
- `POST /api/registros-ponto` - Registrar ponto

### Dashboard
- `GET /api/dashboard` - Obter dados do dashboard

## Profiles

### Development
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production
```bash
java -jar target/rh-system-1.0.0.jar --spring.profiles.active=prod
```

## Build para Produção

### Backend
```bash
cd backend
mvn clean package -DskipTests
```

### Frontend
```bash
cd frontend
npm run build
```

Os arquivos de produção estarão em `frontend/dist/rh-system-frontend/`

## Segurança

- Autenticação via JWT (Bearer Token)
- Tokens com validade de 24 horas
- Senhas criptografadas com BCrypt
- CORS configurado para `http://localhost:4200`
- Autorização baseada em roles

## Roles e Permissões

- **ADMIN**: Acesso total ao sistema
- **RH**: Gestão de funcionários, férias e ponto
- **GESTOR**: Aprovação de férias, visualização de relatórios
- **FUNCIONARIO**: Registro de ponto, solicitação de férias

## Troubleshooting

### Erro de conexão com o banco de dados
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais no `application-dev.properties`
- Certifique-se de que o banco `rhsystem_db` foi criado

### Erro CORS no frontend
- Verifique se o backend está configurado para aceitar requisições de `http://localhost:4200`
- Confirme que o `SecurityConfig` tem a configuração CORS correta

### Token JWT expirado
- O token tem validade de 24 horas
- Faça login novamente para obter um novo token

## Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT.

## Suporte

Para suporte, envie um email para contato@rhsystem.com ou abra uma issue no GitHub.
