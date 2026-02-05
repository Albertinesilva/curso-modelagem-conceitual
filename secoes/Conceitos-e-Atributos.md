## 📊 Seção 2: Conceitos e Atributos

<p align="center">
  <em>Definição da estrutura estática do domínio: identificação de unidades coesas e suas propriedades alfanuméricas.</em>
</p>

---

### **1. Modelo Conceitual: Definição e Escopo**

O Modelo Conceitual (MC) é uma visão do Modelo de Domínio em nível de **Análise**. Sua principal característica é a vinculação ao **escopo do problema**, mantendo-se agnóstico à implementação técnica.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/modelo-conceitual.png" alt="Modelo Conceitual" width="100%">
</div>

* **Foco:** Descrever a estrutura das informações que o sistema gerenciará.
* **Abstração:** Independente de paradigma (OO ou Relacional) e de tecnologia (Linguagens ou SGBDs).
* **Ferramenta:** Utiliza-se o **Diagrama de Classes da UML** para a representação gráfica.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/descreve.png" alt="Modelo Conceitual Descreve" width="100%">
</div>

---
### **2. Conceitos (Entidades de Domínio)**

Um conceito representa uma unidade coesa do mundo real que possui significado para o negócio e necessidade de armazenamento de dados.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/conceitos.png" alt="Conceitos" width="100%">
</div>

* **Identificação:** Geralmente extraídos de substantivos em documentos de requisitos, casos de uso ou entrevistas.
* **Representação UML:** Um retângulo dividido em seções. No nível conceitual, foca-se nas duas primeiras:
    1. **Nome do Conceito:** Substantivo no singular, grafado em *UpperCamelCase*.
    2. **Seção de Atributos:** Lista de propriedades do conceito.
    3. **Seção de Métodos:** *Não utilizada* no Modelo Conceitual (pertence ao nível de Design).

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/diagrama-de-classes-uml.png" alt="diagrama de classes da UML para 
representar conceitos e atributos" width="100%">
</div>

---

### **3. Atributos e Tipagem**

Atributos são informações alfanuméricas simples associadas a um conceito. Devem respeitar a **Primeira Forma Normal (1FN)**: não podem ser multivalorados nem compostos.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/atributos.png" alt="Atributos" width="100%">
</div>

#### **Sintaxe de Representação**
A notação padrão UML para atributos é:  
`visibilidade nome: tipo = valor_inicial`

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
      <td>Grafado em <i>lowerCamelCase</i>. Deve ser autoexplicativo.</td>
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

### **4. Onde Buscar Informações**

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/onde-buscar-informacoes.png" alt="Onde Buscar Informações" width="100%">
</div>

### **5. Visão geral do Sistema**

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/visao-geral-do-sistema.png" alt="documentode formato livre que especifica, em linhas
gerais,os requisitos do sistema. Sistema de Controle Escolar" width="100%">
</div>

### **6. Caso de Uso**

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/caso-de-uso.png" alt="Caso de uso:documento estruturado que especifica uma funcionalidade do sistema
por meio da troca de informações entre usuários (atores de sistema)e o sistema." width="100%">
</div>

### **4. Orientações para Identificação de Conceitos**

A identificação de conceitos é uma atividade de extração analítica. As fontes de informação incluem:

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/1orientacoes-para-identificacao-conceitos.png" alt="Orientações para Identificação de Conceitos." width="100%">
</div>

1.  **Documentação de Requisitos:** Visão Geral do Sistema e Descrições de Casos de Uso.
2.  **Conhecimento Tácito:** Informações obtidas via entrevistas com especialistas de domínio (*Stakeholders*).
3.  **Artefatos de Negócio:** Formulários, Notas Fiscais, Relatórios e Normas vigentes.

- Atenção Procure por:

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/modelo-conceitual/2orientacoes-para-identificacao-conceitos.png" alt="Atenção procure por: Substantivos, Expreções que denotem substantivo Verbos." width="100%">
</div>

#### **Exemplo de Aplicação (Cenário Escolar)**
> *"Deseja-se registrar os **Cursos** disponíveis... quando um curso vai ser oferecido, registra-se uma **Turma**... Uma **Matrícula** de um **Aluno**..."*

**Conceitos Identificados:** `Curso`, `Turma`, `Matrícula`, `Aluno`.

---

### **5. Representação Visual**

Exemplo de estruturação de um conceito com diferentes tipos de atributos:

<p align="center">
  <img src="/secoes/assets/img/modelo-conceitual/1exemplo-visao-geral.png" alt="Exemplo 1 Especificação Estilo Visão Geral do Sistema width="40%">
</p>

* **Identificador:** `<<oid>> codPedido: Integer`
* **Atributo Simples:** `data: Date`
* **Valor Default:** `desconto: Double = 0`
* **Atributo Derivado:** `/valorLiquido: Double`

---

> [!IMPORTANT]
> **Regra de Ouro da Modelagem Conceitual:** > Evite "poluir" o modelo com chaves estrangeiras (FKs) como atributos. As ligações entre conceitos devem ser representadas exclusivamente por **Associações** (linhas), que serão tratadas na próxima seção.





