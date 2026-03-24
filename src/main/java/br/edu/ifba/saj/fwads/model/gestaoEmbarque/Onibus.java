package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.util.UUID;


public class Onibus extends AbstractModel<UUID> {

    private String placa;
    private int lotacaoMaxima;

   
    public Onibus() {
        super();
    }

   
    public Onibus(String placa, int lotacaoMaxima) {
        this();
        this.placa = placa;
        this.lotacaoMaxima = lotacaoMaxima;
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public void setLotacaoMaxima(int lotacaoMaxima) {
        // Proteção simples: não permitir lotação negativa
        if (lotacaoMaxima > 0) {
            this.lotacaoMaxima = lotacaoMaxima;
        }
    }

    @Override
    public String toString() {
        return "Ônibus Placa: " + placa + " (Capacidade: " + lotacaoMaxima + ")";
    }
}
