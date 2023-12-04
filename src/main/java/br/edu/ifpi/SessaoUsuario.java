package br.edu.ifpi;


public class SessaoUsuario {
    public static String nomeUsuario;
    public static String emailUsuario;
    public static String tipoUsuario;

    public static String getEmailUsuario() {
        return emailUsuario;
    }
    
    public static void setEmailUsuario(String emailUsuario) {
        SessaoUsuario.emailUsuario = emailUsuario;
    }
    
    public static String getNomeUsuario() {
        return nomeUsuario;
    }
    
    public static void setNomeUsuario(String nome) {
        nomeUsuario = nome;
    }
    
    public static String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public static void setTipoUsuario(String tipo) {
        tipoUsuario = tipo;
    }
}