package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import br.edu.ifpi.entities.Aluno;

public class controladorModalCadastroNotas {

    @FXML
    private TextField inputNota1;

    @FXML
    private TextField inputNota2;

    @FXML
    private TextField inputNota3;

    @FXML
    private AnchorPane nodalBackground;

    @FXML
    private Button btnCadastrar;

    private Aluno alunoSelecionado;

    public void inicializar(Aluno aluno) {
        this.alunoSelecionado = aluno;
    }

    @FXML
    private void handleCadastrar() {
        String textoNota1 = inputNota1.getText();
        String textoNota2 = inputNota2.getText();
        String textoNota3 = inputNota3.getText();

    }
}