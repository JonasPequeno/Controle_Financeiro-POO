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
 * Classe usada para realizar as operações de CRUD, utilizando persistência em arquivos.
 * @param T Representa o tipo de parâmetro.
 * @author aguirre
 */
public class GenericArquivoDao<T> implements Dao<T>{
    
    private final File arquivo;
    private final File dir;
    
    /**
     * Construtor default da classe.
     * Cria um diretório com o arquivo utilizado para a persistência na pasta raíz do projeto caso não existam.
     * @throws IOException 
     */
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
    /**
     * 
     * Salva um elemento no arquivo.
     * @param o Elemento que será salvo.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
       public boolean create(T o) throws IOException, ClassNotFoundException{
        List<T> elementos = this.read();
        
        if(elementos.add(o)){
            this.escreverNoArquivo(elementos);
            return true;
        }
        return false;
        
    }
    
    /**
     * Lista todos os elementos salvos no arquivo.
     * @return Todos os elementos salvos no arquivo.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
    public List<T> read() throws IOException, ClassNotFoundException{
        List<T> elementos = null;
        
        if(arquivo.length() > 0){
            
            elementos = (ArrayList<T>) this.lerDoArquivo();
        }
        
        return elementos;
    }
    
    /**
     * Atualiza um elemento salvo no arquivo.
     * @param o Elemento que será atualizado.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
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
    
    /**
     * Deleta um elemento do arquivo.
     * @param o Elemento que será deletado.
     * @return True caso a operação seja realizada com sucesso e False no caso contrário.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
    public boolean delete(T o) throws IOException, ClassNotFoundException{
        List<T> elementos = this.read();
        if(elementos.remove(o)){
            this.escreverNoArquivo(elementos);
            return true;
        }
        return false;
    }
    
    /**
     * Método privado da classe que permite realizar a escrita sobre o arquivo.
     * @param o Elemento que será escrito no arquivo.
     * @throws IOException 
     */
    private void escreverNoArquivo(Object o) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo));
        oos.writeObject(o);
            
        oos.close();
    }
    
    /**
     * Método privado da classe que permite ler do arquivo.
     * @return Elemento lido do arquivo, caso exista.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private Object lerDoArquivo() throws FileNotFoundException, IOException, ClassNotFoundException{
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo));
       
       Object obj = ois.readObject();
       ois.close();
       
       return obj;        
    }
    
}
