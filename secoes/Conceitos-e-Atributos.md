## 📝 Seção 2: Conceitos e Atributos

<p align="center">
  <em>Definição da estrutura estática do domínio: identificação de entidades coesas e suas propriedades alfanuméricas.</em>
</p>

---

### **1. Modelo Conceitual: Definição e Escopo Técnico**

O Modelo Conceitual (MC) é uma visão do Modelo de Domínio em nível de **Análise**. Sua principal característica é a vinculação ao **escopo do problema**, mantendo-se agnóstico à implementação técnica.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/modelo-conceitual.png" alt="Modelo Conceitual" width="100%">
</div>

- **Foco:** Mapeamento da estrutura de dados que o sistema deverá gerenciar.
- **Abstração:** Independente de paradigma (OO ou Relacional) e de tecnologia (Linguagens ou SGBDs).
- **Ferramenta:** Utiliza-se o **Diagrama de Classes da UML (Unified Modeling Language)** para a representação gráfica.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/descreve.png" alt="Modelo Conceitual Descreve" width="100%">
</div>

---

### **2. Abstração de Conceitos (Entidades de Domínio)**

Um conceito é uma abstração de uma unidade coesa do mundo real que possui relevância para o negócio e exige persistência de dados.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/conceitos.png" alt="Conceitos" width="100%">
</div>

- **Identificação:** Geralmente extraídos de substantivos em documentos de requisitos, casos de uso ou entrevistas (Ex: `Pedido`, não `Pedidos`).
- **Representação em Camadas:** Um retângulo dividido (diagrama UML) em seções. No nível conceitual, foca-se nas duas primeiras:
  1. **Cabeçalho (Nome do Conceito):** Substantivo no singular, grafado em _UpperCamelCase_.
  2. **Corpo (Seção de Atributos):** Lista de propriedades do conceito (Atributos e suas tipagens).
  3. **Base (Seção de Métodos):** _Não utilizada_ no Modelo Conceitual Operações/Métodos (pertence ao nível de Design).

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/diagrama-de-classes-uml.png" alt="diagrama de classes da UML para 
representar conceitos e atributos" width="100%">
</div>

---

### **3. Atributos: Propriedades e Tipagem**

Atributos descrevem as características intrínsecas de um conceito. Devem seguir a **Primeira Forma Normal (1FN)**, evitando estruturas compostas ou coleções multivaloradas no nível atômico.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/atributos.png" alt="Atributos" width="100%">
</div>

#### **Sintaxe e Especificação UML**

A declaração formal segue o padrão: `visibilidade nome: tipo = valor_inicial`.

<table border="2" align="center" style="border-collapse: collapse; text-align:left; font-family: sans-serif;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th width="25%">Elemento</th>
      <th width="75%">Descrição Técnica</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Visibilidade</b></td>
      <td>Embora opcional no MC, utiliza-se <code>-</code> (privado) ou <code>+</code> (público).</td>
    </tr>
    <tr>
      <td><b>Nome</b></td>
      <td>Grafado em <i>lowerCamelCase</i>. Deve ser autoexplicativo (Ex: <code>dataVencimento</code>).</td>
    </tr>
    <tr>
      <td><b>Tipo</b></td>
      <td>Define a natureza do dado (Ex: String, Integer, Double, Date, Boolean).</td>
    </tr>
    <tr>
      <td><b>Estereótipo <<oid>></b></td>
      <td>Identifica o atributo como <b>Object Identifier</b> (identificador único do conceito).</td>
    </tr>
    <tr>
      <td><b>Derivação (/)</b></td>
      <td>Atributos calculados a partir de outros (Ex: <code>/valorLiquido</code>). São <i>read-only</i>.</td>
    </tr>
  </tbody>
</table>

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/classes-uml-representar-conceitos-e-atributos.png" alt=" diagrama de classes da UML para 
representar conceitos e atributos" width="100%">
</div>

---

### **4. Fontes de Extração de Dados**

A identificação de conceitos não é arbitrária; ela baseia-se em artefatos produzidos durante a Engenharia de Requisitos e no conhecimento dos especialistas de negócio.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/onde-buscar-informacoes.png" alt="Fontes de Informação" width="100%">
</div>

### **5. Análise da Visão Geral do Sistema**

O documento de Visão Geral fornece o contexto macro do projeto, permitindo identificar as grandes entidades e o escopo principal do domínio.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/visao-geral-do-sistema.png" alt="Exemplo de Visão Geral" width="100%">
</div>

### **6. Especificação por Casos de Uso**

Casos de uso detalham a interação entre atores e o sistema. Eles são fundamentais para descobrir conceitos que surgem apenas durante a execução de processos específicos.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/caso-de-uso.png" alt="Exemplo de Caso de Uso" width="100%">
</div>

---

### **7. Metodologia para Identificação de Conceitos**

Para uma extração eficiente, o analista deve aplicar técnicas de análise léxica nos documentos disponíveis, focando em substantivos e objetos de valor.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/1orientacoes-para-identificacao-conceitos.png" alt="Metodologia de Identificação" width="100%">
</div>

> [!IMPORTANT]
> **Heurística de Extração:** O Analista deve realizar uma "mineração" nos requisitos, buscando por substantivos (potenciais conceitos), adjetivos (potenciais estados ou atributos) e verbos que sugiram a criação de registros ou transações.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/2orientacoes-para-identificacao-conceitos.png" alt="Destaques na Análise Léxica" width="100%">
</div>

