package br.com.ifpb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
/**
 * Interface Dao 
 * @param <T> 
 */

public interface Dao <T> {
    /**
     * Método create  , cria um novo elemento.
     * @param o 
     * @return boolean
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public boolean create(T o) throws IOException, ClassNotFoundException, SQLException;
    /**
     *  Método List , faz a listagem de todos os elementos.
     * @return boolean
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public List<T> read() throws IOException, ClassNotFoundException, SQLException;
    /**
     * Método update , atualiza um elemento da lista.
     * @param o 
     * @return boolean 
     */
    public boolean update(T o) throws IOException, ClassNotFoundException, SQLException;
    /**
     * Método delete , deleta um elemento da lista.
     * @param o
     * @return boolean
     */
    public boolean delete(T o) throws IOException, ClassNotFoundException, SQLException;
}