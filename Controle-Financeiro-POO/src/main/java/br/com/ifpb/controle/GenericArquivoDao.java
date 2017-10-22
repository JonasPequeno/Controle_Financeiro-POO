/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            this.escreverNoArquivo(elementos);
            return true;
        }
        return false;
        
    }

    @Override
    public List<T> read() throws IOException, ClassNotFoundException{
        List<T> elementos = null;
        
        if(arquivo.length() > 0){
            
            elementos = (ArrayList<T>) this.lerDoArquivo();
        }
        
        return elementos;
    }

    @Override
    public boolean update(T o) throws IOException, ClassNotFoundException{
        List<T> elementos = this.read();
        
        if(elementos.contains(o)){
           for(int i = 0; i < elementos.size(); i++){
               if(elementos.get(i).equals(o)){
                    elementos.remove(i);
                    elementos.add(i, o);
                    this.escreverNoArquivo(elementos);
                    return true;
                }
            }
       }
        return false;        
    }

    @Override
    public boolean delete(T o) throws IOException, ClassNotFoundException{
        List<T> elementos = this.read();
        if(elementos.remove(o)){
            this.escreverNoArquivo(elementos);
            return true;
        }
        return false;
    }
    
    private void escreverNoArquivo(Object o) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
        oos.writeObject(o);
            
        oos.close();
    }
    
    private Object lerDoArquivo() throws FileNotFoundException, IOException, ClassNotFoundException{
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
       
       Object obj = ois.readObject();
       ois.close();
       
       return obj;        
    }
    
}
