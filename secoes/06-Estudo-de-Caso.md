## 🏗️ Seção 6: Estudo de Caso — Implementação do Modelo Conceitual com Java, Spring Boot e JPA

> **Objetivo:** Demonstrar, de forma técnica e aplicada, como um Modelo Conceitual completo é traduzido para uma aplicação RESTful utilizando Java, Spring Boot e JPA/Hibernate, seguindo padrões arquiteturais modernos.

Esta seção é baseada no módulo de Estudo de Caso desenvolvido no curso de **Modelagem Conceitual com UML**, ministrado pelo professor **Nélio Alves**.

Aqui, apresento a consolidação prática do aprendizado adquirido no curso, juntamente com evoluções técnicas que implementei para aprofundar a aplicação em um contexto mais próximo de projetos reais de mercado.

Nesta etapa, deixamos o campo puramente teórico para observar como o **Modelo Conceitual** orienta diretamente o comportamento de uma aplicação real. O foco é compreender a transição entre a abstração representada nos diagramas e sua materialização técnica utilizando o ecossistema **Java**, **Spring Boot**, **JPA/Hibernate** e **Flyway**.

Mais do que apenas “codificar entidades”, este estudo de caso demonstra como decisões tomadas no nível conceitual impactam:

- A estrutura do código
- O modelo relacional gerado
- A integridade dos dados
- A organização arquitetural da aplicação
- O contrato exposto pela API REST

Além da implementação proposta no curso, foram aplicadas as seguintes evoluções técnicas:

- Introdução de **DTOs (Data Transfer Objects)** organizados por contexto
- Uso de **mappers** para isolamento entre domínio e camada de API
- Estrutura inspirada em princípios básicos de **DDD (Domain-Driven Design)**, especialmente na organização do agregado `Pedido`
- Utilização de **Flyway** para versionamento de banco de dados
- Atualização da aplicação para **Spring Boot 4.0.2**
- Uso de **PostgreSQL** como banco principal e **H2** para ambiente de desenvolvimento/teste
- Validações com `spring-boot-starter-validation`

---

### 💻 Pré-requisitos Técnicos

Para acompanhar a implementação com clareza técnica, recomenda-se que o leitor possua os seguintes conhecimentos e ferramentas:

| Categoria       | Requisito                                                              |
| :-------------- | :--------------------------------------------------------------------- |
| **Ambiente**    | Computador (Mac, Linux ou Windows) com suporte a terminal e IDE.       |
| **Linguagem**   | Fundamentos de POO (Classes, Atributos, Encapsulamento, Construtores). |
| **Ferramentas** | Java 17+, Maven, Spring Boot e noções básicas de Git.                  |

---

### ⚙️ Stack Tecnológica do Projeto

A aplicação foi construída com base na seguinte stack:

| Categoria                 | Tecnologia / Ferramenta     | Finalidade                                                                 |
| ------------------------- | --------------------------- | -------------------------------------------------------------------------- |
| **Framework Base**        | Spring Boot 4.0.2           | Bootstrap da aplicação, auto-configuração e gerenciamento do ciclo de vida |
| **Linguagem**             | Java 17                     | Plataforma principal da aplicação                                          |
| **Documentação JavaDocs** | Java                        | A aplicação possui documentação em JavaDocs para referência                |
| **Web Layer**             | Spring Web MVC              | Construção da API REST e tratamento de requisições HTTP                    |
| **Persistência**          | Spring Data JPA + Hibernate | Mapeamento ORM e abstração de acesso a dados                               |
| **Banco Produção**        | PostgreSQL                  | Banco de dados relacional principal em ambiente produtivo                  |
| **Banco Dev/Teste**       | H2 Database                 | Banco em memória para desenvolvimento e testes locais                      |
| **Migrações**             | Flyway                      | Versionamento e controle evolutivo do schema do banco de dados             |
| **Validação**             | Bean Validation (Jakarta)   | Validação declarativa de dados via anotações                               |
| **Ferramentas Dev**       | Spring Boot DevTools        | Hot reload e otimização do fluxo de desenvolvimento                        |
| **Testes**                | Spring Boot Test            | Suporte a testes unitários e de integração                                 |
| **Build Tool**            | Maven                       | Gerenciamento de dependências e ciclo de build do projeto                  |
| **Testes de API**         | Postman                     | Execução e validação manual de requisições HTTP                            |
| **Controle de Versão**    | Git                         | Versionamento de código e gerenciamento de histórico de alterações         |

