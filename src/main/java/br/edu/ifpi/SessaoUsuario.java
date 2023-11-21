package br.edu.ifpi;

public class SessaoUsuario {
    private static String nomeUsuario;
    public static String emailUsuario;
    private static String tipoUsuario; // Pode ser 'Aluno' ou 'Professor'
    
    
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