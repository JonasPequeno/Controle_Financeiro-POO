package br.com.ifpb.controle;

import java.util.List;

public interface Dao <T> {
    public boolean create(T o);
    public List<T> read();
    public boolean update(T o);
    public boolean delete(T o);
}