package br.edu.ifba.saj.fwads.model;

import java.time.LocalTime;

public class OrdinalPonto {
    private int ordinal;
    private Ponto ponto;
    private LocalTime horarioPrevisto;// TODO definir o horario previsto para cada ponto, para facilitar a consulta de viagens por ponto
    public Ponto getPonto() {
        return ponto;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
