<h2 align="center">📊 Seção 1: Introdução à Modelagem de Domínio</h2>

<br>

Esta seção apresenta os fundamentos da modelagem, definindo o que é um domínio e como ele se transforma em diferentes níveis de abstração até chegar à implementação técnica.

---

### **1. O que é Domínio e Modelo de Domínio?**

- Domínio: É definido como a área de negócio que está sendo observada.
- Modelo de Domínio: É um modelo que descreve as entidades presentes naquele domínio e as inter-relações existentes entre elas.

---

### **2. Níveis de Abstração do Modelo**

A modelagem progride através de diferentes níveis, cada um com responsabilidades e objetivos distintos:

<table border="2" align="center" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="4">NÍVEIS DE ABSTRAÇÃO DO MODELO DE DOMÍNIO</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Nível</th>
      <th>Responsável</th>
      <th>Objetivo</th>
      <th>Dependência</th>
    </tr>
  </thead>

  <tbody>
    <tr align="center">
      <td><b>`Conceitual` ou de Análise (de Negócio)</b></td>
      <td>Analista de Negócio</td>
      <td>Descrever as entidades do domínio **(do negócio)** e suas inter-relações: **Independentemente de SISTEMA**.</td>
      <td>Independente de SISTEMA.</td>
    </tr>
    <tr align="center">
      <td><b>**Conceitual** Ou de Análise **(de Sistema)**</b></td>
      <td>Analista de Sistemas</td>
      <td>Descrever as entidades do domínio **(do sistema)** e suas inter-relações Independentemente de PARADIGMA E TECNOLOGIA.</td>
      <td>Independente de PARADIGMA E TECNOLOGIA.</td>
    </tr>
    <tr align="center">
      <td><b>**Lógico** ou de Design</b></td>
      <td>Projetista</td>
      <td>Descrever as entidades do domínio **(do sistema)** e suas inter-relações: preso a um PARADIGMA **(Ex: Relacional, Orientado a Objetos)** .</td>
      Independentemente de TECNOLOGIA.
      <td>Independente de TECNOLOGIA.</td>
    </tr>
    <tr align="center">
      <td><b>**Físico** ou de (Implementação)</b></td>
      <td>Implementador</td>
      <td>Descrever as entidades do domínio **(do sistema)** e suas inter-relações: preso a um PARADIGMA (ex: Relacional, Orientado a Objetos), Preso a uma TECNOLOGIA (ex:Java, C#, PHP, Python, Ruby, NodeJS).</td>
      <td>Preso a uma TECNOLOGIA (Ex: Java, C#, SQL).</td>
    </tr>
  </tbody>
</table>

---

### **3. Análise vs. Design**

Existe uma distinção importante entre as fases de projeto:

- **Análise:** Foca em descrever o problema, mantendo-se independente de paradigma ou tecnologia.
- **Design:** Foca em descrever a solução, já estando atrelado a um paradigma específico (como o Orientado a Objetos).
- **Nota do Curso:** No desenvolvimento orientado a objetos, a Análise e o Design tendem a ser muito próximos. Por isso, nesta modelagem conceitual, embora o foco seja o nível de Análise, serão incluídos alguns aspectos de Design, como tipos de dados e direção de associações.

---

### 4. Exemplo de Evolução: Nota Fiscal

O documento demonstra como um documento do mundo real (Nota Fiscal) é traduzido nos diferentes níveis:

- **Conceitual:** Diagramas com entidades como Pedido, Produto e Item de Pedido.
- **Lógico (Relacional):** Estrutura de tabelas com chaves estrangeiras e normalização.
- **Lógico (OO):** Diagrama de classes com métodos e associações entre objetos.
- **Físico:** Código SQL (CREATE TABLE) ou classes Java com atributos e tipos específicos.
