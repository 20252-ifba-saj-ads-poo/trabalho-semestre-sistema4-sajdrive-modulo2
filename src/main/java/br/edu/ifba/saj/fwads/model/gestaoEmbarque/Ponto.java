package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import java.time.LocalTime;

public class Ponto {
    
    private int id;
    private String nomePonto;


    public Ponto(String InomePonto){
        this.nomePonto = InomePonto;
    }

    public String getNomePonto(){
        return nomePonto;
    }

    public void setNomePonto(String InomePonto){
        this.nomePonto = InomePonto;
    }
}
