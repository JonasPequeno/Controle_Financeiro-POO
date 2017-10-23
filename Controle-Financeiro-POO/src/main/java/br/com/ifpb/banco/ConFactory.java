package br.com.ifpb.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aguirre
 */
public class ConFactory {
    private final String host;
    private final String user;
    private final String password;
    
    public ConFactory(){
        this.host = "jdbc:postgresql://127.0.0.1:5432/Controle-Financeiro-POO";
        this.user = "postgres";
        this.password = "102495";
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(host, user, password);
    }
}