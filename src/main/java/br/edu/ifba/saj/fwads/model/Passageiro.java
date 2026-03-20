package br.edu.ifba.saj.fwads.model;

import java.util.List;

public class Passageiro {
    private int id;
    private String nomePassageiro;    
    private boolean receberAlertaAproximacao;
    private boolean notificarMudancalinha;
    private boolean exibirStatusLotacao;
    private List<Embarque> meusEmbarques; // Definir a relação entre passageiro e embarque, para facilitar a consulta de embarques por passageiro


    public Passageiro(String InomePassageiro){
        this.nomePassageiro = InomePassageiro;
        this.receberAlertaAproximacao = true;
        this.notificarMudancalinha = true;
        this.exibirStatusLotacao = true;
    }
    
    public boolean isReceberAlertaAproximacao() {
        return receberAlertaAproximacao;
    }

    public boolean isNotifiicacaoMudancaLinha(){
        return notificarMudancalinha;
    }

    public boolean isExibirStatusLotacao(){
        return exibirStatusLotacao;
    }

    public List<Embarque> getMeusEmbarques(){
        return meusEmbarques;
    }



}


