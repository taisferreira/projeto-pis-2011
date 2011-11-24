/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.*;

/**
 *
 * @author tais
 */
public class Prontuario {
    private long idPront;
    private String diagnostico;
    private String sintomas;
    private long codigoConsulta;

    public long getCodigoConsulta() {
        return codigoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public long getIdPront() {
        return idPront;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setCodigoConsulta(long codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setIdPront(long idPront) {
        this.idPront = idPront;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

}
