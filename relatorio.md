### 📝 Resumo e Nota
- **Nota Final:** 40/100
- **Visão Geral:** O projeto apresentou uma tentativa de implementar a arquitetura exigida, com a modelagem do domínio (Passageiro, Motorista, Viagem). Entretanto, as falhas são críticas: o projeto **não compila** devido a erros sintáticos severos, como pacotes errados, exceções inexistentes, chamadas a variáveis não declaradas e múltiplas classes públicas no mesmo arquivo. Ademais, há violações acentuadas de estado de objeto (parâmetros perdidos em construtores) e da separação de camadas, denotando fragilidade nos conceitos base de Programação Orientada a Objetos.

### 📊 Detalhamento do Barema
- **[10/20] Interface Gráfica:** A interface gráfica com JavaFX sequer pode ser inicializada, avaliada ou testada, visto que o código base apresenta diversos erros de compilação bloqueantes. Além disso, ainda restam vestígios explícitos do código-base template (classes de Livro e Autor), não atendendo à regra de refatoração para o módulo.
- **[10/30] Camada de Negócio:** Houve a iniciativa de criar `EmbarqueService` e `ViagemService` implementando as validações de domínio (lotação). Contudo, há a importação de arquivos inexistentes (`CapacidadeExcedidaException`), além da ausência de polimorfismo/herança por meio do Service base genérico. A camada fere o encapsulamento modificando diretamente a lista interna das entidades (`viagem.getPassageirosEmbarcados().add(...)`).
- **[10/20] Camada de Dados (Repository/Modelagem):** As entidades de domínio foram criadas, mas a construção do DAO Genérico falha em simular o banco de dados por instanciar a coleção de forma não-estática global (`protected Map<UUID, T> bancoDeDados = new HashMap<>();`), o que impossibilita compartilhar os dados persistidos caso existam múltiplos DAOs. Faltou a aplicação eficaz de `equals()` e `hashCode()` no model base genérico, e persiste o "código zumbi" sem sentido para o escopo (`Biblioteca`, `Autor`, `Livro`).
- **[5/20] Separação em Camadas:** Forte acoplamento e transbordamento de escopo. A camada de Service mistura-se à apresentação, como evidenciado pelo uso injetado de tela em texto (`System.out.println`) no meio da regra de negócio (`verificarAlertasDeLotacao`), assumindo uma responsabilidade que caberia ao Controller (retornar a exceção e tratá-la via `Alert` na Camada de Visão).
- **[5/10] Boas Práticas e POO:** Abundância de más práticas que tornam o projeto inoperável: arquivos no pacote errado (`exceptions` declarado dentro de arquivos da `service`), quebra de estado de objetos descartando dados (`cnh` e `email` ignorados nos construtores do Model), e a quebra da regra fundamental de colocar mais de uma classe pública por arquivo `.java`.

### 🐛 Erros Lógicos, Arquiteturais e Execução
- **Projeto Não Compila:** Variáveis que não existem referenciadas na classe `Linha` (`listaDePontos`, `listaDeViagens`), impedindo imediatamente a compilação e execução da UI JavaFX.
- **Classe/Exception Fantasma:** `CapacidadeExcedidaException` importada na classe `ViagemService` mas deletada/ausente no projeto físico.
- **Múltiplas Classes Públicas no Mesmo Arquivo:** `EmbarqueService` contém a declaração de exceções `PontoInvalidoException` e `DesvioRotaException` como `public class` no fim de seu arquivo `.java`, algo bloqueado sintaticamente pela linguagem Java.
- **Perda de Estado em Construtor:** Construtores de `Motorista` e `Passageiro` recebem parâmetros e os descartam localmente sem fazer atribuições via `this`. 
- **Poluindo o Service com Console (System.out):** Lógica da camada de Visão vazando no Service durante a checagem de lotação do ônibus.
- **Quebra do Acesso Restrito (Encapsulamento):** O Service injeta passageiros dentro da viagem puxando o getter e forçando o `add()`.
- **Banco em Memória Volátil:** A estrutura Map empregada no `GenericDAOImpl` é local, esvaziando ou bifurcando os dados conforme diferentes serviços a instanciem.

### 💡 Refatoração / Código
**1. Conserto dos Construtores (Perda de Estado):**
Sua classe não está atribuindo os parâmetros da assinatura à infraestrutura do objeto. Se não os salvar na propriedade da instância (`this`), o valor é perdido no fim do escopo do método:
```java
// Em Motorista.java
public Motorista(String nome, String cnh) {
    this(); 
    this.nome = nome;
    this.cnh = cnh; // Corrigido! Era omitido e o construtor original tornava a variável inútil.
}
```

**2. Limpeza do Escopo do Service e Isolamento:**
O Serviço não deve usar saídas ao Console ou quebrar propriedades encapsuladas. Delegue a alteração estrutural interna para a própria entidade (Information Expert):
```java
// Em Viagem.java - Centralize a regra interna à própria classe:
public void embarcarPassageiro(Embarque embarque) throws CapacidadeExcedidaException {
    if(this.getPassageirosEmbarcados().size() >= this.getOnibus().getCapacidade()) {
        throw new CapacidadeExcedidaException("Ônibus lotado!");
    }
    this.passageirosEmbarcados.add(embarque);
}

// Em ViagemService.java - Agora o Serviço apenas rege a orquestração!
public void registrarEmbarque(Viagem viagem, Embarque embarque) throws Exception {
    viagem.embarcarPassageiro(embarque); // A própria Viagem cuida de seu ArrayList!
    update(viagem); // Salvar DAO
}
```

**3. Singleton / Contexto Estático no Banco em Memória:**
Para que o seu Map, simulando o DAO, persista e propague os dados aos demais repositórios e serviços durante o Runtime JavaFX, o modificador deve ser no mínimo elevado à partilha de classe:
```java
public abstract class GenericDAOImpl<T extends AbstractModel<UUID>> implements GenericDAO<T, UUID> {
    // Adicionando 'static' para garantir partilha global em memória da instância executada via UI.
    protected static Map<UUID, AbstractModel> bancoDeDados = new HashMap<>();
}
```
