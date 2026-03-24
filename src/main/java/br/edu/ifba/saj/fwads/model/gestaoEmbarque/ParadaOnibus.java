package br.edu.ifba.saj.fwads.model.gestaoEmbarque;


public class ParadaOnibus {
    private int ordem;
    private Ponto ponto;
    
    ParadaOnibus(Ponto Iponto, int Iordem){
        this.ponto = ponto;
        this.ordem = ordem;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public int getordem() {
        return ordem;
    }
}
