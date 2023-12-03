package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusCurso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorCadastroCurso implements Initializable{
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField inputHoras;

    @FXML
    private TextField inputNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnVoltar));
        btnCadastrar.setOnAction( event -> cadastrarCurso());
    }

    public void cadastrarCurso(){
        String nome = inputNome.getText().trim();
        String horasText = inputHoras.getText().trim();
        int horas;
        if (nome.isEmpty() || horasText.isEmpty()) {
            Sistema.exibirPopupErro("Por favor, preencha todos os campos antes de cadastrar o curso.");
            return;
        }

        // Verifica se o campo inputHoras contém apenas números inteiros
        try {
            horas = Integer.parseInt(horasText);
            try {
            CursoDao cursoDao = new CursoDao(Conexao.getConnection());
            ProfessorDao professorDao = new ProfessorDao(Conexao.getConnection());
            Professor professor = professorDao.buscarPorNomeEEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            Curso curso = new Curso(nome, horas, professor, StatusCurso.ABERTO);
            cursoDao.cadastrar(curso);
            inputNome.clear();
            inputHoras.clear();
            Sistema.exibirPopupSucesso("O curso foi criado com sucesso!");
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            Sistema.exibirPopupErro("Valor inválido, O campo Horas deve conter apenas números inteiros.");
            return;
        }

        
    }
}
