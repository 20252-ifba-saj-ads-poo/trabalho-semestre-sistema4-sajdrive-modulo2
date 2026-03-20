package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import java.time.LocalTime; 

public class Motorista {

    private int id;
    private String nomeMotorista;    
    

    public Motorista(String InomeMotorista){
        this.nomeMotorista = InomeMotorista;
    }

    public String getNomeMotorista(){
        return nomeMotorista;
    }

    public void setNomeMotorista(String InomeMotorista){
        this.nomeMotorista = InomeMotorista;
    }
}
