/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.ConsultaDAO;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author tais
 */
public class Consulta {
    /*Atributos da entidade Consulta*/
    private long codigoConsulta;
    private Prontuario Prontuario;
    private String pacienteCPF;
    private String medicoCRM;
    private Timestamp dia;
    private Pagamento pagamento;

    public Consulta(){}
    
    public boolean horarioEhValido(){
        return ConsultaDAO.getInstance().horarioEstaDisponivel(this);
    }

    public int marcarConsulta(){
        return ConsultaDAO.getInstance().salvar(this);
    }

    public void removerConsulta(){
        ConsultaDAO.getInstance().remover(this);
    }

    public ArrayList<Consulta> buscarConsulta(String crm_medico, String cpf_paciente){

        return ConsultaDAO.getInstance().buscar_CPF_CRM(crm_medico, cpf_paciente);
    }

    public Consulta buscarConsultaProtuario(long idProntuario){

        return ConsultaDAO.getInstance().buscarIdProntuario(idProntuario);
    }

    public ArrayList<Consulta> buscarIntervalo(String data_inicio, String data_final){

        return ConsultaDAO.getInstance().buscarIntervalo(data_inicio, data_final, this);
    }

    public long getCodigoConsulta() {
        return codigoConsulta;
    }

    public String getDia() {
        return dia.toString();
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Prontuario getProtuario() {
        return Prontuario;
    }

    public String getMedicoCRM() {
        return medicoCRM;
    }

    public String getPacienteCPF() {
        return pacienteCPF;
    }

    public void setCodigoConsulta(long codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    /* Esperando string do tipo 'yyyy-mm-dd hh:mm:ss' */
    public void setDia(String timestamp) {
        this.dia = java.sql.Timestamp.valueOf(timestamp);
    }


    public void setPagamento(Pagamento idPagamento) {
        this.pagamento = idPagamento;
    }

    public void setProtuario(Prontuario idProtuario) {
        this.Prontuario = idProtuario;
    }

    public void setMedicoCRM(String medicoCRM) {
        this.medicoCRM = medicoCRM;
    }

    public void setPacienteCPF(String pacienteCPF) {
        this.pacienteCPF = pacienteCPF;
    }

    @Override
    public String toString(){
        return "Paciente: "+pacienteCPF+"\nMedico: "+medicoCRM+"\nAgendamento: " +
                ""+dia.toString().substring(8, 10)+"/" +
                ""+dia.toString().substring(5, 7)+"/"+dia.toString().substring(0, 4)
                +" "+dia.toString().substring(11, 19);
    }

}
