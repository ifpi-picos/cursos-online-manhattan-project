package br.edu.ifpi.controllers;

import br.edu.ifpi.SessaoDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControladorTelaInicialAluno {

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

    private SessaoDao sessaoDao;
    
    public ControladorTelaInicialAluno (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

}

