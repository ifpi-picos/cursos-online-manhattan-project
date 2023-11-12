package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements Dao<Professor> {
    private final Connection connection;

    public ProfessorDao(Connection connection) {
        this.connection = connection;
    }
}