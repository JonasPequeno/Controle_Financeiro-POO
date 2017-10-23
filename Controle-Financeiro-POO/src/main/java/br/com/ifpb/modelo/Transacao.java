package br.com.ifpb.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe que contém todos os métodos de Transação.
 * @author Aguirre e Jonas
 */
public class Transacao implements Serializable{
    private String descricao, categoria;
    private LocalDate data;
    private double valor;
    private boolean tipo;
    private final boolean ENTRADA;
    private final boolean SAIDA;
    
    /**
     * Construtor de Transação.
     * @param descricao Descrição da transação.
     * @param categoria Categoria da transação.
     * @param data Data em que a transação foi criada.
     * @param valor O valor da transação.
     * @param tipo Tipo da transação.
     *              True: se for uma ENTRADA.
     *              False: se for uma SAIDA.
     */

    public Transacao(String descricao, String categoria, LocalDate data, double valor, boolean tipo) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
        this.ENTRADA = true;
        this.SAIDA = false;
        if(tipo == true){
            this.tipo = ENTRADA;
        }else{
            this.tipo = SAIDA;
        }
    }

    /**
     * Recupera a descrição da transação.
     * @return  Descrição.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Modifica a descrição da transação.
     * @param descricao Nova descrição.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Recupera a categoria da transação.
     * @return Categoria da transação.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modifica a categoria da transação.
     * @param categoria Nova categoria.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Recupera a data da transação.
     * @return Data da transação
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Modifica a data da transação.
     * @param data Nova data.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Recupera o valor da transação.
     * @return Valor da transação.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Modifica o valor da transação.
     * @param valor Novo valor.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Recupera o tipo da transação.
     * @return Tipo da transação.
     */
    public boolean getTipo() {
        return tipo;
    }

    /**
     * Modifica o tipo da transação
     * @param tipo Novo tipo.
     */
    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.descricao);
        hash = 67 * hash + Objects.hashCode(this.categoria);
        hash = 67 * hash + Objects.hashCode(this.data);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 67 * hash + (this.tipo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transacao other = (Transacao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transacao{" + "descricao=" + descricao + ", categoria=" + categoria + ", data=" + data + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
}