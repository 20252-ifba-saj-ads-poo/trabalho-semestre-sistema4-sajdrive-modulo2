package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.dao.GenericDAO;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Viagem;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Embarque;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Ponto;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.ParadaOnibus;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.CapacidadeExcedidaException;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.PontoInvalidoException;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.DesvioRotaException;

import java.util.UUID;

public class ViagemService {

    private GenericDAO<Embarque, UUID> embarqueDAO;
    private GenericDAO<Viagem, UUID> viagemDAO;

    // Construtor com injeção de dependências da Camada de Dados
    public ViagemService(GenericDAO<Embarque, UUID> embarqueDAO, GenericDAO<Viagem, UUID> viagemDAO) {
        this.embarqueDAO = embarqueDAO;
        this.viagemDAO = viagemDAO;
    }

    /**
     * RF23 (Alerta de Embarque) e RF25 (Alerta de Lotação)
     */
    public void registrarEmbarque(Viagem viagem, Embarque embarque) throws CapacidadeExcedidaException, PontoInvalidoException {
        
        // --- 1. Validação do RF23 (Alerta de Embarque) ---
        // Verifica se os pontos de origem e destino informados existem na Linha desta Viagem
        boolean origemValida = validarPontoNaLinha(viagem, embarque.getPontoOrigem());
        boolean destinoValido = validarPontoNaLinha(viagem, embarque.getPontoDestino());

        if (!origemValida) {
            throw new PontoInvalidoException("Alerta de Embarque: O ponto de origem não pertence à linha " + viagem.getLinha().getNomeLinha());
        }
        if (!destinoValido) {
            throw new PontoInvalidoException("Alerta de Embarque: O ponto de destino não pertence à linha " + viagem.getLinha().getNomeLinha());
        }

        // --- 2. Validação do RF25 (Alerta de Lotação) ---
        if (viagem.getPassageirosEmbarcados().size() >= viagem.getOnibus().getLotacaoMaxima()) {
            throw new CapacidadeExcedidaException("Alerta de Lotação: Capacidade máxima atingida no ônibus " + viagem.getOnibus().getPlaca());
        }

        // --- 3. Efetivação e Persistência ---
        embarqueDAO.salvar(embarque);
        viagem.getPassageirosEmbarcados().add(embarque);
        viagemDAO.salvar(viagem); // Atualiza a viagem na memória com o novo passageiro
    }

    /**
     * RF24 (Alerta de Trajeto)
     */
    public void atualizarLocalizacao(Viagem viagem, Ponto novoPonto) throws DesvioRotaException {
        
        // --- Validação do RF24 (Alerta de Trajeto) ---
        // Se o motorista registrar passagem num ponto que não está na linha, é um desvio.
        boolean pontoPertenceARota = validarPontoNaLinha(viagem, novoPonto);

        if (!pontoPertenceARota) {
            throw new DesvioRotaException("ALERTA DE TRAJETO: O ônibus desviou da rota oficial! O ponto atual não faz parte da linha.");
        }

        // --- Efetivação e Persistência ---
        viagem.setPontoAtual(novoPonto);
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
}

public void atualizarLocalizacao(Viagem viagem, Ponto novoPonto) throws DesvioRotaException {
    
    // 1. Obtém a lista de paradas da linha oficial do ônibus
    List<ParadaOnibus> paradasDaLinha = viagem.getLinha().getParadas();
    
    // 2. Descobre qual deveria ser a próxima parada esperada
    // (Você precisará criar os métodos get e set desse índice na classe Viagem)
    int indiceEsperado = viagem.getIndiceProximaParada(); 
    
    // 3. Valida se a viagem já acabou OU se o novo Ponto NÃO é o Ponto da parada esperada
    if (indiceEsperado >= paradasDaLinha.size() || 
        !paradasDaLinha.get(indiceEsperado).getPonto().getId().equals(novoPonto.getId())) {
        
        throw new DesvioRotaException("ALERTA DE TRAJETO: O ônibus desviou da linha ou pulou um ponto oficial!");
    }

    // 4. Se a ordem estiver correta, atualiza o Ponto Atual do ônibus e avança para a próxima parada
    viagem.setPontoAtual(novoPonto);
    viagem.setIndiceProximaParada(indiceEsperado + 1); // Prepara para a próxima leitura
    
    // 5. Salva a atualização na Camada de Dados (Memória)
    viagemDAO.salvar(viagem);
}