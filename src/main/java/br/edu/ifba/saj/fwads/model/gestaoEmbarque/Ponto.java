package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.util.UUID;

public class Ponto extends AbstractModel<UUID> {
    private String nome;
    private String endereco;

    public Ponto() { 
        super(); 
    }

    public Ponto(String nome, String endereco) {
        this();
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome; 
    }

    public String getEndereco() { 
        return endereco; 
    }
    public void setEndereco(String endereco) 
    { 
        this.endereco = endereco; }
    
    @Override
    public String toString() { 
        return nome; 
    }
}
