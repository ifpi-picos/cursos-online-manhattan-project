package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

public class ControladorTelaCursosAdm implements Initializable{
    @FXML
    private AnchorPane AdmCadastroCurso;

    @FXML
    private AnchorPane AdmCursosInicial;

    @FXML
    private AnchorPane Background;

    @FXML
    private Button CadastrarCursoBotao;

    @FXML
    private Button alunosBotao;

    @FXML
    private Button configuracoesBotao;

    @FXML
    private Button cursosBotao;

    @FXML
    private Button detalhesCurso;

    @FXML
    private Button editarCursoBotao;

    @FXML
    private Button excluirCursoBotao;

    @FXML
    private AnchorPane formulario;

    @FXML
    private Button homeBotao;

    @FXML
    private TextField inputCargaHoraria;

    @FXML
    private TextField inputNomeCurso;

    @FXML
    private Button permissoesContasbotao;

    @FXML
    private Button professoresBotao;

    @FXML
    private ChoiceBox<String> statusCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Adicione opções à ChoiceBox
        statusCurso.getItems().addAll("Selecione o status", "Aberto", "Fechado");
        statusCurso.getSelectionModel().selectFirst();
        statusCurso.getSelectionModel().clearSelection();

        // Configura o TextFormatter para aceitar apenas números
        inputCargaHoraria.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> {
                    if (c.getControlNewText().matches("\\d*")) {
                        return c;
                    } else {
                        return null;
                    }
                }));

    }

    
}
