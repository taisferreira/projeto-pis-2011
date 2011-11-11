/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Paciente {
    private String cpfPaciente;
    private String nomePaciente;
    private String endPaciente;
    private String telPaciente;

    public Paciente(String cpfPaciente, String nomePaciente, String endPaciente, String telPaciente) {
        this.cpfPaciente = cpfPaciente;
        this.nomePaciente = nomePaciente;
        this.endPaciente = endPaciente;
        this.telPaciente = telPaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getEndPaciente() {
        return endPaciente;
    }

    public void setEndPaciente(String endPaciente) {
        this.endPaciente = endPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelPaciente() {
        return telPaciente;
    }

    public void setTelPaciente(String telPaciente) {
        this.telPaciente = telPaciente;
    }

    @Override
    public String toString() {
        return "Paciente{" + "cpfPaciente=" + cpfPaciente + ", nomePaciente=" + nomePaciente + ", endPaciente=" + endPaciente + ", telPaciente=" + telPaciente + '}';
    }
    
}
