package br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Viagem;
import br.edu.ifba.saj.fwads.dao.GenericDAO;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Embarque;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.StatusLotacaoEnum;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.CapacidadeExcedidaException;

import java.util.UUID;

public class EmbarqueService {

    /**
     * Tenta realizar o embarque de um passageiro.
     * @throws CapacidadeExcedidaException se o ônibus não tiver mais vagas.
     */
    public void registrarEmbarque(Viagem viagem, Embarque embarque) throws CapacidadeExcedidaException {
        // Encapsulamento: As regras de negócio ficam protegidas aqui
        int lotacaoAtual = viagem.getPassageirosEmbarcados().size();
        int capacidadeMaxima = viagem.getOnibus().getLotacaoMaxima();

        if (lotacaoAtual >= capacidadeMaxima) {
            throw new CapacidadeExcedidaException("Atenção! Lotação máxima do ônibus " 
                + viagem.getOnibus().getPlaca() + " foi atingida.");
        }

        // Se passou pela validação, efetivamos o embarque
        viagem.getPassageirosEmbarcados().add(embarque);

        // RF25 - Alerta de Lotação: Verifica o status após o embarque
        verificarAlertasDeLotacao(viagem);
    }

    private void verificarAlertasDeLotacao(Viagem viagem) {
        StatusLotacaoEnum status = viagem.calcularStatusLotacao();
        
        if (status == StatusLotacaoEnum.LOTADO) {
            System.out.println("[ALERTA SISTEMA] Ônibus " + viagem.getOnibus().getPlaca() + " está LOTADO!");
        } else if (status == StatusLotacaoEnum.MODERADO) {
            System.out.println("[ALERTA SISTEMA] Lotação moderada na linha " + viagem.getLinha().getNomeLinha());
        }
    }
    // Dependência da Camada de Dados (DAO)
    private GenericDAO<Embarque, UUID> embarqueDAO;

    // Construtor: O Service recebe o DAO pronto para usar
    public EmbarqueService(GenericDAO<Embarque, UUID> embarqueDAO) {
        this.embarqueDAO = embarqueDAO;
    }

    public void registarEmbarque(Viagem viagem, Embarque embarque) throws CapacidadeExcedidaException {
        // 1. Validar a Regra de Negócio (RF25 - Lotação)
        if (viagem.getPassageirosEmbarcados().size() >= viagem.getOnibus().getLotacaoMaxima()) {
            throw new CapacidadeExcedidaException("Lotação máxima atingida no autocarro " + viagem.getOnibus().getPlaca());
        }

        // 2. Comunicar com a Camada de Dados para guardar em memória
        embarqueDAO.salvar(embarque); 

        // 3. Atualizar o estado do Modelo
        viagem.getPassageirosEmbarcados().add(embarque);
    }
}

// Para o RF23
public class PontoInvalidoException extends Exception {
    public PontoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

// Para o RF24
public class DesvioRotaException extends Exception {
    public DesvioRotaException(String mensagem) {
        super(mensagem);
    }
}