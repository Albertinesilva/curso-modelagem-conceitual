## 🏗️ Seção 6: Estudo de Caso — Implementação do Modelo Conceitual com Java, Spring Boot e JPA

> **Objetivo:** Demonstrar, de forma técnica e aplicada, como um Modelo Conceitual completo é traduzido para uma aplicação RESTful utilizando Java, Spring Boot e JPA/Hibernate, seguindo padrões arquiteturais modernos.

Nesta etapa crucial do curso, abandonamos o campo puramente teórico para observar como o **Modelo Conceitual** dita o comportamento de uma aplicação real de mercado. O foco aqui é a transição entre a abstração (Diagramas) e a construção técnica utilizando o ecossistema **Java**, **Spring Boot** e **JPA/Hibernate**.

---

### 💻 Pré-requisitos Técnicos

Para a execução deste estudo de caso, são necessários os seguintes conhecimentos e ferramentas:

| Categoria       | Requisito                                                                      |
| :-------------- | :----------------------------------------------------------------------------- |
| **Ambiente**    | Computador (Mac, Linux ou Windows) com capacidade de instalação/terminal.      |
| **Linguagem**   | Conhecimento básico em POO (Classes, Atributos, Encapsulamento, Construtores). |
| **Ferramentas** | Java (JDK), Spring Boot e conhecimento desejável em Git.                       |

---

### 🗺️ O Modelo de Referência

Para este cenário, utilizaremos um modelo que engloba todos os desafios aprendidos nas seções anteriores. A implementação cobrirá desde a leitura dos diagramas até a persistência dos dados.

#### **1. Diagrama de Classes (Visão Estrutural)**

O diagrama de classes abaixo serve como a "planta" do sistema, definindo as regras de negócio, multiplicidades e os tipos de associações (direcionadas ou não).

<img src="/secoes/assets/img/estudo-de-caso/diagrama-classes-completo.png" alt="Diagrama de Classes Completo" width="100%" />

#### **2. Diagrama de Objetos (Visão de Instância)**

Para validar o modelo, utilizamos o diagrama de objetos para representar um cenário real de execução, demonstrando como os objetos se relacionam e trocam informações em tempo de execução.

<img src="/secoes/assets/img/estudo-de-caso/diagrama-objetos-exemplo.png" alt="Diagrama de Objetos" width="100%" />

---

### 📌 Visão Geral do Projeto

Este módulo representa a transição definitiva da **modelagem conceitual** para a **implementação orientada a objetos com persistência relacional**.

O projeto implementa um sistema de domínio completo contendo:

- Entidades com diferentes tipos de associações
- Herança e polimorfismo
- Enumerações persistidas
- Entidades fracas
- Classes de associação
- Chaves primárias compostas
- API REST padronizada
- Tratamento global de exceções
- Boas práticas com Spring Boot 3+

---

### 🎯 Objetivos de Aprendizado

Demonstrar na prática como um modelo conceitual abrangente é traduzido para o paradigma orientado a objetos, aplicando padrões de arquitetura e boas práticas de desenvolvimento. O estudo visa consolidar o entendimento sobre:

- **Implementação de Associações**: De 1:1 até N:N com tabelas de junção.
- **Mapeamento de Herança e Enumerações**: Como o banco de dados lida com hierarquias e tipos enumerados.
- **Conceitos Avançados**: Tratamento de entidades fracas (`@ElementCollection`) e chaves compostas (Tipos Primitivos como `ItemPedidoPK`).

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
Controller → Service → Repository → Banco de Dados
```

### 🔹 Camadas

### Controller

- Exposição de endpoints REST
- Conversão JSON ↔ Objetos
- Uso de `ResponseEntity`

### Service

- Regras de negócio
- Orquestração de operações
- Lançamento de exceções de domínio

### Repository

- Interface `JpaRepository`
- Operações CRUD automáticas

---

### 🛠️ Tecnologias Utilizadas

| Tecnologia      | Finalidade                    |
| --------------- | ----------------------------- |
| Java 17+        | Linguagem principal           |
| Spring Boot 3+  | Framework principal           |
| Spring Data JPA | Persistência                  |
| Hibernate       | Implementação ORM             |
| H2 / MySQL      | Banco de dados                |
| Maven           | Gerenciamento de dependências |
| Postman         | Testes de API                 |
| Git             | Controle de versão            |

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
Controller → Service → Repository → Banco
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

# 🛒 Pedido (Exemplo com Classe de Associação)

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

### 🎯 Recapitulação: O Ciclo da Modelagem

Este estudo de caso fecha o ciclo de aprendizado ao provar que **um bom código nasce de um bom modelo**. Ao dominar a leitura dos diagramas e a sua tradução para JPA, o desenvolvedor garante que o software seja:

1.  **Fiel ao Negócio**: Refletindo exatamente os requisitos levantados.
2.  **Escalável**: Através de associações bem definidas e tipos primitivos corretos.
3.  **Padronizado**: Seguindo as melhores práticas da indústria (Spring/JPA).

---

### 🏁 Conclusão Técnica

Este projeto consolida o ciclo completo:

```
Modelo Conceitual → Modelo Orientado a Objetos → Modelo Relacional → API REST
```

O aprendizado obtido garante que:

- O código reflita fielmente o domínio
- A persistência preserve integridade
- A API seja padronizada e previsível
- A arquitetura seja escalável
- O sistema esteja alinhado às boas práticas de mercado

---

✅ **Seção 06 Concluída**

Este estudo de caso representa a consolidação prática da modelagem conceitual aplicada ao desenvolvimento backend moderno com Spring Boot.

---

### 🔗 Navegação

<p align="center">
  <b>Seção 6 concluída ✔</b><br>
  <a href="05-Heranca-e-Generalizacao.md">⬅ Seção 5 - Herança e Tipos (Generalização)</a> |
  <a href="../README.md">🏠 Início</a> |
  <a href="07-Conclusao.md">Seção 7 - Considerações Finais ➡</a>
</p>
