/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.ConsultaDAO;
import dao.PagamentoDAO;
import dao.ProntuarioDAO;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author tais
 */
public class Consulta {
    /*Atributos da entidade Consulta*/
    private long codigoConsulta;
    private Prontuario prontuario;
    private String pacienteCPF;
    private String medicoCRM;
    private Timestamp dia;
    private Pagamento pagamento;

    public Consulta(){}
    
    public boolean horarioEhValido(){
        return ConsultaDAO.getInstance().horarioEstaDisponivel(this);
    }

    public void marcarConsulta(){
        ConsultaDAO.getInstance().salvar(this);
        ArrayList<Consulta> ac = ConsultaDAO.getInstance().buscar_CPF_CRM(medicoCRM, pacienteCPF);

                /*.buscarIntervalo(dia.toString().substring(0, 10)
                , dia.toString().substring(0, 10), this);*/

        if(ac.size()==0){
            System.out.println("Falha ao salvar Consulta");
        }
        else{
            Consulta c = ac.get(ac.size()-1);
            codigoConsulta = c.getCodigoConsulta();
            pagamento.setCodigoConsulta(codigoConsulta);
            prontuario.setCodigoConsulta(codigoConsulta);

            ProntuarioDAO.getInstance().salvar(prontuario);
            prontuario = ProntuarioDAO.getInstance().buscar(codigoConsulta);
            if(prontuario == null){
                System.out.println("Falha ao salvar prontuario");
            }
            
            PagamentoDAO.getInstance().salvar(pagamento);
            pagamento = PagamentoDAO.getInstance().buscar(codigoConsulta);
            if(pagamento == null){
                System.out.println("Falha ao salvar pagamento");
            }

            this.atualizar();
        }

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
        return prontuario;
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
        System.out.println(timestamp);
        this.dia = java.sql.Timestamp.valueOf(timestamp);
    }


    public void setPagamento(Pagamento idPagamento) {
        this.pagamento = idPagamento;
    }

    public void setProtuario(Prontuario idProtuario) {
        this.prontuario = idProtuario;
    }

    public void setMedicoCRM(String medicoCRM) {
        this.medicoCRM = medicoCRM;
    }

    public void setPacienteCPF(String pacienteCPF) {
        this.pacienteCPF = pacienteCPF;
    }

    @Override
    public String toString(){
        return "Paciente: "+pacienteCPF+" \nMedico: "+medicoCRM+" \nAgendamento: " +
                ""+dia.toString().substring(8, 10)+"/" +
                ""+dia.toString().substring(5, 7)+"/"+dia.toString().substring(0, 4)
                +" "+dia.toString().substring(11, 19);
    }

     public void atualizar(){
        ConsultaDAO.getInstance().atualizar(this);
    }
}
