package br.com.ifpb.visao;

import br.com.ifpb.controle.Dao;
import br.com.ifpb.modelo.Transacao;
import br.com.ifpb.modelo.Usuario;
import br.com.ifpb.telas.TelaLogin;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner scan;
    
    private static Dao<Usuario> usuariosDao;
    private static Usuario logado;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        TelaLogin telaLogin = new TelaLogin();
    }
}