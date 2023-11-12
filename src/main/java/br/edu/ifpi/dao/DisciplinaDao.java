package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Disciplina;
import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDao implements Dao<Disciplina> {
    private final Connection connection;

    public DisciplinaDao(Connection connection) {
        this.connection = connection;
    }
}