/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;

/**
 *
 * @author tais
 */
public class Exame {
    private long id;
    private String nome;
    private double valor;
    private String local; /*Usando o atributo convênio do banco*/
    private Timestamp data;

    public String getLocal() {
        return local;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return nome+": local="+local+", valor="+valor+", data="+data;
    }
}
