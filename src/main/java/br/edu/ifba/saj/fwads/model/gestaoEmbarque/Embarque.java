package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import java.time.LocalTime;

public class Embarque {
   private Ponto pontoOrigem;
    private Ponto pontoDestino;
    private Viagem viagem;
    private Passageiro passageiro;
    private StatusEmbarqueEnum status;


    public Embarque(Ponto IpontoOrigem, Ponto IpontoDestino, Viagem Iviagem, Passageiro Ipassageiro, StatusEmbarqueEnum Istatus){

        this.pontoOrigem = IpontoOrigem;
        this.pontoDestino = IpontoDestino;
        this.viagem = Iviagem;
        this.passageiro = Ipassageiro;
        this.status = Istatus;
    }

    public void setPontoOrigem(Ponto IPontoOrigemPonto) {
        this.pontoOrigem = IPontoOrigemPonto;
    }

    public void setPontoDestino(Ponto IpontoDestino){
        this.pontoDestino = IpontoDestino;
    }

    public void setViagem(Viagem Iviagem){
        this.viagem = Iviagem;
    }

    public void setPassageiro(Passageiro Ipassageiro) {
        this.passageiro = Ipassageiro;
    }

    public void setStatusEmbarque(StatusEmbarqueEnum IstatusEmbarqueEnum) {
        this.status = IstatusEmbarqueEnum;
    }

    public Ponto getPontoOrigem() {
        return pontoOrigem;
    }

    public Ponto getPontoDestino(){
        return pontoDestino;
    }

    public Viagem getViagem(){
        return viagem;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public StatusEmbarqueEnum getStatus() {
        return status;
    }

    
}
