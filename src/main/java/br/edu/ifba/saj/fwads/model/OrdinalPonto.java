package br.edu.ifba.saj.fwads.model;

import java.time.LocalTime;
import java.time.Period;

public class OrdinalPonto {
    private int ordinal;
    private Ponto ponto;
    private Period tempoPontoAnterior;

   // TODO definir o horario previsto para cada ponto, para facilitar a consulta de viagens por ponto
    
    
    public Ponto getPonto() {
        return ponto;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
