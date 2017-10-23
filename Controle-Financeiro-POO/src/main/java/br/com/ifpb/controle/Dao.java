package br.com.ifpb.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

public interface Dao <T> {
    public boolean create(T o) throws IOException, ClassNotFoundException, SQLException;
    public List<T> read() throws IOException, ClassNotFoundException, SQLException;
    public boolean update(T o) throws IOException, ClassNotFoundException, SQLException;
    public boolean delete(T o) throws IOException, ClassNotFoundException, SQLException;
}