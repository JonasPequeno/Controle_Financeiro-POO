package br.com.ifpb.visao;

import br.com.ifpb.controle.Dao;
import br.com.ifpb.controle.GenericDao;
import br.com.ifpb.modelo.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Dao<Usuario> usuarios = new GenericDao<>(); 
        
        
        Scanner scan = new Scanner(System.in);
        System.out.println(" 1 - LOGIN ");
        System.out.println(" 2 - CADASTRA-SE");
        System.out.println(" 3 - SAIR");
        int opcao = scan.nextInt();
        boolean opcaoV = true;
        
        while(opcaoV){
        
            switch(opcao){
            case 1 :{
                      System.out.println(" EMAIL :");
                      String email = scan.nextLine();
                      System.out.println(" SENHA :");
                      String senha = scan.nextLine();
                                         
                break;
            }
            case 2 :{
                
                    System.out.println("EMAIL :");
                    String email = scan.next();
                    System.out.println("NOME :");
                    String nome = scan.next();
                    System.out.println("NASCIMENTO :");
                    String nascimento = scan.next();      
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(nascimento, formatador);
                    System.out.println(data);      
                    System.out.println("SEXO:");
                    String sexo = scan.next();
                    System.out.println("SENHA:");
                    String senha = scan.next();
                    System.out.println("CONFIRMAR SENHA :");
                    String confirmaSenha = scan.next();
                    Usuario user = new Usuario(nome,email,senha, sexo,data);
                    usuarios.create(user);                                       
                    break;
            }
            case 3 :{
                
               break;
            }
            default:{
                 
            }
        }
        if(opcao != 3){
        opcao = scan.nextInt();
        }else opcaoV = false;  
        }
    }
    
}
