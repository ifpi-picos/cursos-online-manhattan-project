package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class controladorPerfilAluno implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerNotas;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<?, ?> colunaNomeCursos;

    @FXML
    private TableView<?> cursosCadastrado;

    @FXML
    private Text textEmail;

    @FXML
    private Text textNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNomeCursos.setCellValueFactory(new PropertyValueFactory<>("nome"));

        textNome.setText(SessaoUsuario.getNomeUsuario());
        textEmail.setText(SessaoUsuario.getEmailUsuario());

        // try (Connection connection = Conexao.getConnection()) {
        //     CursoDao cursoDao = new CursoDao(connection);


        //     // Configurar a lista de cursos na tabela
        //     cursosCadastrado.getItems().setAll(listaCursos);
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/cursosAluno.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilAluno.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnVoltar));
    }

}
