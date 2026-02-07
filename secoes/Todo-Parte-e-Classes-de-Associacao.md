# 🏗️ Seção 4: Associações Todo-Parte (Agregação e Composição)

Na análise de sistemas e modelagem orientada a objetos, as associações **Todo-Parte** são especializações da associação comum. Elas são utilizadas quando um conceito não é apenas relacionado a outro, mas sim constituído por ele ou o possui como um componente estrutural.

Visualmente, essas associações são identificadas por um **diamante** posicionado na extremidade da classe que representa o **"Todo"**.

---

### **1. Definições e Notações Visuais**

A distinção entre os tipos de diamante define o grau de dependência existencial e a exclusividade entre as partes e o todo.

<img src="/secoes/assets/img/todo-parte/definicao-diamantes.png" alt="Associações Todo-Parte: Agregação e Composição" width="100%">

---

### **2. Composição (Diamante Preto)**

A **Composição** é uma forma estrita de agregação que indica um acoplamento forte e uma dependência existencial severa da "Parte" em relação ao seu "Todo".

#### **2.1. Semântica e Ciclo de Vida**
- **Exclusividade:** A "parte" pertence a apenas um "todo" por vez. Ela não possui autonomia ou vida própria fora do contexto do objeto pai.
- **Ciclo de Vida Compartilhado:** Existe um vínculo de sobrevivência. Se o "todo" for removido, as "partes" são obrigatoriamente eliminadas (Deleção lógica ou física em cascata).

#### **2.2. A Regra da Exclusividade (Multiplicidade 1 ou 0..1)**
Na Engenharia de Software, a exclusividade de posse reflete diretamente na restrição de cardinalidade do diagrama.

<img src="/secoes/assets/img/todo-parte/exclusividade-composicao.png" alt="Regra de Exclusividade na Composição UML" width="100%">

- **Restrição Técnica:** Como a relação é exclusiva, a multiplicidade no lado do diamante (o Todo) será **obrigatoriamente 1 ou 0..1**.
- **Implicação em Banco de Dados:** Essa regra garante que a Chave Estrangeira (FK) na tabela da "Parte" aponte para um único registro na tabela do "Todo", impedindo a inconsistência de "dupla paternidade" de dados.
- **Exemplo Prático:** Um `Estado` e suas `Cidades`. Uma cidade não pode pertencer a dois estados simultaneamente; se o estado for excluído, suas cidades perdem a razão de existência no domínio.

---

### **3. Agregação (Diamante Branco)**

A **Agregação** representa um relacionamento de acoplamento fraco, onde a "parte" pertence ao "todo", mas mantém sua independência funcional e existencial.

#### **3.1. Semântica e Independência**
- **Não Exclusividade:** O conceito "parte" pode existir sem o "todo" ou estar vinculado a diferentes instâncias em momentos distintos.
- **Ciclo de Vida Independente:** Se o objeto "Todo" for destruído, o objeto "Parte" permanece íntegro no sistema. A escolha pela agregação é tomada quando a "Parte" possui valor de negócio por si só.

#### **3.2. Cenários de Aplicação Prática**

**A. Contexto de Vendas e Estoque (Exemplo 2):**
<img src="/secoes/assets/img/todo-parte/agregacao-exemplo-2.png" alt="Agregação no contexto de Pedidos e Vendas" width="100%">

- **Análise Técnica:** Um `Produto` é agregado ao `ItemPedido`. Se o `Pedido` for excluído, o `Produto` deve permanecer no catálogo (estoque). A exclusão do "Todo" não pode causar o apagamento da "Parte" (Produto), caracterizando a autonomia da agregação.

**B. Contexto Acadêmico (Exemplo 3):**
<img src="/secoes/assets/img/todo-parte/agregacao-exemplo-3.png" alt="Agregação no contexto de Cursos e Disciplinas" width="100%">

- **Análise Técnica:** Uma `Disciplina` pode compor a grade de um `Curso`. No entanto, se o curso for descontinuado, a disciplina continua existindo no banco de dados, podendo ser reaproveitada em outras matrizes curriculares.

---

### **4. Matriz de Decisão: Quando usar cada uma?**

| Cenário | Relação | Justificativa de Engenharia |
| :--- | :--- | :--- |
| **Pedido e ItemPedido** | ⬥ **Composição** | O item é um detalhe interno; não existe item de um pedido inexistente. |
| **Curso e Disciplina** | ♢ **Agregação** | A disciplina é uma entidade do catálogo; possui vida própria além do curso. |
| **Venda e Produto** | ♢ **Agregação** | O produto é independente; a venda é apenas um evento que o utiliza. |

---

### **5. Ressalvas Importantes (Boas Práticas)**

#### **5.1. Verificação da Semântica de Posse**
O diamante deve ser usado apenas em relações reais de **Todo-Parte**. Não confunda "propriedade" ou "uso" com "composição".
- **❌ Incorreto:** `Pessoa` ⬥--- `Carro`. (Uma pessoa não é "feita" de carros).
- **✅ Correto:** Use uma associação comum com o papel "dono".

<img src="/secoes/assets/img/todo-parte/ressalva-uso-diamante.png" alt="Erro comum no uso de diamantes" width="100%">

#### **5.2. O Mito da Deleção em Cascata**
- **Fato:** O que define tecnicamente a necessidade de apagar registros dependentes é a **Regra de Negócio** traduzida na **Multiplicidade** (especialmente o limite mínimo 1). O diamante preto é um reforço visual da exclusividade, mas a lógica de sistema baseia-se na cardinalidade mínima.

<img src="/secoes/assets/img/todo-parte/ressalva-delecao-cascata.png" alt="Ressalva sobre deleção em cascata" width="100%">

---

### **6. Resumo Comparativo**

| Característica | Agregação (♢) | Composição (⬥) |
| :--- | :--- | :--- |
| **Tipo de Relação** | Todo-Parte (Fraca) | Todo-Parte (Forte) |
| **Exclusividade** | Não | Sim (Multiplicidade 1 ou 0..1) |
| **Ciclo de Vida** | Independente | Dependente (Todo comanda a Parte) |

> [!TIP]
> **Heurística de Design:** Ao modelar, pergunte: *"Se eu deletar o Todo, a Parte ainda tem utilidade para o sistema?"*. Se a resposta for **Sim**, use Agregação. Se for **Não**, use Composição.

<p align="center">
  <b>Próximo Nível: 👉 </b> <a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/Heranca-e-Tipos-Generalizacao.md">Seção 5: Herança e Tipos (Generalização)</a>
</p>
