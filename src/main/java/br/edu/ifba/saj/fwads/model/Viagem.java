package br.edu.ifba.saj.fwads.model;

import java.io.ObjectInputFilter.Status;
import java.time.LocalTime;
import java.util.List;

public class Viagem {
    private int id;
    private Linha linha;
    private Motorista motorista;// adiçaão do motorista na viagem, para facilitar a consulta de viagens por motorista
    private Onibus onibus;
    private LocalTime horarioSaida;//TODO um horarioa para cada OrdinalPonto
    private OrdinalPonto pontoAtual;// adição do ponto atual para facilitar a consulta de viagens por ponto
    private List<Embarque> listaDeEmbarques;
    private StatusLotacaoEnum statusLotacao;
    private OrdinalPonto pontoDesvio;  //define qual ponto da minha linha vai ter um desvio 
    private OrdinalPonto pontoRetorno;
    
    public Onibus getOnibus() {
        return onibus;
    }

    public Linha getLinha() {
        return linha;
    }
    public void setPontoAtual(Ponto pontoAtual) {
        // sempre q trocar, notificar os interressados em embarcar no proximo ponto pela lista de OrdialPonto da linha
        int ordinalAtual = 0;
        for (OrdinalPonto ordinalPonto : getLinha().getListaDePontos()) {
            if(ordinalPonto.getPonto().equals(pontoAtual)){
               ordinalAtual = ordinalPonto.getOrdinal();
            }
            
        } 
        //se ordinal começa em 1 
        Ponto proximoPonto = getLinha().getListaDePontos().get(ordinalAtual).getPonto();

      for (Embarque embarque : listaDeEmbarques) {
        if (embarque.getPontoOrigem().equals(proximoPonto)) {
            if (embarque.getPassageiro().isReceberAlertaAproximacao()) {
                
                //TODO notificar passageiro

                
            }
            
        }
      }

        // buscar passageiros que querem embarcar nesse ponto;

        this.pontoAtual = pontoAtual;
    }
    public StatusLotacaoEnum getStatusLotacao() {
        int embarcados = 0;
        
        for (Embarque embarque : listaDeEmbarques) {
            
            if (embarque.getStatus().equals(StatusEmbarqueEnum.Concluido)) {
                embarcados++;
            };
        }

        if(embarcados/getOnibus().getQuantidaMaximaEmbarque() > 0.5){
            return StatusLotacaoEnum.Moderado;
        }
        //TODO criar outras condicionais


    }
}
