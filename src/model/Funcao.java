/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Funcao {
    private String nomeFn;

    public Funcao(String nomeFn) {
        this.nomeFn = nomeFn;
    }

    public String getNomeFn() {
        return nomeFn;
    }

    public void setNomeFn(String nomeFn) {
        this.nomeFn = nomeFn;
    }

    @Override
    public String toString() {
        return "Funcao{" + "nomeFn=" + nomeFn + '}';
    }
    
}
