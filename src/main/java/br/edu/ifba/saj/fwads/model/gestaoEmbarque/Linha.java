package br.edu.ifba.saj.fwads.model.gestaoEmbarque;



import java.util.ArrayList;
import java.util.List;

public class Linha {
    private String nomeLinha;    
    private List<ParadaOnibus> paradas;

    public Linha(String InomeLinha){
        this.nomeLinha = InomeLinha;
    }
    
    public String getNomeLinha(){
        return nomeLinha;
    }

    public List<ParadaOnibus> getListaDePontos() {
        return listaDePontos;
    }
    
    public List<Viagem> getlistaViagems(){
        return listaDeViagens;
    }

}
