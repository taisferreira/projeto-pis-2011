/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Conexao;
import dao.ConvenioDao;
import dao.DAOException;
import dao.DespesasDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.CadastrarConvenio;
import view.CadastrarDespesa;

/**
 *
 * @author Leonardo
 */
public class Model {
    Conexao con;
    public Model(){
        con = new Conexao();
        
    }
    
    public void salvarConvenio(String tfNome, String tfCnpj){
        ConvenioDao dao = new ConvenioDao(con);

    Convenio conv = new Convenio(tfNome, tfCnpj);

    try {
        dao.cadConvenio(conv);
    } catch (DAOException ex) {
        Logger.getLogger(CadastrarConvenio.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CadastrarConvenio.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void salvarDespesa(String taDescricao, Double tfValor, String pago, String tfCpf){
        DespesasDao dao = new DespesasDao(con);
        Despesa desp = new Despesa(taDescricao, tfValor, pago, tfCpf);
        try {
            dao.cadDespesa(desp);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarDespesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarDespesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Conexao getCon() {
        return con;
    }

    public void setCon(Conexao con) {
        this.con = con;
    }
}
