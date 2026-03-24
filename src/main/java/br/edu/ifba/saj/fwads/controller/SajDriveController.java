package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Viagem;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.Embarque;
import br.edu.ifba.saj.fwads.service.EmbarqueService;
import br.edu.ifba.saj.fwads.model.gestaoEmbarque.exceptions.CapacidadeExcedidaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class SajDriveController {

    // Componentes mapeados do seu arquivo FXML (ex: Monitoramento.fxml)
    @FXML private Label lblStatusLotacao;
    @FXML private Button btnRegistrarEmbarque;

    private EmbarqueService embarqueService;
    private Viagem viagemAtual; // Dados carregados simulando banco em memória

    @FXML
    public void initialize() {
        // Inicializações simuladas para rodar o exemplo
        this.embarqueService = new EmbarqueService();
        // Aqui você buscaria a viagem do seu banco de dados em memória (GenericDAO)
    }

    @FXML
    public void handleRegistrarEmbarque() {
        try {
            Embarque novoEmbarque = new Embarque(); // Preencheria com dados da tela
            
            // Passamos a responsabilidade para a camada de Negócio
            embarqueService.registrarEmbarque(viagemAtual, novoEmbarque);
            
            // Atualiza a UI se o embarque for bem sucedido
            lblStatusLotacao.setText("Status: " + viagemAtual.calcularStatusLotacao().toString());
            lblStatusLotacao.setStyle("-fx-text-fill: green;");

        } catch (CapacidadeExcedidaException e) {
            // Separação de responsabilidades: A interface só mostra o erro que o Negócio identificou
            mostrarAlertaErro("Falha no Embarque", e.getMessage());
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