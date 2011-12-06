/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import dao.*;

import java.sql.Date;

/**
 *
 * @author tais
 */
public class Pagamento {
    private long idPag;
    private long codigoConsulta;
    private Convenio conv;
    private double valor;
    private Date vencimento;

    public long getCodigoConsulta() {
        return codigoConsulta;
    }

    public Convenio getIdConv() {
        return conv;
    }

    public long getIdPag() {
        return idPag;
    }

    public double getValor() {
        return valor;
    }

    public String getVencimento() {
        return vencimento.toString();
    }
    
    public String getVencimento2() {
        String dia, mes, ano;
        dia = vencimento.toString().substring(8, 10);
        mes = vencimento.toString().substring(5, 7);
        ano = vencimento.toString().substring(0, 4);
        return dia + "/" + mes + "/" + ano;
    }

    public void setCodigoConsulta(long codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public void setIdConv(Convenio conv) {
        this.conv = conv;
    }

    public void setIdPag(long idPag) {
        this.idPag = idPag;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /* Esperando string do tipo dd/mm/yyyy */
    public void setVencimento(String dd_mm_yyyy) {
        this.vencimento = java.sql.Date.valueOf(dd_mm_yyyy);
    }

    public int salvarPagamento(){
        return PagamentoDAO.getInstance().salvar(this);
    }

    public void removerPagamento(){
        PagamentoDAO.getInstance().remover(this);
    }

    public Pagamento buscarPagamento(long codigoConsulta){
        return PagamentoDAO.getInstance().buscar(codigoConsulta);
    }
}
