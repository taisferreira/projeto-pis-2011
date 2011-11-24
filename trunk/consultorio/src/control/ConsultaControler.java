/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.ConsultaDAO;
import dao.ConvenioDao;
import dao.MedicoDao;
import dao.PacienteDao;
import java.sql.Date;
import java.util.ArrayList;
import model.Consulta;
import model.Medico;
import model.Pagamento;
import model.Prontuario;
import view.ExcluirConsulta;
import view.MarcarConsulta;

/**
 *
 * @author tais
 */
public class ConsultaControler {

   public static void marcar_consulta(){
       new MarcarConsulta().setVisible(true);
   }

   public static void desmarcar_consulta(){
       new ExcluirConsulta().setVisible(true);
   }

   public static void buscar_consulta(){
       System.out.println("Buscar consuta");
   }

   public static ArrayList selecionar_consulta_para_excluir(Object medico, String cpfPaciente){
       Medico m = (Medico) medico;
       return ConsultaDAO.getInstance().buscar_CPF_CRM(m.getCrm(), cpfPaciente);
       /*Consulta c = new Consulta();
       Medico m = (Medico) medico;
       c.setMedicoCRM(m.getCrm());
       c.setPacienteCPF(cpfPaciente);

       Date d1 = new Date(System.currentTimeMillis());
       int ano = Integer.parseInt(d1.toString().substring(0, 4));
       ano = ano+5;

       Date d2 = Date.valueOf(ano+d1.toString().substring(4, 10));
       d1 = Date.valueOf("0000-00-00");

       //Busca todas as consultas de hoje até daqui 5 anos
       return ConsultaDAO.getInstance().buscarIntervalo(d1.toString(), d2.toString(), c);*/
   }

    public static ArrayList<Object> getAllMedicos(){
        MedicoDao md = new MedicoDao();
        return md.findAllMedicos();
    }

    public static String getDay(){
       // não implementado
        Consulta c = new Consulta();
        c.setCodigoConsulta(3);
        c.setDia("2011-11-25 10:40:00");
        c.setMedicoCRM("123");
        c.setPacienteCPF("2589999");
        c.setPagamento(new Pagamento());
        c.setProtuario(new Prontuario());
        //ConsultaDAO.getInstance().salvar(c);
        return c.getDia().toString();
    }

    public static ArrayList<Object> getAllConvenios() {
        return new ConvenioDao().getAllConvenios();
    }

    public static boolean pacienteEhValido(String cpf){
        if(PacienteDao.buscar(cpf) == null){
            return false;
        }
        return true;
    }

    public static void exclui_consulta(Object o) {
        Consulta c = (Consulta) o;
        ConsultaDAO.getInstance().remover(c);
    }

}
