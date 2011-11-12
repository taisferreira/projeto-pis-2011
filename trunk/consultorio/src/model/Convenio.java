/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Convenio {
    private String nome;
    private String cnpj;

    public Convenio(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Convenio{" + "nome=" + nome + ", cnpj=" + cnpj + '}';
    }
    
}
