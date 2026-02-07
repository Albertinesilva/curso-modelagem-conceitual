# 🏗️ Seção 4: Associações Todo-Parte (Agregação e Composição)

Na análise de sistemas e modelagem orientada a objetos, as associações **Todo-Parte** são especializações da associação comum. Elas são utilizadas quando um conceito não é apenas relacionado a outro, mas sim composto por ele ou o possui como um componente estrutural.

Visualmente, essas associações são identificadas por um **diamante** posicionado na extremidade da classe que representa o **"Todo"**.

---

### **1. Definições e Notações Visuais**

A distinção entre os tipos de diamante define o grau de dependência e exclusividade entre as partes e o todo.

<img src="/secoes/assets/img/todo-parte/definicao-diamantes.png" alt="Associações Todo-Parte: Agregação e Composição" width="100%">

#### **1.1. Diamante Branco: Agregação**

- **Definição:** Indica que o conceito "parte" **não é exclusivo** do "todo".
- **Engenharia:** A parte pode existir independentemente ou estar vinculada a outros todos simultaneamente (ou em momentos diferentes).

#### **1.2. Diamante Preto: Composição**

- **Definição:** Indica que o conceito "parte" **é exclusivo** do "todo".
- **Engenharia:** A existência da parte está intrinsecamente ligada ao todo. Devido a essa exclusividade, a multiplicidade no lado do diamante preto (o Todo) será **sempre 1 ou 0..1**.

---

#### **2.1. A Regra da Exclusividade (Multiplicidade 1 ou 0..1)**

Na Engenharia de Software, a composição implica que uma "parte" não pode pertencer a múltiplos "todos" simultaneamente. Essa exclusividade de posse reflete diretamente na multiplicidade do diagrama.

<img src="/secoes/assets/img/todo-parte/exclusividade-composicao.png" alt="Regra de Exclusividade na Composição UML" width="100%">

- **Restrição Técnica:** Como a composição (diamante preto) é uma relação exclusiva, a multiplicidade no lado do diamante será **obrigatoriamente 1 ou 0..1**.
- **Implicação no Banco de Dados:** Isso garante que a chave estrangeira (FK) na tabela da "Parte" aponte para um único registro na tabela do "Todo", impedindo inconsistências de dupla paternidade de dados.

---

### **3. Exemplos Práticos de Agregação**

Na Engenharia de Software, a escolha pela agregação (diamante branco) é tomada quando a "Parte" tem valor para o sistema mesmo sem o seu "Todo" original.

#### **3.1. Cenário de Vendas e Pedidos (Exemplo 2)**

Neste modelo, observamos como os produtos e itens se comportam em relação a uma venda ou pedido.

<img src="/secoes/assets/img/todo-parte/agregacao-exemplo-2.png" alt="Agregação no contexto de Pedidos e Vendas" width="100%">

- **Análise Técnica:** Um `Produto` é agregado ao `Item`, mas se o `Pedido` ou a `Venda` forem excluídos, o `Produto` permanece no catálogo do sistema. A exclusão do "Todo" (Venda) não pode causar a exclusão da "Parte" (Produto), caracterizando a independência da agregação.

#### **3.2. Cenário Acadêmico: Cursos e Disciplinas (Exemplo 3)**

Este exemplo ilustra o compartilhamento de partes entre diferentes todos.

<img src="/secoes/assets/img/todo-parte/agregacao-exemplo-3.png" alt="Agregação no contexto de Cursos e Disciplinas" width="100%">

- **Análise Técnica:** Uma `Disciplina` pode ser parte de um `Curso`. No entanto, a disciplina possui uma existência autônoma na instituição de ensino. Se um curso for descontinuado, a disciplina continua existindo no banco de dados, podendo inclusive ser reaproveitada por outros cursos.

---

### **1. Agregação (Diamante Branco)**

A Agregação representa um relacionamento onde a "parte" pertence ao "todo", mas mantém uma existência independente. É uma relação de **não exclusividade**.

- **Semântica:** O conceito "parte" pode existir sem o "todo".
- **Ciclo de Vida:** Se o objeto "Todo" for destruído, o objeto "Parte" continua existindo no sistema (Acoplamento Fraco).
- **Exemplo Prático:** Um `Carro` e seus `Pneus`. Os pneus são partes do carro, mas se o carro for destruído, os pneus podem ser removidos e reutilizados em outro contexto.

