package br.edu.ifba.saj.fwads.model.gestaoEmbarque;


public class Onibus {

    private String placa;
    private int quantidadeMaximaEmbarque;
    
    
    public Onibus(String Iplaca){
        this.placa = Iplaca;
        this.quantidadeMaximaEmbarque = 10;
    }

    public String getPlaca(){
        return placa;
    }

    public int getQuantidaMaximaEmbarque() {
        return quantidadeMaximaEmbarque;
    }

    public void setPlaca(String Iplaca){
        this.placa = Iplaca;
    }

    public void setQuantidadeMaximaEmbarque(int IQuantidadeMaximaEmbarque){
        this.quantidadeMaximaEmbarque = IQuantidadeMaximaEmbarque;
    }
}

