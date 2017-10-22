/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aguirre
 */
public class GenericArquivoDao<T> implements Dao<T>{
    
    private final File arquivo;
    private final File dir;
    
    public GenericArquivoDao() throws IOException{
        dir = new File("data");
        arquivo = new File("data/controle-transacoes.bin");
        
        if(!dir.exists()){
            dir.mkdir();
        }
        
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
    }

    @Override
    public boolean create(T o) throws IOException, ClassNotFoundException{
        List<T> elementos = this.read();
        
        if(elementos.add(o)){
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
            oos.writeObject(elementos);
            
            oos.close();
            return true;
        }
        return false;
        
    }

    @Override
    public List<T> read() throws IOException, ClassNotFoundException{
        List<T> elementos = null;
        
        if(arquivo.length() > 0){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
            
            elementos = (ArrayList<T>) ois.readObject();
            ois.close();
        }
        
        return elementos;
    }

    @Override
    public boolean update(T o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(T o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
