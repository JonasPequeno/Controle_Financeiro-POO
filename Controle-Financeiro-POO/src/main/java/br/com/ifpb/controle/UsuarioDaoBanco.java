package br.com.ifpb.controle;

import br.com.ifpb.banco.ConFactory;
import br.com.ifpb.modelo.Transacao;
import br.com.ifpb.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe usada para fazer a persitência de usuario no banco de dados.
 * @author aguirre
 */
public class UsuarioDaoBanco implements Dao<Usuario> {
    
    private Connection con;
    
    /**
     * Construtor default da classe. Ao ser chamado, ele tenta realizar a conexão com o banco de dados.
     */
    public UsuarioDaoBanco(){
        try {
            con = new ConFactory().getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Falha na conexão com o banco",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Salva um usuário no banco de dados.
     * @param o Usuário que será salvo.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean create(Usuario o) throws IOException, ClassNotFoundException, SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario(id, nome, email, senha, sexo, nascimento)"
                                                        + "VALUES(?, ?, ?, ?, ?, ?);");
        
        stmt.setInt(1, o.getId());
        stmt.setString(2, o.getNome());
        stmt.setString(3, o.getEmail());
        stmt.setString(4, o.getSenha());
        stmt.setString(5, o.getSexo());
        stmt.setDate(6, Date.valueOf(o.getNascimento()));
        
        return stmt.executeUpdate() > 0;
    }
    
    /**
     * Lista todos os usuários salvos no banco.
     * @return Todos os usuários salvos.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public List<Usuario> read() throws IOException, ClassNotFoundException, SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * from usuario");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setSexo(rs.getString(5));
            usuario.setNascimento(rs.getDate(6).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            usuario.addAllTransacoes(this.getTransacoesUsuario(usuario.getId()));
            
            usuarios.add(usuario);
        }
        
        return usuarios;
    }
    
    /**
     * Atualiza um usuário do banco.
     * @param o Usuário que terá suas informações atualizadas.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean update(Usuario o) throws IOException, ClassNotFoundException, SQLException {
        PreparedStatement stmt =  con.prepareStatement("UPDATE usuario SET Nome = ?, Email = ?, Senha = ?, Sexo = ?, Nascimento = ? WHERE id = ?");
        
        stmt.setString(1, o.getNome());
        stmt.setString(2, o.getEmail());
        stmt.setString(3, o.getSenha());
        stmt.setString(4, o.getSexo());
        stmt.setDate(5, Date.valueOf(o.getNascimento()));
        stmt.setInt(6, o.getId());
        
        return stmt.executeUpdate() > 0;
    }
    
    /**
     * Deleta algum usuário do banco.
     * @param o Usuário que deve ser deletado.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @Override
    public boolean delete(Usuario o) throws IOException, ClassNotFoundException, SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario u WHERE u.id = ?");
        
        stmt.setInt(1, o.getId());
        
        return stmt.executeUpdate() > 0;
    }
    
    /**
     * Método privado da classe que permite recuperar todas as transações de um usuário a partir do seu id.
     * @param id ID do usuário para o qual devem ser recuperadas suas transações.
     * @return A lista de transações do usuário.
     * @throws SQLException 
     */
    private List<Transacao> getTransacoesUsuario(int id) throws SQLException{
        List<Transacao> transacoes = new ArrayList<>();
        
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM transacao t WHERE t.idUsuario = ?");
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            Transacao transacao = new Transacao(rs.getString("descricao"), rs.getString("categoria"), 
                                                rs.getDate("data").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                rs.getFloat("valor"), rs.getBoolean("tipo"));
            transacoes.add(transacao);
        }
        
        return transacoes;
    }
    
}
