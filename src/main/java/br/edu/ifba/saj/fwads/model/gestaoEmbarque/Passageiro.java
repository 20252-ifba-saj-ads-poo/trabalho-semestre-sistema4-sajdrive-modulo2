package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.util.UUID;

public class Passageiro extends AbstractModel<UUID> {
    private String nome;
    private boolean receberAlertaAproximacao;
    private boolean receberAlertaDesvio;
    private boolean exibirLotacao;

    public Passageiro() {
        super();
        
        this.receberAlertaAproximacao = true;
        this.receberAlertaDesvio = true;
        this.exibirLotacao = true;
    }

    public Passageiro(String nome, String email) {
        this();
        this.nome = nome;
    }

    // Getters e Setters
    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public boolean isReceberAlertaAproximacao() { 
        return receberAlertaAproximacao;
     
    }

    public void setReceberAlertaAproximacao(boolean status) { 
        this.receberAlertaAproximacao = status; 
    }

    public boolean isReceberAlertaDesvio() { 
        return receberAlertaDesvio; 
    }
    public void setReceberAlertaDesvio(boolean status) { 
        this.receberAlertaDesvio = status; 
    }

    public boolean isExibirLotacao() { 
        return exibirLotacao; 
    }

    public void setExibirLotacao(boolean status) { 
        this.exibirLotacao = status; 
    }
}


