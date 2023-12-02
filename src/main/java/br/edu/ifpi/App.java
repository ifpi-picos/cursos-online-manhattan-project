package br.edu.ifpi;

import java.sql.Connection;

import br.edu.ifpi.controllers.ControladorLogIn;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application{

    private Connection connection;
    private AlunoDao alunoDao;
    private ProfessorDao professorDao;
    private CursoDao cursoDao;
    private AlunoCursoDao alunoCursoDao;
    public SessaoDao sessaoDao;

    @Override
    public void init() throws Exception {
        connection = Conexao.getConnection();
        alunoDao = new AlunoDao(connection);
        professorDao = new ProfessorDao(connection);
        cursoDao = new CursoDao(connection);
        alunoCursoDao = new AlunoCursoDao(connection);

        sessaoDao = new SessaoDao(connection, alunoDao, professorDao, cursoDao, alunoCursoDao);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        ControladorLogIn controladorLogIn = new ControladorLogIn(sessaoDao);
        loader.setController(controladorLogIn);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() throws Exception {
        Conexao.closeConnection();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