Essa configuração permite:

- Desenvolvimento local rápido com H2
- Ambiente produtivo com PostgreSQL
- Versionamento seguro do schema via Flyway
- Persistência orientada a domínio com JPA/Hibernate
- API REST padronizada com Spring MVC

---

### 🗺️ O Modelo de Referência

O cenário adotado nesta seção é exatamente o modelo trabalhado ao longo do curso, cobrindo:

- Associações simples e complexas
- Herança
- Enumerações
- Entidades fracas
- Classes de associação
- Chaves compostas

A implementação parte da leitura do modelo conceitual e percorre todo o ciclo até a persistência relacional e exposição via API.

---

#### 1️⃣ Diagrama de Classes (Visão Estrutural)

O diagrama de classes funciona como a **planta arquitetural do domínio**. Ele define:

- Entidades
- Atributos
- Multiplicidades
- Tipos de associações
- Hierarquias (Generalização/Especialização)

<img src="/secoes/assets/img/estudo-de-caso/diagrama-classes-completo.png" alt="Diagrama de Classes Completo" width="100%" />

Esse diagrama é a principal fonte de verdade para a implementação das entidades JPA.

---

#### 2️⃣ Diagrama de Objetos (Visão de Instância)

Enquanto o diagrama de classes representa a estrutura estática do sistema, o diagrama de objetos valida o comportamento em tempo de execução.

Ele demonstra:

- Instâncias reais das entidades
- Valores atribuídos aos atributos
- Relações efetivamente estabelecidas entre objetos

<img src="/secoes/assets/img/estudo-de-caso/diagrama-objetos-exemplo.png" alt="Diagrama de Objetos" width="100%" />

Essa visualização auxilia na compreensão do fluxo de dados que será persistido no banco relacional.

---

### 📌 Visão Geral do Projeto

Este módulo representa a transição definitiva da **modelagem conceitual** para a **implementação orientada a objetos com persistência relacional**.

O sistema desenvolvido segue a proposta original do curso e foi expandido com melhorias arquiteturais. Ele contempla:

- Entidades com diferentes tipos de associações
- Herança e polimorfismo persistido
- Enumerações convertidas para tipos compatíveis com banco relacional
- Entidades fracas
- Classes de associação
- Chaves primárias compostas
- API REST padronizada
- Tratamento global de exceções
- Estrutura com DTOs e mapeadores
- Versionamento de banco com Flyway
- Organização inspirada em DDD

A proposta é evidenciar que o modelo conceitual não é apenas documentação, mas a base estrutural que orienta decisões técnicas em todas as camadas da aplicação.

---

### 🎯 Objetivos de Aprendizado

Este estudo de caso demonstra, na prática, como um modelo conceitual abrangente é traduzido para o paradigma orientado a objetos, preservando regras de negócio e integridade estrutural no modelo relacional.

Serão consolidados os seguintes pontos técnicos:

- **Implementação de Associações:** mapeamento de relacionamentos 1:1, 1:N e N:N, incluindo tabelas de junção e classes de associação.
- **Mapeamento de Herança e Enumerações:** estratégias de persistência de hierarquias e conversão de tipos enumerados.
- **Conceitos Avançados de Persistência:** tratamento de entidades fracas (`@ElementCollection`) e modelagem de chaves compostas com classes `@Embeddable` (ex.: `ItemPedidoPK`).
- **Separação Arquitetural:** aplicação do padrão em camadas (Controller → Service → Repository).
- **Evolução Arquitetural:** introdução de DTOs, validação e versionamento de banco com Flyway.

Ao concluir esta seção, o desenvolvedor consolida os seguintes conhecimentos:

### 1️⃣ Tradução do Modelo Conceitual para Código

- Conversão de classes do diagrama em entidades JPA
- Implementação correta das multiplicidades
- Resolução de dependências bidirecionais
- Aplicação de encapsulamento orientado a domínio

---

### 2️⃣ Implementação de Associações

