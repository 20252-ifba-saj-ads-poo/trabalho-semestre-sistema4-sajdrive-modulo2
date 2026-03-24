package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.dao.GenericDAO;
import br.edu.ifba.saj.fwads.dao.GenericDAOImpl;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Viagem;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Embarque;
import br.edu.ifba.saj.fwads.service.ViagemService; // Mudamos para ViagemService
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.CapacidadeExcedidaException;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.PontoInvalidoException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.UUID;

public class SajDriveController {

    @FXML private Label lblStatusLotacao;
    @FXML private Button btnRegistrarEmbarque;

    private ViagemService viagemService; // Agora usamos o Service correto
    private Viagem viagemAtual; 

    @FXML
    public void initialize() {
        // Inicialização dos DAOs em memória para respeitar a Arquitetura
        GenericDAO<Embarque, UUID> embarqueDAO = new GenericDAOImpl<>(UUID.class);
        GenericDAO<Viagem, UUID> viagemDAO = new GenericDAOImpl<>(UUID.class);
        
        // Injeção de dependência: O Service agora tem capacidade de salvar dados!
        this.viagemService = new ViagemService(embarqueDAO, viagemDAO);
    }

    @FXML
    public void handleRegistrarEmbarque() {
        try {
            Embarque novoEmbarque = new Embarque(); 
            
            // Usamos o viagemService que lida com todos os requisitos (RF23, 24, 25)
            viagemService.registrarEmbarque(viagemAtual, novoEmbarque);
            
            lblStatusLotacao.setText("Status: " + viagemAtual.calcularStatusLotacao().toString());
            lblStatusLotacao.setStyle("-fx-text-fill: green;");

        // Capturamos DUAS exceções diferentes agora (Lotação e Ponto Inválido)
        } catch (CapacidadeExcedidaException | PontoInvalidoException e) {
            mostrarAlertaErro("Regra de Negócio Violada", e.getMessage());
            lblStatusLotacao.setStyle("-fx-text-fill: red;");
            
        } catch (Exception e) {
            mostrarAlertaErro("Erro Inesperado", "Ocorreu um erro ao processar o embarque.");
        }
    }

    private void mostrarAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}