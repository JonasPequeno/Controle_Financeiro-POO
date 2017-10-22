package br.com.ifpb.controle;

import java.io.IOException;
import java.util.List;

public interface Dao <T> {
    public boolean create(T o) throws IOException, ClassNotFoundException;
    public List<T> read() throws IOException, ClassNotFoundException;
    public boolean update(T o) throws IOException, ClassNotFoundException;
    public boolean delete(T o) throws IOException, ClassNotFoundException;
}