A implementação prática explorará os seguintes pilares da modelagem moderna:

- **Associações**: Implementação de `OneToMany`, `ManyToOne`, `OneToOne` e `ManyToMany`.
- **Classes de Associação**: Como transformar uma relação complexa em uma entidade funcional.
- **Herança**: Estratégias de mapeamento de superclasses e subclasses para o banco relacional.
- **Entidades Fracas**: Uso de `@ElementCollection` para coleções de tipos básicos ou embutidos.
- **Tipos Primitivos Customizados**: Uso de chaves primárias compostas para itens de pedido.

| Tipo de Associação   | Implementação JPA                   |
| -------------------- | ----------------------------------- |
| 1:1                  | `@OneToOne`                         |
| 1:N                  | `@OneToMany` + `@ManyToOne`         |
| N:N                  | `@ManyToMany`                       |
| Classe de Associação | Entidade própria com chave composta |

---

### 3️⃣ Herança no Banco Relacional

Aplicação de:

```java
@Inheritance(strategy = InheritanceType.JOINED)
```

Permite:

- Separação por tabelas
- Integridade relacional
- Polimorfismo persistido

---

### 4️⃣ Enumerações Persistidas

Enums convertidos para tipo inteiro usando:

```java
@Enumerated(EnumType.ORDINAL)
```

Com métodos auxiliares para conversão segura.

---

### 5️⃣ Entidades Fracas

Uso de:

```java
@ElementCollection
```

Para representar:

- Telefones
- Coleções de tipos simples
- Objetos embutidos

---

### 6️⃣ Chaves Primárias Compostas

Implementação via:

- Classe `@Embeddable`
- `@EmbeddedId`
- Implementação de `equals()` e `hashCode()`

Exemplo: `ItemPedidoPK`

---

### 🏛️ Arquitetura Aplicada

O projeto segue uma arquitetura em camadas:

```
Controller → DTO → Service → Repository → Banco de Dados
```

### 🔹 Camadas

### Controller

- Exposição de endpoints REST
- Conversão JSON ↔ Objetos
- Uso de `ResponseEntity`
- Recebe e envia **DTOs** para separar a camada de apresentação das entidades de domínio

### DTO (Data Transfer Object)

- Objetos simples para transporte de dados entre Controller e Service
- Contêm apenas os campos necessários para requisição ou resposta
- Evitam expor diretamente entidades do domínio
- Facilita validação, mapeamento e manutenção da API

### Service

- Regras de negócio
- Orquestração de operações
- Mapeamento de DTOs para entidades e vice-versa
- Lançamento de exceções de domínio

### Repository

- Interface `JpaRepository`
- Operações CRUD automáticas
- Trabalha diretamente com entidades de domínio

---

### 🗂️ Estrutura do Projeto

A estrutura foi organizada seguindo princípios de **arquitetura em camadas**, separação de responsabilidades e segmentação por **contexto de domínio**.

O projeto adota:

- Organização por feature (categoria, cliente, pedido)
- Separação clara entre domínio, DTOs e camada de aplicação
- Tratamento centralizado de exceções
- Configurações isoladas
- Estrutura preparada para evolução e escalabilidade

---

### 📦 Estrutura de Diretórios

```
src/main/java/com/albertsilva/cursomc
│
├── config
│   └── SpringTimezoneConfig.java
│
├── domain
│
├── dto
│   ├── categoria
│   │   ├── mapper
│   │   ├── request
│   │   └── response
│   │
│   ├── cliente
│   │   ├── mapper
│   │   ├── request
│   │   └── response
│   │
│   └── pedido
│       ├── mapper
│       ├── request
│       ├── response
│       └── update
│
├── repositories
│
├── resources
│   ├── exception
│   ├── CategoriaResource.java
│   ├── ClienteResource.java
│   └── PedidoResource.java
│
├── services
│   ├── exceptions
│   ├── CategoriaService.java
│   ├── ClienteService.java
│   └── PedidoService.java
│
└── CursomcApplication.java
```

---

### 📂 resources (Configurações e Infraestrutura)

```
src/main/resources
│
├── db
│   ├── data
│   ├── migration
│   └── test
│
├── application.properties
├── application-dev.properties
└── application-test.properties
```

---

### 🔎 Detalhamento das Camadas