---

### **8. Estudos de Caso e Refinamento**

Abaixo, observamos a aplicação prática das técnicas de extração em diferentes níveis de complexidade.

#### **Cenário A: Tradução de Requisitos em Modelos Estáticos**

Exemplo de como uma descrição textual da Visão Geral do Sistema é convertida em um esboço inicial de classes.

<div align="center">
  <img src="/secoes/assets/img/modelo-conceitual/1exemplo-visao-geral.png" alt="Tradução de Visão Geral" width="100%">
</div>

#### **Cenário B: Identificação via Fluxo de Eventos**

Análise de um caso de uso para compra de livros, onde o fluxo de interação revela as necessidades de dados do sistema.

<div align="center">
  <img src="/secoes/assets/img/modelo-conceitual/2exemplo-caso-de-uso.png" alt="Fluxo de Caso de Uso" width="100%">
</div>

#### **Cenário C: Mapeamento Léxico Grifado**

Técnica de destaque visual sobre o caso de uso para isolar substantivos que se tornarão classes no modelo final.

<div align="center">
  <img src="/secoes/assets/img/modelo-conceitual/3exemplo-caso-de-uso-conceitos-grifados.png" alt="Análise Léxica Grifada" width="100%">
</div>

#### **Cenário D: Refinamento e Decomposição**

O primeiro esboço raramente é final. É necessário refinar as entidades para garantir coesão e eliminar redundâncias ou conceitos que pertencem apenas à interface.

<div align="center">
  <img src="/secoes/assets/img/modelo-conceitual/4exemplo-entidades-requer-refinamento.png" alt="Refinamento de Modelo" width="100%">
</div>

---

> [!IMPORTANT]
> **A Regra de Ouro da Pureza Conceitual:** > Jamais utilize atributos para representar chaves estrangeiras (FKs) no Modelo Conceitual. Relações entre conceitos são expressas unicamente por **Associações** (conectores visuais, ex: linhas), mantendo o modelo limpo e focado na semântica do negócio.

---

### 🎯 **5. Recapitulação Técnica: A Arte de Isolar e Estruturar Conceitos**

Nesta etapa, a modelagem deixa de ser uma visão panorâmica e passa a focar na granularidade das informações. A identificação precisa de **Conceitos** e **Atributos** é o que garante que o sistema seja uma representação fiel, coesa e eficiente do negócio.

#### **5.1. Matriz de Componentes Estruturais**

| Componente | Definição Técnica | Critério de Inclusão |
| :--- | :--- | :--- |
| **Conceito (Classe)** | Unidade coesa que representa algo com significado real para o domínio. | Deve possuir necessidade de armazenamento e identidade própria (substantivos do negócio). |
| **Atributo** | Dado alfanumérico simples que qualifica e descreve um conceito. | Deve ser **atômico** (cumprindo a regra de não ser composto nem multivalorado). |
| **Identificador (OID)** | Atributo ou conjunto que garante a distinção única entre instâncias. | Essencial para garantir a identidade do objeto e a integridade referencial futura. |



#### **5.2. Insights de Engenharia e Prática Profissional**

* **Agnosticismo de Implementação e Escopo do Problema:** Reforcei a compreensão de que o modelo conceitual pertence estritamente ao **escopo do problema**. O foco reside na estrutura lógica e na **regra**, e não no código. A estrutura deve ser válida independentemente de o dado ser persistido em SQL, NoSQL ou memória.
* **Normalização Precoce (Conformidade com 1FN):** Na engenharia de software, aplicamos restrições da **Primeira Forma Normal (1FN)** já na modelagem. Atributos não podem ser listas ou objetos compostos; caso um atributo apresente essa complexidade, ele é "promovido" a um novo **Conceito**, garantindo a atomicidade dos dados.
* **Análise Léxica como Heurística de Design:** Aprendi a extrair informações de documentos de requisitos (Visão Geral e Casos de Uso) através da identificação sistemática de substantivos. Essa técnica reduz a subjetividade, evitando que detalhes de interface poluam o modelo de domínio.
* **Semântica, Tipagem e Derivação:** A utilização da sintaxe UML (`nome : tipo`) e a identificação de **atributos derivados** (valores calculados) funcionam como uma "planta arquitetônica" universal. Isso evita redundâncias desnecessárias e define claramente o que deve ser armazenado versus o que deve ser processado em tempo de execução.
* **Processo Iterativo e Refinamento:** A modelagem não é estática. O primeiro diagrama valida o entendimento inicial, mas é através do refinamento constante que eliminamos redundâncias e alcançamos a elegância estrutural necessária para sistemas escaláveis.

> [!IMPORTANT]
> **Conclusão Técnica:** A qualidade de um sistema começa na precisão de seus dados. Um conceito mal isolado ou um atributo mal posicionado gera dívida técnica e inconsistência. Esta base sólida de propriedades atômicas é o alicerce indispensável para a próxima etapa: a conexão das entidades.

---

Consolidada a anatomia das entidades, avançaremos agora para a exploração de como esses conceitos se comunicam e dependem uns dos outros através das **Associações e Multiplicidades**.

<p align="center">
  <b>Próximo Nível: 👉 </b> <a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/Associacoes-e-Multiplicidades.md">Seção 3 - Associações e Multiplicidades</a>
</p>
