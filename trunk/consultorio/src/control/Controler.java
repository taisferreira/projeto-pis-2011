/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Model;


/**
 *
 * @author Leonardo
 */
public class Controler {
    private Model model;
    public Controler(Model m){
        model = m;
    }
    
    public void salvarConvenio(String tfNome, String tfCnpj){
        model.salvarConvenio(tfNome, tfCnpj);
    }
    
    public void salvarDespesa(String taDescricao, Double tfValor, int pg, String tfCpf){
        String pago;
        if (pg == 0) {
            pago = "S";
        } else {
            pago = "N";
        }
        model.salvarDespesa(taDescricao, tfValor, pago, tfCpf);
    }
    
}
