package br.com.ifpb.visao;

import br.com.ifpb.controle.Dao;
import br.com.ifpb.controle.GenericDao;
import br.com.ifpb.modelo.Transacao;
import br.com.ifpb.modelo.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner scan = new Scanner(System.in);
    
    private static Dao<Usuario> usuariosDao = new GenericDao<>();
    private static Usuario logado = null;
       
    private static void menuLogin(){
        System.out.println(" 1 - LOGIN ");
        System.out.println(" 2 - CADASTRA-SE");
        System.out.println(" 3 - SAIR");
    }
    
    private static void menuAtualizaCadastro(){
        System.out.println("O que deseja atualizar?\n"
                + "1. Email\n"
                + "2. Nome\n"
                + "3. Data de nascimento\n"
                + "4. Sexo\n"
                + "5. Senha\n"
                + "6. Voltar");
    }
    
    private static void menuPrincipal(){
        System.out.println("Bem vindo");
    }
    
    private static Usuario login(){
        System.out.println("Realize seu login\n"
                + "Email:");
        String email = scan.next();
        
        List<Usuario> usuarios = usuariosDao.read();
        
        for(Usuario user : usuarios){
            if(email.equals(user.getEmail())){
                System.out.println("Senha");
                String senha = scan.next();
                if(senha.equals(user.getSenha())){
                    return user;
                }
            }
        }
        
        return null;
    }
    
    private static Usuario cadastraUsuario(){
        
        System.out.println("EMAIL :");
        String email = scan.next();
        
        System.out.println("NOME :");
        String nome = scan.next();
        
        System.out.println("NASCIMENTO :");
        String nascimento = scan.next();      
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(nascimento, formatador);
        
        System.out.println("SEXO:");
        String sexo = scan.next();
        
        System.out.println("SENHA:");
        String senha = scan.next();
        
        System.out.println("CONFIRMAR SENHA :");
        String confirmaSenha = scan.next();
        
        return new Usuario(nome,email,senha, sexo,data);
    }
    
    private static Transacao cadastraTransacao(){
        
        System.out.println("Descrição:");
        String descricao = scan.next();
        
        System.out.println("Data (dd/MM/yyy):");
        String data = scan.next();      
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataTransacao = LocalDate.parse(data, formatador);
        
        System.out.println("Valor:");
        double valor = scan.nextDouble();
        
        System.out.println("Tipo: \n"
                + "Digite (-1) para uma movimentação de saída\n"
                + "Digite (1) para uma movimentação de entrada");
        int valorTipo = scan.nextInt();
        boolean tipo = true;
        if(valorTipo == -1){
            tipo = false;
        }
        
        System.out.println("Categoria:");
        String categoria = scan.next();
        
        
        return new Transacao(descricao, categoria, dataTransacao, valor, tipo);
    }
    
    private static void atualizarUsuario(Usuario usuario, Dao<Usuario> usuarios){
        
        menuAtualizaCadastro();
        int opcao = scan.nextInt();
        
        while(opcao != 6){
            
            switch(opcao){
                case 1:
                    System.out.println("Digite o email:");
                    String email = scan.next();
                    usuario.setEmail(email);
                    break;
                case 2:
                    System.out.println("Digite o nome:");
                    String nome = scan.next();
                    usuario.setNome(nome);
                    break;
                case 3:
                    System.out.println("Digite o Data de nascimento:");
                    String nascimento = scan.next();
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(nascimento, formatador);
                    usuario.setNascimento(data);
                    break;
                case 4:
                    System.out.println("Digite o sexo:");
                    String sexo = scan.next();
                    usuario.setSexo(sexo);
                    break;
                case 5:
                    System.out.println("Digite a senha:");
                    String senha = scan.next();
                    String confirmeSenha = scan.next();
                    if(senha.equals(confirmeSenha)){
                        usuario.setSenha(senha);
                    }                
                    break;
                case 6:
                    menuPrincipal();
                    break;
            }
            
            System.out.println("1 - Fazer outra alteração\n"
                    + "2 - Voltar");
            opcao = scan.nextInt();
            if(opcao == 1){
                menuAtualizaCadastro();
                opcao = scan.nextInt();
                continue;
            }else{
                break;
            }                
        }              
    }
    
    
    public static void main(String[] args){
        
         boolean isFuncionando = true;

        while(isFuncionando){
            menuLogin();
        
            int opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    logado = login();
                    break;          
                case 2:
                    if(usuariosDao.create(cadastraUsuario())){
                        System.out.println("\nCadastro realizado com sucesso\n");
                        logado = login();
                    }
                    break;
                case 3:
                    isFuncionando = false;
                    continue;
            }

            if(logado == null){
                System.out.println("Email ou senha errados");
                continue;
            }else{
                boolean isLogado = true;
                while(isLogado){
                    menuPrincipal();
                    opcao = scan.nextInt();
                    switch(opcao){
                        case 1:
                            System.out.println("transacao cadastrada");
                            continue;
                        case 2:
                            System.out.println("gerenciar finanças");
                            continue;
                        case 3:
                            System.out.println("gerenciar perfil");
                            continue;
                        case 4:
                            isLogado = false;
                            break;
                    }
                }
            }
        }
    }    
}