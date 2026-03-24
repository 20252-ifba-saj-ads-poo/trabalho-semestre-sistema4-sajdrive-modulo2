package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.util.UUID;

public class Motorista extends AbstractModel<UUID> {
    private String nome;


    public Motorista() {
        super(); 
    }

    public Motorista(String nome, String cnh) {
        this();
        this.nome = nome;
    }

    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    
}