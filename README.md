<h2 align="center">📊 Modelagem de Dados UML</h2>
<p align="center"><i>Análise & Projeto Orientado a Objetos: Do Conceitual ao Físico</i></p>
 
[![UML Diagram](https://img.shields.io/badge/UML-Diagram-blue?style=flat-square&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDAgMTAwIj48Y2lyY2xlIGN4PSI1MCIgY3k9IjUwIiByPSI0MCIgc3Ryb2tlPSIjMDAyMjg4IiBzdHJva2Utd2lkdGg9IjIiIGZpbGw9IiNmZmYiLz48L3N2Zz4=)](https://www.uml.org/)

<br>

Este repositório contém os estudos e implementações focados em **Modelagem Conceitual**, progredindo da abstração de negócios até a implementação técnica com `Java` e `Spring Boot`.

---

### 🗺️ Mapa de Navegação da Base de Conhecimento

Abaixo estão listados os módulos fundamentais da modelagem conceitual, organizados de forma incremental, desde a abstração teórica até a implementação técnica.

<table border="2" align="center" style="border-collapse: collapse; text-align:center; font-family: sans-serif;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">CONTEÚDO PROGRAMÁTICO E REFERÊNCIAS</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th width="10%">Módulo</th>
      <th width="65%">Ementa Técnica (Tópicos Principais)</th>
      <th width="25%">Documentação</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>01</b></td>
      <td><b>Introdução:</b> Fundamentos da modelagem de domínio, níveis de abstração e distinção entre Análise e Design.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/01-Introducao-Modelagem-Dominio.md">📄 Acessar Seção</a></td>
    </tr>
    <tr>
      <td><b>02</b></td>
      <td><b>Conceitos e Atributos:</b> Identificação de entidades de domínio, sintaxe de atributos e tipagem conceitual.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/02-Conceitos-e-Atributos.md">📄 Acessar Seção</a></td>
    </tr>
    <tr>
      <td><b>03</b></td>
      <td><b>Associações e Multiplicidades:</b> Conectividade entre objetos, cardinalidades (1..*, 0..1, *) e papéis nas relações.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/03-Associacoes-e-Multiplicidades.md">📄 Acessar Seção</a></td>
    </tr>
    <tr>
      <td><b>04</b></td>
      <td><b>Todo-Parte e Classes de Associação:</b> Semântica de Agregação, Composição e resolução de relações N:N.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/04-Todo-Parte-e-Classe-Associacao.md">📄 Acessar Seção</a></td>
    </tr>
    <tr>
      <td><b>05</b></td>
      <td><b>Herança e Tipos:</b> Generalização/Especialização, Enumerações e tratamento de tipos primitivos em modelos OO.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/05-Heranca-e-Generalizacao.md">📄 Acessar Seção</a></td>
    </tr>
    <tr>
      <td><b>06</b></td>
      <td><b>Estudo de Caso:</b> Transição do Modelo Conceitual para o Modelo Físico com Java, Spring Boot e JPA/Hibernate.</td>
      <td><a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/06-Estudo-de-Caso.md">📄 Acessar Seção</a></td>
    </tr>
  </tbody>
</table>

---

### 🛣️ Roadmap de Evolução: Além da Modelagem

A conclusão deste curso (Seção 07) marca o fim da teoria base, mas o início da jornada arquitetural. Abaixo estão os pilares recomendados para aprofundar os conhecimentos adquiridos neste repositório:

#### **1. Design Patterns (Padrões de Projeto)**

Agora que você domina classes e associações, o próximo passo é aprender soluções consagradas para problemas recorrentes.

- **Foco:** Estudar padrões como _Strategy_, _Observer_ e _Factory_ para tornar seus modelos UML mais flexíveis e menos acoplados.

#### **2. Domain-Driven Design (DDD)**

Aprenda a lidar com a complexidade de grandes sistemas dividindo o modelo conceitual em contextos delimitados.

- **Foco:** Entender a distinção entre _Entities_, _Value Objects_ e _Aggregates_ dentro de um modelo de domínio real.

#### **3. Clean Architecture e Princípios SOLID**

Aplique regras de design que garantem que sua implementação Java seja testável e fácil de manter.

- **Foco:** Especial atenção ao **LSP (Liskov Substitution Principle)**, que valida tecnicamente se sua hierarquia de Herança (Seção 05) está correta.

#### **4. Otimização de Persistência (JPA/Hibernate)**

Traduzir o modelo para o banco é apenas o começo; o próximo nível é a performance.

- **Foco:** Estudar estratégias de carregamento (_Lazy vs Eager_) e resolver problemas de performance em associações complexas.

---

> [!CHECKLIST]
> **Finalizou os estudos?** Confira as [**📄 Considerações Finais e Próximos Passos**](https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/07-Conclusao.md) para consolidar seu aprendizado.

---

### 🛠 Ferramentas Utilizadas:

- Modelagem: (Draw.io, Astah UML, Lucidchart)
- Backend: Java 17+, Spring Boot, JPA/Hibernate
- Banco de Dados: H2 / PostgreSQL

---

### 🎓 Reconhecimento e Créditos

Gostaria de expressar meu agradecimento ao **Professor Nelio Alves** pelo excelente curso [**Modelagem de Dados UML (Análise & Projeto Orientado a Objetos)**](https://www.udemy.com/course/uml-diagrama-de-classes/).

> [!IMPORTANT]
> **Nota de Retificação:** As imagens e diagramas utilizados neste repositório foram retirados do material didático do curso mencionado acima. Este projeto tem fins de estudo e fixação de conteúdo, e recomendo fortemente a matrícula para quem deseja se aprofundar no tema.

**Principais aprendizados consolidados:**

- **Modelo de Domínio:** Compreensão do modelo conceitual como o alicerce para descrever as informações que o sistema gerencia, indo além do tradicional modelo Entidade-Relacionamento.
- **Paradigma Orientado a Objetos vs. Estruturado:** Transição do paradigma estruturado para soluções mais elegantes e flexíveis utilizando a **UML (Unified Modeling Language)** e o Diagrama de Classes.
- **Domínio Técnico em UML:** Modelagem de conceitos, atributos, associações, multiplicidades, agregação, composição, herança e classes de associação.
- **Implementação Prática (Mundo Real):** Aplicação de **Mapeamento Objeto-Relacional (ORM)** com **Java, Spring Boot e JPA/Hibernate**, transformando abstrações conceituais em bancos de dados relacionais automáticos e APIs REST funcionais.

Este curso elevou meu grau de compreensão sobre a disciplina de análise de sistemas, permitindo uma ponte sólida entre os requisitos de negócio e a codificação de alto nível.

---

| 🌎 LinkedIn                                                              | 👨‍💻 **Autor**                                                                 |
| ------------------------------------------------------------------------ | ---------------------------------------------------------------------------- |
| [LinkedIn](https://www.linkedin.com/in/albert-backend-java-spring-boot/) | [Albert Silva](https://www.linkedin.com/in/albert-backend-java-spring-boot/) |