<img src="/secoes/assets/img/todo-parte/agregacao-exemplo.png" alt="Exemplo de Agregação UML entre Carro e Pneu" width="100%">

---

### **2. Composição (Diamante Preto)**

A Composição é uma forma mais forte e restrita de agregação, indicando que a "parte" é **exclusiva** de um único "todo". Existe uma dependência existencial severa.

- **Semântica:** A "parte" pertence a apenas um "todo" por vez. Ela não tem vida própria fora do contexto do objeto pai.
- **Ciclo de Vida:** Existe um compartilhamento de ciclo de vida. Se o "todo" for removido, as "partes" são obrigatoriamente eliminadas junto (Acoplamento Forte).
- **Restrição de Multiplicidade:** Devido à regra de exclusividade, a multiplicidade no lado do diamante preto deve ser sempre **1** ou **0..1**.

- **Exemplo Prático:** Um `Estado` e suas `Cidades`. Uma cidade não pode pertencer a dois estados simultaneamente. Se o estado for excluído do modelo, suas cidades perdem a razão de existência.

<img src="/secoes/assets/img/todo-parte/composicao-exemplo.png" alt="Exemplo de Composição UML entre Estado e Cidade" width="100%">

---

### **3. Matriz de Decisão: Quando usar cada uma?**

Como analista de sistemas, a escolha do diamante impacta a integridade referencial e o design do banco de dados:

| Cenário                 | Relação          | Justificativa de Engenharia                                                                 |
| :---------------------- | :--------------- | :------------------------------------------------------------------------------------------ |
| **Pedido e ItemPedido** | ⬥ **Composição** | O item é um detalhe interno do pedido; não existe item de um pedido inexistente.            |
| **Curso e Disciplina**  | ♢ **Agregação**  | A disciplina é uma entidade do catálogo; pode existir mesmo que o curso seja descontinuado. |
| **Venda e Produto**     | ♢ **Agregação**  | O produto é independente; a exclusão de uma venda não deve apagar o produto do estoque.     |

<img src="/secoes/assets/img/todo-parte/exemplos-agregacao-composicao.png" alt="Exemplos Práticos de Todo-Parte" width="100%">

---

### **4. Ressalvas Importantes (Boas Práticas)**

O uso de diamantes exige rigor técnico para não poluir o diagrama com informações semanticamente incorretas:

#### **4.1. Verificação da Semântica de Posse**

O diamante deve ser usado apenas em relações reais de **Todo-Parte**. Não confunda "propriedade" ou "uso" com "composição".

- **❌ Incorreto:** `Pessoa` ⬥--- `Carro`. (Uma pessoa não é "feita" de carros).
- **✅ Correto:** Use uma associação comum com o papel "dono".

<img src="/secoes/assets/img/todo-parte/ressalva-uso-diamante.png" alt="Erro comum no uso de diamantes" width="100%">

#### **4.2. O Mito da Deleção em Cascata**

Muitos analistas acreditam que o diamante preto é a única forma de indicar deleção em cascata (_Cascade Delete_).

- **Fato:** Na verdade, o que define tecnicamente a necessidade de apagar registros dependentes é a **Regra de Negócio** e a **Multiplicidade** (especialmente o limite mínimo 1). O diamante é apenas um reforço visual da exclusividade de posse.

<img src="/secoes/assets/img/todo-parte/ressalva-delecao-cascata.png" alt="Ressalva sobre deleção em cascata" width="100%">

---

### **5. Resumo Comparativo**

| Característica      | Agregação (♢)      | Composição (⬥)                      |
| :------------------ | :----------------- | :---------------------------------- |
| **Tipo de Relação** | Todo-Parte (Fraca) | Todo-Parte (Forte)                  |
| **Exclusividade**   | Não                | Sim (Multiplicidade 1 ou 0..1)      |
| **Ciclo de Vida**   | Independente       | Dependente (O Todo comanda a Parte) |

> [!TIP]
> **Heurística de Design:** Ao modelar, pergunte: _"Se eu deletar o Todo, a Parte ainda tem utilidade ou sentido para o sistema?"_. Se a resposta for **Sim**, use Agregação. Se for **Não**, use Composição.

<p align="center">
  <b>Próximo Nível: 👉 </b> <a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/Heranca-e-Tipos-Generalizacao.md">Seção 5: Herança e Tipos (Generalização)</a>
</p>
