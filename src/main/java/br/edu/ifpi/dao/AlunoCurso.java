package br.edu.ifpi.dao;

import java.sql.Connection;

public class AlunoCurso{
    private Connection connection;

    public AlunoCurso(Connection connection) {
        this.connection = connection;
    }
}