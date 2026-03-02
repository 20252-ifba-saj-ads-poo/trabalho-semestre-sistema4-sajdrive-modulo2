package br.edu.ifba.saj.fwads.model;

import java.time.LocalTime;
import java.util.List;

public class Viagem {
    private int id;
    private Linha linha;
    private Onibus onibus;
    private LocalTime horarioSaida;
    private List<Embarque> listaDeEmbarques;
    private StatusLotacaoEnum statusLotacao;

}