### 🔹 `config`

Responsável por configurações globais da aplicação.

Exemplo:

- `SpringTimezoneConfig.java`  
  Configuração do fuso horário padrão da aplicação.

---

### 🔹 `domain`

Contém o **modelo de domínio**:

- Entidades JPA
- Enumerações
- Classes de associação
- Classes `@Embeddable`
- Regras estruturais do modelo

Esta camada não deve depender de DTOs ou Controllers.

---

### 🔹 `dto`

Organizado por contexto (feature-based structure):

Cada entidade possui:

- `request` → Entrada de dados da API
- `response` → Saída padronizada da API
- `mapper` → Conversão entre Entidade ↔ DTO
- `update` → DTO específico para operações parciais (quando aplicável)

Essa abordagem:

- Evita exposição direta das entidades
- Permite versionamento de contrato
- Isola modelo de domínio da API

---

### 🔹 `repositories`

Interfaces que estendem `JpaRepository`.

Responsáveis exclusivamente pela persistência.

Nenhuma regra de negócio deve existir aqui.

---

### 🔹 `services`

Camada de aplicação responsável por:

- Regras de negócio
- Orquestração transacional
- Validações
- Lançamento de exceções de domínio

Subpacote:

- `exceptions` → Exceções customizadas da camada de serviço

---

### 🔹 `resources`

Camada de entrada da aplicação (Controllers REST).

Responsável por:

- Mapear endpoints
- Receber DTOs
- Delegar para a camada de serviço
- Retornar `ResponseEntity`

Subpacote:

- `exception` → Tratamento global via `@RestControllerAdvice`

---

### 🧱 Organização Arquitetural Aplicada

A estrutura combina dois padrões:

### 1️⃣ Arquitetura em Camadas

```
Controller → DTO → Service → Repository → Banco de Dados
```

### 2️⃣ Organização por Contexto de Domínio (Feature-based)

Cada agregado (Categoria, Cliente, Pedido) possui seus próprios:

- DTOs
- Mappers
- Contratos de entrada e saída

---

### 🎯 Benefícios da Estrutura

✔ Separação clara de responsabilidades  
✔ Baixo acoplamento  
✔ Alta coesão  
✔ Facilidade de manutenção  
✔ Facilidade para escalar novas features  
✔ Contrato de API desacoplado do modelo de domínio  
✔ Estrutura pronta para migração futura para arquitetura mais modular (ex: Hexagonal)

---

### 🌐 Endpoints da API

### 🔹 Categoria

### 🔸 Buscar todas

`GET /categorias`

**Response 200**

```json
[
  {
    "id": 1,
    "nome": "Informática"
  }
]
```

---

### 🔸 Buscar por ID

`GET /categorias/{id}`

**Response 200**

```json
{
  "id": 1,
  "nome": "Informática"
}
```

---

### 🔸 Criar

`POST /categorias`

**Request**

```json
{
  "nome": "Eletrodomésticos"
}
```

**Response 201**

```json
{
  "id": 3,
  "nome": "Eletrodomésticos"
}
```

---

### 🔸 Atualizar

`PUT /categorias/{id}`

**Request**

```json
{
  "nome": "Tecnologia"
}
```

**Response 204**

Sem corpo.

---

### 🔸 Deletar

`DELETE /categorias/{id}`

**Response 204**

---

### 🛒 Pedido (Exemplo com Classe de Associação)

### Criar Pedido

`POST /pedidos`

**Request**

```json
{
  "cliente": { "id": 1 },
  "enderecoDeEntrega": { "id": 1 },
  "pagamento": {
    "tipo": 1,
    "estado": 1
  },
  "itens": [
    {
      "produto": { "id": 1 },
      "quantidade": 2,
      "desconto": 0.0,
      "preco": 2000.0
    }
  ]
}
```

**Response 201**

```json
{
  "id": 1,
  "instante": "2026-02-22T21:00:00Z",
  "cliente": {
    "id": 1,
    "nome": "Maria Silva"
  },
  "valorTotal": 4000.0
}
```

---

### ⚠️ Tratamento Global de Exceções

Implementação com:

```java
@RestControllerAdvice
```

### Exceções Implementadas

