package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import java.util.ArrayList;

/**
 *
 * @author tais
 */
public class ConsultaDAO {
    private static ConsultaDAO consultaDAO = null;
    private Connection conn;

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private ConsultaDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConsultaDAO getInstance(){
        if(consultaDAO == null){
            consultaDAO = new ConsultaDAO();
        }
        return consultaDAO;
    }


   public Connection getConn(){
       return conn;
   }
/*-------------- CONSULTAS NO BANCO -----------------*/
    public int salvar(Consulta c){

        Statement stm;

       String insere_consulta = "insert into consulta (idProntuario, cpf_Paciente," +
            " crm_Medico, dia, idPagamento) values ("+c.getProtuario().getIdPront()+"" +
            ", '"+c.getPacienteCPF()+"','"+c.getMedicoCRM()+"','"+c.getDia().substring(0, 19)+"'," +
            ""+c.getPagamento().getIdPag()+");";
        System.out.println(insere_consulta);
        
	try {
            stm = conn.createStatement();
            stm.executeUpdate(insere_consulta);
            
	} catch (SQLException e) {
            e.printStackTrace();
	}

        return 1;
    }

    public void remover(Consulta c){
       Statement stm;
       
       /*Alterar consulta no banco aqui*/
       String query = "DELETE FROM consulta WHERE codigo="+c.getCodigoConsulta()+";";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }

    public Consulta buscar(long i){
       /*Alterar consulta no banco aqui*/
       String query = "SELECT * FROM consulta WHERE codigo="+i+";";

       ResultSet rs;
       Statement stm;
       Consulta c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Consulta();
		c.setCodigoConsulta(rs.getLong(1));
                c.setProtuario(new Prontuario());
                c.getProtuario().setCodigoConsulta(c.getCodigoConsulta());
                c.getProtuario().setIdPront(rs.getInt(2));
                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));
                c.setPagamento(new Pagamento());
                c.getPagamento().setCodigoConsulta(c.getCodigoConsulta());
                c.getPagamento().setIdPag(rs.getInt(6));
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}

        return c;
    }

   /*Lista as consultas de um dado paciente com um dado médico*/
    public ArrayList<Consulta> buscar_CPF_CRM(String crm_medico,
            String cpf_paciente){

        String query = "";

       if(crm_medico == null || crm_medico.isEmpty()){
           if (cpf_paciente == null || cpf_paciente.isEmpty()){
               query = "SELECT * " +
                       "FROM consulta " +
                       "WHERE cpf_Paciente="+cpf_paciente+" " +
                       "AND crm_Medico="+crm_medico+";";
           }
           else{
               query = "SELECT * FROM consulta WHERE " +
                       "cpf_Paciente="+cpf_paciente+";";
           }
       }
       else {
           query = "SELECT * FROM consulta WHERE crm_Medico="+crm_medico+";";
       }

       ResultSet rs;
       Statement stm;
       Consulta c = null;
       ArrayList<Consulta> a = new ArrayList<Consulta>();

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            while(rs.next()){
		c = new Consulta();
		c.setCodigoConsulta(rs.getLong(1));
                c.setProtuario(new Prontuario());
                c.getProtuario().setCodigoConsulta(c.getCodigoConsulta());
                c.getProtuario().setIdPront(rs.getInt(2));
                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));
                c.setPagamento(new Pagamento());
                c.getPagamento().setCodigoConsulta(c.getCodigoConsulta());
                c.getPagamento().setIdPag(rs.getInt(6));
                
                a.add(c);
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}
       
        return a;
    }

     /*Busca a consulta associada a um dado prontuario*/
    public Consulta buscarIdProntuario(long id_prontuario){

        /*Alterar consulta no banco aqui*/
       String query = "SELECT * FROM consulta " +
               "WHERE idProntuario="+id_prontuario+";";

       ResultSet rs;
       Statement stm;
       Consulta c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Consulta();
		c.setCodigoConsulta(rs.getLong(1));
                c.setProtuario(new Prontuario());
                c.getProtuario().setCodigoConsulta(c.getCodigoConsulta());
                c.getProtuario().setIdPront(rs.getInt(2));
                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));
                c.setPagamento(new Pagamento());
                c.getPagamento().setCodigoConsulta(c.getCodigoConsulta());
                c.getPagamento().setIdPag(rs.getInt(6));
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}

        System.out.println("Consulta.buscarIdProntuario");
        return c;
    }

    /*Corrigir: Pouco eficiente, evite usar, médico pode ter centenas de consultas cadastradas*/
    public ArrayList<Consulta> buscarIntervalo(String data_inicio, String data_fim, Consulta consulta){
        System.out.println(data_inicio+" "+data_fim);

        String query = "SELECT * FROM consulta " +
                "WHERE cpf_Paciente="+consulta.getPacienteCPF()+" AND " +
                "crm_Medico="+consulta.getMedicoCRM()+" AND " +
                "dia BETWEEN '"+data_inicio+"' AND '"+data_fim+"';";
        String query2 = "SELECT * FROM consulta " +
                "WHERE cpf_Paciente="+consulta.getPacienteCPF()+" AND " +
                "dia BETWEEN '"+data_inicio+"' AND '"+data_fim+"';";
        String query3 = "SELECT * FROM consulta " +
                "WHERE crm_Medico="+consulta.getMedicoCRM()+" AND " +
                "dia BETWEEN '"+data_inicio+"' AND '"+data_fim+"';";
        String query4 = "SELECT * FROM consulta " +
                "WHERE dia BETWEEN '"+data_inicio+"' AND '"+data_fim+"';";


       ResultSet rs;
       Statement stm;
       Consulta c = null;
       ArrayList<Consulta> a = new ArrayList<Consulta>();

        try {
            stm = conn.createStatement();
            if(consulta.getMedicoCRM() == null || consulta.getMedicoCRM().isEmpty()){
                if(consulta.getPacienteCPF() == null || consulta.getPacienteCPF().isEmpty()){
                    rs = stm.executeQuery(query4);
                }
                else rs = stm.executeQuery(query2);
            }
            else{
                if(consulta.getPacienteCPF() == null || consulta.getPacienteCPF().isEmpty()){
                    rs = stm.executeQuery(query3);
                }
                else rs = stm.executeQuery(query4);
            }

            while(rs.next()){
		c = new Consulta();
		c.setCodigoConsulta(rs.getLong(1));
                c.setProtuario(new Prontuario());
                c.getProtuario().setCodigoConsulta(c.getCodigoConsulta());
                c.getProtuario().setIdPront(rs.getInt(2));
                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));
                c.setPagamento(new Pagamento());
                c.getPagamento().setCodigoConsulta(c.getCodigoConsulta());
                c.getPagamento().setIdPag(rs.getInt(6));

                a.add(c);
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}

        return a;
    }

    /*Idem buscarIntervalo*/
    public boolean horarioEstaDisponivel(Consulta c) {
        int i;
        Date d = new Date(System.currentTimeMillis());
        Date d2 = new Date(d.getTime()+(2*365*24*60*60*1000000));

        ArrayList<Consulta> a =
                ConsultaDAO.getInstance().buscarIntervalo(d.toString(), d2.toString(), c);

        for(i = 0; i <a.size(); i++){
            /*Se bater com horário já cadastrado não está disponível*/
/*            if(a.get(i).getHorario().equals(c.getHorario()) &&
                    a.get(i).getDia().equals(c.getDia()))
            {
                return false;
            }*/
        }

        return true;
    }
}
