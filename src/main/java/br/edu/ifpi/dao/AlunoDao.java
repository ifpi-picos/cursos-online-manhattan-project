package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements Dao<Aluno>{
    private final Connection connection;

    public AlunoDao(Connection connection) {
        this.connection = connection;
    }
}