| Exceção                 | Status HTTP |
| ----------------------- | ----------- |
| ObjectNotFoundException | 404         |
| DataIntegrityException  | 409         |

---

### 🔹 Exemplo de Erro 404

```json
{
  "timestamp": "2026-02-22T21:10:00Z",
  "status": 404,
  "error": "Objeto não encontrado",
  "message": "Categoria não encontrada! Id: 100",
  "path": "/categorias/100"
}
```

---

### 🔄 Fluxo Completo de Requisição

1. Cliente envia JSON via Postman
2. Controller recebe via `@RequestBody`
3. Service valida e executa regra
4. Repository persiste
5. JPA converte para SQL
6. Banco executa
7. Response é retornado como JSON

---

# 🧪 Como Testar no Postman

### 1️⃣ Criar nova requisição

- Método: `POST`
- URL: `http://localhost:8080/categorias`

### 2️⃣ Headers

```
Content-Type: application/json
```

### 3️⃣ Body → Raw → JSON

Inserir o JSON correspondente.

---

### 📚 Conceitos de Modelagem Aplicados

✔ Associação bidirecional  
✔ Associação unidirecional  
✔ Herança (Generalização/Especialização)  
✔ Classe de associação  
✔ Entidade fraca  
✔ Chave composta  
✔ Enum persistido  
✔ Integridade referencial

---

### 🏁 Conclusão Técnica

O estudo de caso apresentado consolida o ciclo completo de desenvolvimento backend moderno, demonstrando como a **modelagem conceitual** se traduz em software funcional e de qualidade.

🔄 **Fluxo percorrido:**

Esta abordagem garante que cada camada da aplicação seja **coerente com o domínio do negócio** e siga boas práticas técnicas reconhecidas:

---

### 1️⃣ Fidelidade ao Negócio

- Diagramas conceituais traduzidos para **entidades JPA**, mantendo associações, cardinalidades e restrições de negócio.
- Regras de negócio refletidas no **Service**, garantindo comportamento consistente com os requisitos levantados.

### 2️⃣ Separação de Responsabilidades e Escalabilidade

- Arquitetura em camadas: **Controller → DTO → Service → Repository → Banco de Dados**, promovendo **desacoplamento** e manutenibilidade.
- **DTOs** isolam o domínio de detalhes de implementação, transmitindo apenas os dados necessários e permitindo adaptações futuras sem impacto no restante da aplicação.

### 3️⃣ Padronização e Boas Práticas

- Uso consistente de **Spring Boot, Spring Data JPA e Bean Validation** garante padronização no ciclo de vida da aplicação, validação de dados e persistência.
- **ResponseEntity** e tratamento estruturado de exceções tornam a API REST previsível e confiável.
- **Maven, Flyway e Git** reforçam práticas profissionais de engenharia de software.

### 4️⃣ Integração entre Modelos

- Tradução do modelo conceitual → objetos → modelo relacional, garantindo integridade e coerência dos dados persistidos.
- Integração **Service ↔ Repository** com mapeamento de entidades assegura que a persistência de dados preserve **integridade e consistência**.

### 5️⃣ Benefícios do Estudo

- **Fiel ao domínio:** reflete exatamente os requisitos levantados.
- **Escalável:** associações bem definidas e arquitetura modular.
- **Previsível e padronizada:** APIs REST claras e consistentes.
- **De fácil manutenção:** alterações futuras podem ser realizadas sem comprometer a base do sistema.

---

✅ **Resumo Final**

Este estudo de caso evidencia que **um bom modelo gera um bom código**.  
Ao dominar a interpretação de diagramas e a tradução para **JPA e APIs REST**, o desenvolvedor constrói sistemas:

- **Robustos**
- **Escaláveis**
- **Alinhados às boas práticas de mercado**

Consolidando competências essenciais em **desenvolvimento backend com Spring Boot**.

---

### 🔗 Navegação

<p align="center">
  <b>Seção 6 concluída ✔</b><br>
  <a href="05-Heranca-e-Generalizacao.md">⬅ Seção 5 - Herança e Tipos (Generalização)</a> |
  <a href="../README.md">🏠 Início</a> |
  <a href="07-Conclusao.md">Seção 7 - Considerações Finais ➡</a>
</p>
