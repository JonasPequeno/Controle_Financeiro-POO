package br.com.ifpb.controle;

import java.util.ArrayList;
import java.util.List;

public class GenericDao<T> implements Dao<T>{
    
    private List<T> elementos;

    public GenericDao() {
        elementos = new ArrayList<>();
    }

    @Override
    public boolean create(T o) {
        return elementos.add(o);
    }

    @Override
    public List<T> read() {
        return elementos;
    }

    @Override
    public boolean update(T o) {
       if(elementos.contains(o)){
           for(int i = 0; i < elementos.size(); i++){
               if(elementos.get(i).equals(o)){
                    elementos.remove(i);
                    elementos.add(i, o);
                    return true;
                }
            }
       }
       return false;
    }

    @Override
    public boolean delete(T o) {
        return elementos.remove(o);
    }
    
}