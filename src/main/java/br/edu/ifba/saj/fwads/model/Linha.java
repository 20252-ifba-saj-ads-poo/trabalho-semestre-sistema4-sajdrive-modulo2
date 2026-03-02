package br.edu.ifba.saj.fwads.model;

import java.time.LocalTime; 
import java.util.List;

public class Linha {

    private int id;
    private String nome;    
    private List<OrdinalPonto> listaDePontos;//definir ordem
    private List<Viagem> listaDeViagens;

    public List<OrdinalPonto> getListaDePontos() {
        return listaDePontos;
    }

}
