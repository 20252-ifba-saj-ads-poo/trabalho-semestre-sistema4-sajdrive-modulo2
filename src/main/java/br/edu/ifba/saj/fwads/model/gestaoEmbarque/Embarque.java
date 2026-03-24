package br.edu.ifba.saj.fwads.model.gestaoEmbarque;


import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.time.LocalDateTime;
import java.util.UUID;

public class Embarque extends AbstractModel<UUID> {
    private Passageiro passageiro;
    private Ponto pontoOrigem;
    private Ponto pontoDestino;
    private LocalDateTime horarioSolicitacao;

    public Embarque() {
        super();
        this.horarioSolicitacao = LocalDateTime.now();
    }

    public Embarque(Passageiro passageiro, Ponto origem, Ponto destino) {
        this();
        this.passageiro = passageiro;
        this.pontoOrigem = origem;
        this.pontoDestino = destino;
    }

    
    public Passageiro getPassageiro() { 
        return passageiro; 
    }

    public void setPassageiro(Passageiro p) { 
        this.passageiro = p; 
    }

    public Ponto getPontoOrigem() {
        return pontoOrigem; 
    }

    public void setPontoOrigem(Ponto o) {
        this.pontoOrigem = o;
    }
    
    public Ponto getPontoDestino() { 
        return pontoDestino; 
    }

    public void setPontoDestino(Ponto d) { 
        this.pontoDestino = d; 
    }
}