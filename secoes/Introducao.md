<h2 align="center">📊 Seção 1: Introdução à Modelagem de Domínio</h2>

<br>

Esta seção apresenta os fundamentos da modelagem, definindo o que é um domínio e como ele se transforma em diferentes níveis de abstração até chegar à implementação técnica.

---

### **1. O que é Domínio e Modelo de Domínio?**

- Domínio: É definido como a área de negócio que está sendo observada.
- Modelo de Domínio: É um modelo que descreve as entidades presentes naquele domínio e as inter-relações existentes entre elas.

<img src="/secoes/assets/img/introducao/dominio-e-modelo-de-dominio.png" alt="O que e modelo de dominio?" width="70%" height="70%">

---

### **2. Níveis de Abstração do Modelo**

A modelagem progride através de diferentes níveis, cada um com responsabilidades e objetivos distintos:

<table border="2" align="center" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="4">NÍVEIS DE ABSTRAÇÃO DO MODELO DE DOMÍNIO</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th width="20%">Nível</th>
      <th width="15%">Responsável</th>
      <th width="35%">Objetivo (O Quê)</th>
      <th width="30%">Dependência (Vínculos)</th>
    </tr>
  </thead>

  <tbody>
    <tr>
      <td><b>Conceitual (Negócio)</b></td>
      <td>Analista de Negócio</td>
      <td>Modelar as regras e entidades fundamentais do negócio sob a ótica do cliente.</td>
      <td>Independente de <b>Sistema</b>.</td>
    </tr>
    <tr>
      <td><b>Conceitual (Sistema)</b></td>
      <td>Analista de Sistemas</td>
      <td>Mapear as entidades do domínio que farão parte da solução computacional.</td>
      <td>Independente de <b>Paradigma e Tecnologia</b>.</td>
    </tr>
    <tr>
      <td><b>Lógico (Design)</b></td>
      <td>Projetista</td>
      <td>Definir a estrutura e o comportamento dos dados conforme um modelo computacional.</td>
      <td>Vinculado a um <b>Paradigma</b> (Ex: OO ou Relacional); Independente de Tecnologia.</td>
    </tr>
    <tr>
      <td><b>Físico (Implementação)</b></td>
      <td>Implementador</td>
      <td>Codificar, instanciar e persistir o modelo em um ambiente de execução real.</td>
      <td>Vinculado a <b>Paradigma e Tecnologia</b> (Ex: Java, C#, SQL, NoSQL).</td>
    </tr>
  </tbody>
</table>

---

<img src="/secoes/assets/img/introducao/conceitual-analise-sistema.png" alt="Conceitual ou de análise de sistema" width="70%" height="70%">
<img src="/secoes/assets/img/introducao/conceitual-analise-negocio.png" alt="Conceitual ou de análise de negocio" width="70%" height="70%">

<img src="/secoes/assets/img/introducao/1nivel-logico-ou-design.png" alt="Nivel lógico ou de design" width="70%" height="70%">
<img src="/secoes/assets/img/introducao/2nivel-logico-ou-design.png" alt="Nivel lógico ou de design" width="70%" height="70%">

<img src="/secoes/assets/img/introducao/1nivel-fisico-ou-implementacao.png" alt="Nivel fisico ou implementação" width="70%" height="70%">
<img src="/secoes/assets/img/introducao/2nivel-fisico-ou-implementacao.png" alt="Nivel fisico ou implementação" width="70%" height="70%">

<img src="/secoes/assets/img/introducao/paradigma-estruturado-relacional.png" alt="Paradigma estruturado relacional" width="70%" height="70%">
<img src="/secoes/assets/img/introducao/paradigma-orientado-objetos.png" alt="Paradigma orientado a objeto" width="70%" height="70%">

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
