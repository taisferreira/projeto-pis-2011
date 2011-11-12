/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Despesa {
    private String descricao;
    private Double valor;
    private String pago;
    private String cpfUsuario;

    public Despesa(String descricao, Double valor, String pago, String cpfUsuario) {
        this.descricao = descricao;
        this.valor = valor;
        this.pago = pago;
        this.cpfUsuario = cpfUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Despesa{" + "descricao=" + descricao + ", valor=" + valor + ", pago=" + pago + ", cpfUsuario=" + cpfUsuario + '}';
    }    
}
