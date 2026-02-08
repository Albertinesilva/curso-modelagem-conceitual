## 🌳 Seção 5: Herança e Tipos de Generalização

Esta seção explora os mecanismos de especialização e organização taxonômica de dados. O estudo está estruturado em uma progressão lógica que se inicia pela padronização de domínios finitos e estruturas atômicas, evoluindo para a complexidade das hierarquias de classes.

A seção está dividida em três atos fundamentais:

1.  **Enumerações e Tipos Primitivos:** Padronização de valores e tipos customizados.
2.  **Herança (Parte 1):** Fundamentos, motivação e definições essenciais.
3.  **Herança (Parte 2):** Critérios de aplicabilidade e restrições de design.

---

### **1. Enumerações (Enumerations)**

No design de software profissional, uma enumeração é estrategicamente considerada um **"meio termo"** entre um conceito (classe) e um atributo simples. Ela é utilizada para elevar o nível de semântica e segurança do modelo de dados.

- **Definição**: Representa um conceito que possui um número finito de valores possíveis.
- **Valor para o Negócio**: É aplicada quando esses valores específicos são relevantes o suficiente para serem descritos e padronizados, garantindo a consistência das regras de negócio.
- **Representação UML**: No diagrama de classes, uma enumeração é identificada pelo uso do estereótipo `<<enumeration>>` no topo da estrutura.
- **Identificação de Constantes**: Cada valor possível é listado como uma constante, utilizando o estereótipo `<<enum constant>>` seguido de seu tipo (geralmente `int`).

#### **Representação Visual e Exemplos**

<img src="/secoes/assets/img/heranca-e-generalizacao/enumeracoes.png" alt="Enumeração UML" />

Com base na modelagem apresentada acima, as enumerações permitem padronizar fluxos e calendários:

- **Estado do Pedido (`EstadoDoPedido`)**: Restringe o ciclo de vida de uma venda aos estados `AGUARDANDO_PAGAMENTO`, `ENVIADO`, `ENTREGUE`, `DEVOLVIDO` e `CANCELADO`.
- **Dias da Semana (`DiaDaSemana`)**: Define o domínio fechado de `Domingo` a `Sabado`, garantindo que o sistema não aceite valores inconsistentes para datas semanais.

> [!TIP]
> **Dica de Design**: Para simplificar o diagrama principal do sistema, recomenda-se incluir as enumerações em um diagrama separado. No diagrama do sistema, o nome da enumeração deve ser representado diretamente como o tipo do atributo.

#### **Aplicação no Diagrama do Sistema**

Para simplificar o diagrama principal do sistema, recomenda-se incluir as enumerações em um diagrama separado. No diagrama do sistema, o nome da enumeração é representado diretamente como um tipo de atributo.

<img src="/secoes/assets/img/heranca-e-generalizacao/uso-da-enumeracao.png" alt="Uso da Enumeração no Pedido" />

Esta abordagem mantém o diagrama principal limpo e legível. [cite_start]Como demonstrado na classe `Pedido`, o atributo `estado` assume o tipo `EstadoDoPedido`, delegando a definição dos valores possíveis para a estrutura da enumeração externa.

#### **Exemplo Prático: Ciclo de Vida de um Pedido**

Um pedido não pode assumir qualquer estado; ele está restrito a valores pré-definidos que orientam o fluxo do sistema:

* `AGUARDANDO_PAGAMENTO`
* `ENVIADO`
* `ENTREGUE`
* `DEVOLVIDO`
* `CANCELADO`

> [!TIP]
> **Dica de Design:** Para manter a clareza do diagrama principal, recomenda-se modelar as enumerações em um diagrama separado, referenciando o nome da enumeração diretamente como o tipo do atributo no diagrama do sistema.

---

### **2. Tipos Primitivos Customizados**

Assim como as enumerações, os **Tipos Primitivos** são considerados um "meio-termo" na modelagem. Eles representam conceitos cuja simplicidade estrutural não justifica a criação de uma entidade comum com identificador próprio no diagrama do modelo conceitual.

* **Regra de Formação:** É meramente sintática e independente dos dados dinâmicos do sistema.
* **Uso Comum:** Utilizados para encapsular dados que possuem uma estrutura interna fixa (compostos), mas que funcionam como uma unidade de valor.

#### **Exemplos de Tipos Primitivos**

<img src="/secoes/assets/img/heranca-e-generalizacao/tipos-primitivos.png" alt="Exemplos de Tipos Primitivos UML" />

A modelagem de tipos primitivos permite definir estruturas claras para atributos que, embora simples, possuem componentes internos:

* **Telefone:** Composto por `codigoDoPais`, `ddd` e `numero`, mas tratado como um único atributo de contato.
* **Endereçamento/Posição:** Como `Posicao` (x, y) ou `CEP`.
* **Data:** Composta por `dia`, `mes` e `ano`.
* **Documentação:** Como o `ISBN` para livros.

---
### **3. Ressalvas Técnicas sobre Tipagem de Datas**
 Embora possamos modelar um tipo primitivo `Data` com campos inteiros (`dia`, `mes`, `ano`), a engenharia de software moderna trata esses dados de forma otimizada.

- **Perspectiva de Implementação (Java):** O tipo `Date` armazena a informação como um número inteiro longo (`long`), representando os milissegundos decorridos desde a _Unix Epoch_ (01/01/1970).
- **Reflexo na Modelagem:** Ao modelar, foque na necessidade do negócio. Se a data requer cálculos complexos ou fusos horários, trate-a como um tipo primitivo robusto, mas ciente da abstração de implementação subjacente.

---

### **💡 Síntese da Transição**

Dominar **Enums** e **Tipos Primitivos** é o primeiro passo para o reuso de tipos. Enquanto estes simplificam os atributos, a **Herança** (que veremos a seguir) simplifica a estrutura das classes, permitindo que conceitos diferentes compartilhem uma base comum de dados e comportamentos.

<p align="center">
  <b>Próximo Nível: 👉 </b> <a href="https://github.com/Albertinesilva/curso-modelagem-conceitual/blob/main/secoes/06-Estudo-de-Caso.md">Seção 6: Estudo de Caso</a>
</p>
