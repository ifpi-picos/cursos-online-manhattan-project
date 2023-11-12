package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao implements Dao<Curso> {
    private final Connection connection;

    public CursoDao(Connection connection) {
        this.connection = connection;
    }
}