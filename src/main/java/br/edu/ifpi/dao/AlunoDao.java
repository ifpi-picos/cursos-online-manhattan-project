package br.edu.ifpi.dao;
import java.sql.Connection;

import br.edu.ifpi.entities.Aluno;

public class AlunoDao implements Dao<Aluno>{
    private Connection  connection;

    public AlunoDao(Connection connection) {
        this.connection = connection;
    }

    
}
