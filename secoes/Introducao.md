## 📊 Seção 1: Introdução à Modelagem de Domínio

<p align="center">
  <em>A fundamentação da modelagem: da abstração de negócios à implementação técnica.</em>
</p>

---

### **1. Definições Fundamentais**

A modelagem de domínio é o alicerce de qualquer software robusto. Ela permite que desenvolvedores e especialistas de negócio falem a mesma língua.

- **Domínio:** Representa o ecossistema do problema — a área específica de negócio, suas regras, processos e restrições (Ex: Logística, Finanças, Saúde).
- **Modelo de Domínio:** É a representação abstrata e visual desse ecossistema. Ele descreve as **entidades** (objetos de interesse) e as **inter-relações** (como elas colaboram) para atender aos requisitos.

<p align="center">
  <img src="/secoes/assets/img/introducao/dominio-e-modelo-de-dominio.png" alt="Abstração do Modelo de Domínio" width="75%">
</p>

---

### **2. Ciclo de Abstração do Modelo**

O desenvolvimento de software moderno exige a transição entre diferentes níveis de detalhamento. Cada nível atende a um stakeholder e a um propósito específico:

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

### **3. O Dualismo: Análise vs. Design**

Na engenharia de software, dividimos o projeto em dois grandes momentos:

- **Fase de Análise:** Foca na **compreensão do problema**. O objetivo é garantir que "estamos construindo a coisa certa". É puramente conceitual.
- **Fase de Design (Projeto):** Foca na **construção da solução**. O objetivo é garantir que "estamos construindo a coisa da maneira certa", já considerando limitações técnicas.

> [!TIP]
> **Convergência no Mundo Orientado a Objetos (OO):** No paradigma OO, a barreira entre Análise e Design é tênue, pois o modelo conceitual frequentemente evolui diretamente para o modelo de implementação.

---

### **4. Estudo de Caso Evolutivo: O Ciclo da Nota Fiscal**

Abaixo, observamos a metamorfose de um artefato de negócio (**Nota Fiscal**) através dos níveis de abstração:

#### **A. Perspectiva Conceitual (Análise)**

Foco nas entidades e na semântica. Identificamos que uma nota possui itens e produtos, sem nos preocuparmos com tabelas ou classes de acesso.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/introducao/conceitual-analise-sistema.png" alt="Diagrama Conceitual de Sistema" width="45%">
  <img src="/secoes/assets/img/introducao/conceitual-analise-negocio.png" alt="Fluxo de Negócio" width="45%">
</div>

---

#### **B. Perspectiva Lógica (Design de Solução)**

Aqui o modelo se divide. À esquerda, a visão **Relacional** (normalização e chaves); à direita, a visão **Orientada a Objetos** (associações e métodos).

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/introducao/1nivel-logico-ou-design.png" alt="Esquema Relacional" width="45%">
  <img src="/secoes/assets/img/introducao/2nivel-logico-ou-design.png" alt="Diagrama de Classes Lógico" width="45%">
</div>

---

#### **C. Perspectiva Física (Implementação)**

A última etapa: o modelo se torna "vivo". O SQL define a persistência no banco de dados, enquanto o Java (Spring Boot) define o comportamento em memória.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/introducao/1nivel-fisico-ou-implementacao.png" alt="Script DDL SQL" width="45%">
  <img src="/secoes/assets/img/introducao/2nivel-fisico-ou-implementacao.png" alt="Entidade Java JPA" width="45%">
</div>

---

#### **Comparativo de Paradigmas**

A modelagem conceitual serve como ponte para ambos os paradigmas abaixo, sendo essencial para a integridade do sistema.

<div style="display: flex; gap: 16px; justify-content: center;">
  <img src="/secoes/assets/img/introducao/paradigma-estruturado-relacional.png" alt="Modelo Relacional" width="45%">
  <img src="/secoes/assets/img/introducao/paradigma-orientado-objetos.png" alt="Modelo de Objetos" width="45%">
</div>

---

## 🎯 Conclusão do Módulo: A Fundamentação da Abstração

A conclusão desta etapa inicial estabelece a compreensão de que a modelagem não é uma tarefa meramente documental, mas um processo de **tradução sistemática**. Compreender o domínio é garantir que a solução tecnológica construída seja, antes de tudo, fiel à realidade que ela se propõe a resolver.

### **Principais Insights Adquiridos:**

* **Hierarquia de Níveis de Abstração:** Compreendi que o desenvolvimento de software exige diferentes visões (Conceitual, Lógica e Física). Cada nível resolve um problema específico: o Conceitual foca na **regra**, o Lógico no **paradigma** e o Físico na **execução**.
* **O Valor da Fase de Análise:** Ficou clara a importância de separar a "compreensão do problema" (Análise) da "construção da solução" (Design). Essa separação evita que decisões tecnológicas precoces limitem ou distorçam os requisitos de negócio.
* **A Modelagem como Linguagem Comum:** O Modelo de Domínio atua como o elo de comunicação entre stakeholders (negócio) e desenvolvedores (técnico), eliminando ambiguidades e alinhando as expectativas desde a concepção do sistema.
* **Transição Progressiva:** Através do exemplo da Nota Fiscal, observei como um artefato do mundo real é gradualmente decomposto e transformado até se tornar um script SQL ou uma entidade JPA, sem perder sua essência original.

Com a visão macro dos níveis de abstração consolidada, o próximo passo é mergulhar na **Seção 2**, onde iniciaremos a identificação granular das entidades através de **Conceitos e Atributos**.

---
<p align="center">
  <b>Próximo Nível: 📦 Seção 2 - Conceitos e Atributos</b>
</p>
