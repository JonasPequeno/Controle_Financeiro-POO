package br.com.ifpb.controle;

import java.io.IOException;
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
    public boolean create(T o) throws IOException, ClassNotFoundException;
    /**
     *  Método List , faz a listagem de todos os elementos.
     * @return boolean
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public List<T> read() throws IOException, ClassNotFoundException;
    /**
     * Método update , atualiza um elemento da lista.
     * @param o 
     * @return boolean 
     */
    public boolean update(T o);
    /**
     * Método delete , deleta um elemento da lista.
     * @param o
     * @return boolean
     */
    public boolean delete(T o);
}