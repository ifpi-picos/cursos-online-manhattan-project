package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
    private Button finalizarCadastro;

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
    private Button voltarBotao;

    @FXML
    private ChoiceBox<String> statusCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Adicione opções à ChoiceBox
        statusCurso.getItems().addAll( "Aberto", "Fechado");

        // Configura o TextFormatter para aceitar apenas números
        inputCargaHoraria.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> {
                    if (c.getControlNewText().matches("\\d*")) {
                        return c;
                    } else {
                        return null;
                    }
         }));

        //Desativa uma cena e ativa a outra com base nos botões
        CadastrarCursoBotao.setOnAction(event -> trocarParaAdmCadastroCurso());
        voltarBotao.setOnAction(event -> voltarBotaoAction(event));     
        finalizarCadastro.setOnAction(event -> getNovoCurso());

        // Inicializa carregando o AnchorPane AdmCursosInicial
        trocarParaAdmCursosInicial();
    }

    private void trocarParaAdmCadastroCurso() {
        AdmCursosInicial.setDisable(true);
        AdmCursosInicial.setVisible(false);

        AdmCadastroCurso.setDisable(false);
        AdmCadastroCurso.setVisible(true);
    }

    @FXML
    private void voltarBotaoAction(ActionEvent event) {
        AdmCadastroCurso.setDisable(true);
        AdmCadastroCurso.setVisible(false);

        AdmCursosInicial.setDisable(false);
        AdmCursosInicial.setVisible(true);
    }

    private void trocarParaAdmCursosInicial() {
        AdmCursosInicial.setDisable(false);
        AdmCursosInicial.setVisible(true);

        AdmCadastroCurso.setDisable(true);
        AdmCadastroCurso.setVisible(false);
    }

    private void getNovoCurso(){
        String nomeCurso = inputNomeCurso.getText();
        String cargaHoraria = inputCargaHoraria.getText();
        String status = statusCurso.getValue();

        inputNomeCurso.clear();
        inputCargaHoraria.clear();
        statusCurso.getSelectionModel().clearSelection();

        System.out.println( "Nome do curso:" + nomeCurso);
        System.out.println("Carga Horária:" + cargaHoraria + " Horas");
        System.out.println("Status do curso:" + status);

        
    }
}
