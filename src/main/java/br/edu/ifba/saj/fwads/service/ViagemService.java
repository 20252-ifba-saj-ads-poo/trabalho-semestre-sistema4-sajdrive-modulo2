package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.dao.GenericDAO;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Viagem;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Embarque;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Ponto;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.ParadaOnibus;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.CapacidadeExcedidaException;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.PontoInvalidoException;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.DesvioRotaException;

import java.util.List;
import java.util.UUID;

public class ViagemService {

    private GenericDAO<Embarque, UUID> embarqueDAO;
    private GenericDAO<Viagem, UUID> viagemDAO;

    // Construtor com injeção de dependências da Camada de Dados
    public ViagemService(GenericDAO<Embarque, UUID> embarqueDAO, GenericDAO<Viagem, UUID> viagemDAO) {
        this.embarqueDAO = embarqueDAO;
        this.viagemDAO = viagemDAO;
    }

    public void registrarEmbarque(Viagem viagem, Embarque embarque) throws CapacidadeExcedidaException, PontoInvalidoException {
        
        // Verifica se os pontos de origem e destino informados existem na Linha desta Viagem
        boolean origemValida = validarPontoNaLinha(viagem, embarque.getPontoOrigem());
        boolean destinoValido = validarPontoNaLinha(viagem, embarque.getPontoDestino());

        if (!origemValida) {
            throw new PontoInvalidoException("Alerta de Embarque: O ponto de origem não pertence à linha " + viagem.getLinha().getNomeLinha());
        }
        if (!destinoValido) {
            throw new PontoInvalidoException("Alerta de Embarque: O ponto de destino não pertence à linha " + viagem.getLinha().getNomeLinha());
        }

        if (viagem.getPassageirosEmbarcados().size() >= viagem.getOnibus().getLotacaoMaxima()) {
            throw new CapacidadeExcedidaException("Alerta de Lotação: Capacidade máxima atingida no ônibus " + viagem.getOnibus().getPlaca());
        }

        embarqueDAO.salvar(embarque);
        viagem.getPassageirosEmbarcados().add(embarque);
        viagemDAO.salvar(viagem); // Atualiza a viagem na memória com o novo passageiro
    }

     public void atualizarLocalizacao(Viagem viagem, Ponto novoPonto) throws DesvioRotaException {
    
    List<ParadaOnibus> paradasDaLinha = viagem.getLinha().getParadas();
    
    int indiceEsperado = viagem.getIndiceProximaParada(); 
    
    if (indiceEsperado >= paradasDaLinha.size() || 
        !paradasDaLinha.get(indiceEsperado).getPonto().getId().equals(novoPonto.getId())) {
        
        throw new DesvioRotaException("ALERTA DE TRAJETO: O ônibus desviou da linha ou pulou um ponto oficial!");
    }

    viagem.setPontoAtual(novoPonto);
    viagem.setIndiceProximaParada(indiceEsperado + 1); // Prepara para a próxima leitura
    
    viagemDAO.salvar(viagem);
}

    // Método utilitário privado (Encapsulamento da lógica de busca)
    private boolean validarPontoNaLinha(Viagem viagem, Ponto pontoProcurado) {
        if (pontoProcurado == null || viagem.getLinha() == null || viagem.getLinha().getParadas() == null) {
            return false;
        }
        
        // Percorre as paradas da linha para ver se o ponto existe nela
        for (ParadaOnibus parada : viagem.getLinha().getParadas()) {
            if (parada.getPonto().getId().equals(pontoProcurado.getId())) {
                return true;
            }
        }
        return false;
    }
