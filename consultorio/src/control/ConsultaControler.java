/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.ConsultaDAO;
import dao.ConvenioDao;
import dao.MedicoDao;
import dao.PacienteDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JButton;
import model.Consulta;
import model.Convenio;
import model.Medico;
import model.Pagamento;
import model.Prontuario;
import util.Observador;
import view.BuscarConsulta;
import view.EscolherDia;
import view.EscolherHorario;
import view.ExcluirConsulta;
import view.MarcarConsulta;
import view.RemarcarConsulta;

/**
 *
 * @author tais
 */
public class ConsultaControler implements Observador{
    private static JButton observarDia = null;
    private static JButton observarHorario = null;
    private static JButton observarVencimento = null;
    private static String dataEscolhida = null;
    private static String horarioEscolhido = null;
    private static EscolherDia escolherdia = null;
    private static EscolherHorario escolherhorario = null;
    private static String crmMedicoSelecionado = null;
    private static Convenio convenio = null;
    private static EscolherDia escolherVencimento = null;
    private static String vencimentoEscolhido = null;

   public static void marcar_consulta(){
       new MarcarConsulta().setVisible(true);
   }

   public static void desmarcar_consulta(){
       new ExcluirConsulta().setVisible(true);
   }

   public static void buscar_para_remarcar(){
       new BuscarConsulta().setVisible(true);
   }

   public static void remarcar_consulta(Object o){
       Consulta c = (Consulta) o;
       new RemarcarConsulta(c).setVisible(true);
   }

   public static void exibir_alterar_consulta(){
       System.out.println("Exibir ou Alterar dados da consuta");
   }

