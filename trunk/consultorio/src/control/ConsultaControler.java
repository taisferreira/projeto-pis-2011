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
import model.Exame;
import model.Medicacao;
import model.Medico;
import model.Pagamento;
import model.Prontuario;
import util.Observador;
import view.AdicionarExame;
import view.AdicionarMedicacao;
import view.BuscarConsulta;
import view.EscolherDia;
import view.EscolherHorario;
import view.ExcluirConsulta;
import view.ExibirAlterarProntuario;
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
    private static ExibirAlterarProntuario observarExAltMed = null;

   public static void marcar_consulta(){
       new MarcarConsulta().setVisible(true);
   }

   public static void desmarcar_consulta(){
       new ExcluirConsulta().setVisible(true);
   }

   public static void buscar_para_alterar(int opcao){
       new BuscarConsulta(opcao).setVisible(true);
   }

   public static void remarcar_consulta(Object o){
       Consulta c = (Consulta) o;
       new RemarcarConsulta(c).setVisible(true);
   }

   public static void exibir_alterar_prontuario(Object o){
       Consulta c = (Consulta) o;
       new ExibirAlterarProntuario(c).setVisible(true);
   }

   public static ArrayList buscar_lista_consultas(Object medico, String cpfPaciente){
       if(medico == null) {
           return ConsultaDAO.getInstance().buscar_CPF_CRM("", cpfPaciente);
       }
       Medico m = (Medico) medico;
       return ConsultaDAO.getInstance().buscar_CPF_CRM(m.getCrm(), cpfPaciente);
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

    public static void atualizaProntuario(Prontuario p,
            String diagnostico, String sintomas)
    {
        if(p.getIdPront() != 0){
           p.setDiagnostico(diagnostico);
           p.setSintomas(sintomas);
           p.atualizar();
        }
    }

    public static void adicionaMedicacao(Prontuario prontuario, Object medicacao) {
        Medicacao m =  (Medicacao) medicacao;
        prontuario.inserirMedicacao(m);
    }

    public static void removeMedicacao(Prontuario protuario, Object medicacao) {
        Medicacao m =  (Medicacao) medicacao;
        protuario.removerMedicacao(m);
    }

    public static void pegarMedicacao(ExibirAlterarProntuario eap)
    {
        ConsultaControler.observarExAltMed = eap;
        AdicionarMedicacao am = new AdicionarMedicacao(new Medicacao());
        am.addObservador(new ConsultaControler());
        am.setVisible(true);
    }

    public static ArrayList<Medicacao> buscarMedicacoes(Prontuario p){
        return p.buscaMedicacoes();
    }

    public static ArrayList getAllExames(Consulta consulta) {
        return consulta.getAllExames();
    }

    public static void adicionaExame(Consulta consulta, Object exa) {
        Exame e = (Exame) exa;
        consulta.adicionarExame(e);
    }

    public static void removeExame(Consulta consulta, Object exa) {
        Exame e = (Exame) exa;
        consulta.removerExame(e);
    }

    public static void pegarExame(ExibirAlterarProntuario eap) {
        ConsultaControler.observarExAltMed = eap;
        AdicionarExame ae = new AdicionarExame();
        ae.addObservador(new ConsultaControler());
        ae.setVisible(true);
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
        if(obj instanceof AdicionarMedicacao){
            AdicionarMedicacao am = (AdicionarMedicacao) obj;
            if(ConsultaControler.observarExAltMed != null){
                ConsultaControler.observarExAltMed.adicionaListaMedicacao(am.getMedicacao());
            }
        }
        if(obj instanceof AdicionarExame){
            AdicionarExame ae = (AdicionarExame) obj;
            if(ConsultaControler.observarExAltMed != null){
                ConsultaControler.observarExAltMed.adicionaListaExames(ae.getExame());
                ae.dispose();
            }
        }
    }
}
