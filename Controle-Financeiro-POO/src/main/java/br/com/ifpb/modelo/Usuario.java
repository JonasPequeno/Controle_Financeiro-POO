package br.com.ifpb.modelo;

import br.com.ifpb.controle.Autenticavel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Autenticavel, Serializable{
    private static int idCont = 0;
    private int id;
    private String nome, email, senha, sexo;
    private LocalDate nascimento;
    private List<Transacao> transacoes;

    public Usuario(String nome, String email, String senha, String sexo, LocalDate nascimento) {
        this.id = idCont++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.transacoes = new ArrayList<>();
    }
    
    public boolean addTransacao(Transacao transacao){
        return transacoes.add(transacao);
    }
    
    public List<Transacao> listarTransacoes(){
        return this.transacoes;
    }
    
    public boolean editarTransacao(int pos, Transacao transacao){
        int posReal = pos - 1; //no array a posicao inicia em zero
        return transacoes.set(posReal, transacao) != null;
    }
    
    public boolean removerTransacao(int pos){
        int posReal = pos -1;
        return transacoes.remove(posReal) != null;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.email);        
        hash = 47 * hash + Objects.hashCode(this.sexo);
        hash = 47 * hash + Objects.hashCode(this.nascimento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", senha=" + senha + ", sexo=" + sexo + ", nascimento=" + nascimento + '}';
    }   

    @Override
    public boolean autentica(String email, String senha) {
        return email.equals(this.getEmail()) && senha.equals(this.getSenha());
    }
}
