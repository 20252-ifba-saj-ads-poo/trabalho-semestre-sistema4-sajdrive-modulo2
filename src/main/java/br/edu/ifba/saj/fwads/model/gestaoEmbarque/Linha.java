package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import java.time.LocalTime; 
import java.util.List;

public class Linha {

    private int id;
    private String nomeLinha;    
    private List<OrdinalPonto> listaDePontos;
    private List<Viagem> listaDeViagens;

    public Linha(String InomeLinha){
        this.nomeLinha = InomeLinha;
    }
    
    public String getNomeLinha(){
        return nomeLinha;
    }

    public List<OrdinalPonto> getListaDePontos() {
        return listaDePontos;
    }
    
    public List<Viagem> getlistaViagems(){
        return listaDeViagens;
    }

}
