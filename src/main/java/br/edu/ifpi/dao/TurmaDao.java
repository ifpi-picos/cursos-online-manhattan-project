package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Turma;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDao implements Dao<Turma> {
    private Connection connection;

    public TurmaDao(Connection connection) {
        this.connection = connection;
    }
}