   public static ArrayList selecionar_consulta_para_excluir(Object medico, String cpfPaciente){
       if(medico == null) {
           return ConsultaDAO.getInstance().buscar_CPF_CRM("", cpfPaciente);
       }
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

    public static void selectDay(String init, JButton jb){
        /*if(ConsultaControler.escolherdia != null){
            ConsultaControler.escolherdia.dispose();
        }*/
        ConsultaControler.escolherdia  = new EscolherDia(init);
        ConsultaControler.escolherdia .setVisible(true);
        ConsultaControler.observarDia = jb;
        ConsultaControler.escolherdia.addObservador(new ConsultaControler());
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

    public static String getDataEscolhida(){
        return ConsultaControler.dataEscolhida;
    }

    public static String getHorarioEscolhido(){
        return ConsultaControler.horarioEscolhido;
    }

    public static void selectHour(JButton horario, Object medico) {
        ArrayList<String> proibidos = new ArrayList<String>();
        String nome = "";
        String crm = "";
        int i;

        ArrayList<Consulta> ac = null;
        /*Busca horarios no banco*/
        if(medico instanceof Medico){
            Medico m = (Medico) medico;
            nome = m.getUserName();
            crm = m.getCrm();
            ConsultaControler.crmMedicoSelecionado = m.getCrm();
        }

        ac = new Consulta().buscarConsulta(crm, null);

        for(i = 0; i < ac.size(); i++){
            if(ac.get(i).getDia().substring(0, 10)
                    .equals(ConsultaControler.dataEscolhida.substring(0, 10))){
                proibidos.add(Timestamp.valueOf(ac.get(i).getDia()).toString().substring(11, 20));
            }
        }

        /*if(ConsultaControler.escolherhorario != null){
            ConsultaControler.escolherhorario.dispose();
        }*/
        ConsultaControler.escolherhorario = new EscolherHorario(ConsultaControler.dataEscolhida, proibidos, nome, crm);
        ConsultaControler.escolherhorario.setVisible(true);
        ConsultaControler.observarHorario = horario;
        ConsultaControler.escolherhorario.addObservador(new ConsultaControler());
    }

    public static void salvarConsulta(MarcarConsulta mc){
        if(ConsultaControler.escolherhorario != null){
            ConsultaControler.escolherhorario.dispose();
        }
        if(ConsultaControler.escolherdia != null){
            ConsultaControler.escolherdia.dispose();
        }
        if(ConsultaControler.escolherVencimento != null){
            ConsultaControler.escolherVencimento.dispose();
        }
        Prontuario pront = new Prontuario();
        pront.setDiagnostico(" ");
        pront.setSintomas(" ");

        Pagamento pag = new Pagamento();
        pag.setValor(mc.getValorConsulta());
        System.out.println(pag.getValor()+"");
        if(mc.getVencimento().isEmpty()){
            pag.setVencimento(" ");
        }
        else {
            pag.setVencimento(mc.getVencimento());
        }
        Convenio conv = (Convenio) mc.getConvenio();
        pag.setIdConv(conv);

        Consulta c = new Consulta();

        c.setDia(Timestamp.valueOf(""+ConsultaControler.dataEscolhida+" " +
                ""+ConsultaControler.horarioEscolhido+"0").toString());
        
        c.setMedicoCRM(ConsultaControler.crmMedicoSelecionado);
        c.setPacienteCPF(mc.getCpfPaciente());
        c.setPagamento(pag);
        c.setProtuario(pront);        

        c.marcarConsulta();
    }

    public static void setConvenioSelecionado(Object obj) {
        convenio = (Convenio) obj;
    }

    public static double getDescontoConvenio() {
        return convenio.getDesconto();
    }

    public static void selectDiaVencimento(String init, JButton jb) {
        /*if(ConsultaControler.escolherVencimento != null){
            ConsultaControler.escolherVencimento.dispose();
        }*/
        ConsultaControler.escolherVencimento  = new EscolherDia(init);

        ConsultaControler.escolherVencimento.setVisible(true);
        ConsultaControler.observarVencimento = jb;
        ConsultaControler.escolherVencimento.addObservador(new ConsultaControler());
    }

    public static void atualizarConsulta(Consulta c) {
        System.out.println("Consulta Controler: Atualizar Consulta");
        if(ConsultaControler.escolherhorario != null){
            ConsultaControler.escolherhorario.dispose();
        }
        if(ConsultaControler.escolherdia != null){
            ConsultaControler.escolherdia.dispose();
        }
        if(horarioEscolhido == null){
            horarioEscolhido = c.getDia().substring(11, 20);
        }
        if(dataEscolhida==null){
            dataEscolhida = c.getDia().substring(0, 10);
        }
        c.setDia(Timestamp.valueOf(""+ConsultaControler.dataEscolhida+" " +
                ""+ConsultaControler.horarioEscolhido+"0").toString());
        c.atualizar();
    }

    @Override
    public void atualizar(Object obj){
        if(obj instanceof EscolherDia){
            EscolherDia ed = (EscolherDia) obj;
            if(ed.equals(escolherdia)){
                if(ConsultaControler.observarDia != null){
                    ConsultaControler.dataEscolhida = ed.getDiaSelecionado();
                    ConsultaControler.observarDia.setText(ConsultaControler.dataEscolhida.substring(8, 10)+"/" +
                        ""+ConsultaControler.dataEscolhida.substring(5, 7)+"/"+
                        ConsultaControler.dataEscolhida.substring(0, 4));
                }
            }
            if(ed.equals(escolherVencimento)){
                if(ConsultaControler.observarVencimento != null){
                    ConsultaControler.vencimentoEscolhido = ed.getDiaSelecionado();
                    ConsultaControler.observarVencimento.setText(
                        ConsultaControler.vencimentoEscolhido.substring(8, 10)+"/" +
                        ""+ConsultaControler.vencimentoEscolhido.substring(5, 7)+"/"+
                        ConsultaControler.vencimentoEscolhido.substring(0, 4)
                    );
                }
            }

        }
        if(obj instanceof EscolherHorario){
            EscolherHorario eh = (EscolherHorario) obj;
            if(ConsultaControler.observarHorario != null){
                ConsultaControler.horarioEscolhido = eh.getHorarioSelecionado();
                ConsultaControler.observarHorario.setText(ConsultaControler.horarioEscolhido.substring(0, 2)+":00");
            }
        }
    }
}
