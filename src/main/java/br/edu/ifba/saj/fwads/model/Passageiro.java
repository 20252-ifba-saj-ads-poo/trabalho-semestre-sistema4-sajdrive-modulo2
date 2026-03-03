package br.edu.ifba.saj.fwads.model;

import java.util.List;

public class Passageiro {
    private int id;
    private String nome;    
    private boolean receberAlertaAproximacao;
    private boolean notificarMudancalinha;
    private boolean exibirStatusLotacao;
    private List<Embarque> meusEmbarques; // Definir a relação entre passageiro e embarque, para facilitar a consulta de embarques por passageiro
    // lista de embarques 
    public boolean isReceberAlertaAproximacao() {
        return receberAlertaAproximacao;
    }




}


