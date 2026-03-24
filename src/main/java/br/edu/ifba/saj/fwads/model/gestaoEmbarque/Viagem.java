package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.AbstractModel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Viagem extends AbstractModel<UUID> {

    private Onibus onibus;
    private Motorista motorista;
    private Linha linha;
    private Ponto pontoAtual; // Onde o ônibus está agora no mapa
    private List<Embarque> passageirosEmbarcados;
    private String horarioPartida;
    private boolean emAndamento;
    private int indiceProximaParada;

   
    public Viagem() {
        super();
        this.passageirosEmbarcados = new ArrayList<>();
        this.emAndamento = false;
        this.indiceProximaParada = 0;
    }
    

    
    public Viagem(Onibus onibus, Linha linha, Motorista motorista) {
        this();
        this.onibus = onibus;
        this.linha = linha;
        this.motorista = motorista;
        this.emAndamento = true;
    }

    

  
    public StatusLotacaoEnum calcularStatusLotacao() {
        if (onibus == null || onibus.getLotacaoMaxima() <= 0) {
            return StatusLotacaoEnum.LIVRE;
        }

        int totalPassageiros = passageirosEmbarcados.size();
        double percentualOcupacao = (double) totalPassageiros / onibus.getLotacaoMaxima();

        if (percentualOcupacao >= 1.0) {
            return StatusLotacaoEnum.LOTADO; 
        } else if (percentualOcupacao >= 0.7) {
            return StatusLotacaoEnum.MODERADO; 
        } else {
            return StatusLotacaoEnum.LIVRE;
        }
    }

    

    public Onibus getOnibus() { 
        return onibus; 
    }

    public void setOnibus(Onibus onibus) 
    { 
        this.onibus = onibus; 

    }

    public Motorista getMotorista() { 
        return motorista; 
    }

    public void setMotorista(Motorista motorista) 
    { 
        this.motorista = motorista; 
    }

    public Linha getLinha() { 
        return linha; 
    }
    public void setLinha(Linha linha) {
       
        this.linha = linha; 

    }

    public Ponto getPontoAtual() { 
        return pontoAtual; 
    }

    public void setPontoAtual(Ponto pontoAtual) { 
        this.pontoAtual = pontoAtual; 
    }

    public List<Embarque> getPassageirosEmbarcados() { 
        return passageirosEmbarcados; 
    }

    public String getHorarioPartida() { 
        return horarioPartida; 
    }

    public void setHorarioPartida(String horarioPartida) { 
        this.horarioPartida = horarioPartida; 
    }

    public boolean isEmAndamento() { 
        return emAndamento; 
    }
    public void setEmAndamento(boolean emAndamento) { 
        this.emAndamento = emAndamento; 
    }
    public int getIndiceProximaParada() {
        return indiceProximaParada;
    }
    @Override
    public String toString() {
        return "Viagem da Linha: " + (linha != null ? linha.getNomeLinha() : "N/A") + 
               " | Ônibus: " + (onibus != null ? onibus.getPlaca() : "N/A");
    }
}
public void setIndiceProximaParada(int indiceProximaParada) {
        this.indiceProximaParada = indiceProximaParada;
    }
