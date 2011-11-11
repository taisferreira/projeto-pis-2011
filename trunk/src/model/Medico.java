/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Medico extends Usuario {
    private String crm;

    public Medico(String crm, String userCpf, String userName, String userPassword, Integer userType) {
        super(userCpf, userName, userPassword, userType);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return "Medico{" + "crm=" + crm + '}';
    }
    
}
