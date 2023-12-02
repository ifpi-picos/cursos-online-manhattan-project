package br.edu.ifpi;

import java.sql.Connection;

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

public class App extends Application{

    private Connection connection;
    private AlunoDao alunoDao;
    private ProfessorDao professorDao;
    private CursoDao cursoDao;
    private AlunoCursoDao alunoCursoDao;
    public SessaoDao sessaoDao;

    @Override
    public void init() throws Exception {
        this.connection = Conexao.getConnection();
        this.alunoDao = new AlunoDao(connection);
        this.professorDao = new ProfessorDao(connection);
        this.cursoDao = new CursoDao(connection);
        this.alunoCursoDao = new AlunoCursoDao(connection);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        sessaoDao = new SessaoDao(connection, alunoDao, professorDao, cursoDao, alunoCursoDao);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        SessaoController controller = loader.getController();

        if (controller instanceof SessaoController) {
            ((SessaoController) controller).getSessao(sessaoDao);
        }
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
