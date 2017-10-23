package br.com.ifpb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * Interface genérica utilizada para realizar operações elementares sobre
 * os dados.
 * @param <T> Representa meu tipo de parâmetro.
 * @author Aguirre e Jonas
 */

public interface Dao <T> {
    /**
     * Método create, cria um novo elemento.
     * @param o Elemento que será criado. 
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean create(T o) throws IOException, ClassNotFoundException, SQLException;

    /**
     *  Método read, faz a listagem de todos os elementos.
     * @return A lista de elementos salvos.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<T> read() throws IOException, ClassNotFoundException, SQLException;

    /**
     * Método update, atualiza um elemento da lista.
     * @param o O elemento que será atualizado. 
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean update(T o) throws IOException, ClassNotFoundException, SQLException;

    /**
     * Método delete, deleta um elemento da lista.
     * @param o O elemento que será deletado.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean delete(T o) throws IOException, ClassNotFoundException, SQLException;

}