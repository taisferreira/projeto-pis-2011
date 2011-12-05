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
   public void atualizar(Consulta consulta){
        Statement stm;

       String atualizar_consulta = "update consulta set idPagamento="
               +consulta.getPagamento().getIdPag()+"," +
               " dia='"+consulta.getDia().substring(0, 19)+"'," +
               "idProntuario="+consulta.getProtuario().getIdPront()+
               " where codigo="+consulta.getCodigoConsulta()+";";
        try {
            stm = conn.createStatement();
            stm.executeUpdate(atualizar_consulta);

	} catch (SQLException e) {
            e.printStackTrace();
	}
    }

    public int salvar(Consulta c){

       Statement stm;

       String insere_consulta = "insert into consulta (idProntuario, cpf_Paciente," +
            " crm_Medico, dia, idPagamento) values ("+c.getProtuario().getIdPront()+"" +
            ", '"+c.getPacienteCPF()+"','"+c.getMedicoCRM()+"','"+c.getDia().substring(0, 19)+"'," +
            ""+c.getPagamento().getIdPag()+");";
        
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
       String query1 = "DELETE FROM consulta WHERE codigo="+c.getCodigoConsulta()+";";
       String query2 = "DELETE FROM prontuario WHERE codigoConsulta="+c.getCodigoConsulta()+";";
       String query3 = "DELETE FROM pagamento WHERE codigoConsulta="+c.getCodigoConsulta()+";";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query1);
            stm.executeUpdate(query2);
            stm.executeUpdate(query3);
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

                c.setProtuario(ProntuarioDAO.getInstance().buscar(c.getCodigoConsulta()));

                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));

                c.setPagamento(PagamentoDAO.getInstance().buscar(c.getCodigoConsulta()));
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

       if(crm_medico == null || crm_medico.isEmpty()){/*Sem medico*/
           if (cpf_paciente == null || cpf_paciente.isEmpty()){/*Esm paciente*/
               query = "SELECT * FROM consulta;";
           }
           else{/*So paciente*/
               query = "SELECT * FROM consulta WHERE " +
                       "cpf_Paciente="+cpf_paciente+";";
           }
       }
       else {/*Com médico*/

            if(cpf_paciente == null || cpf_paciente.isEmpty()){/*e sem paciente*/
                 query = "SELECT * FROM consulta WHERE crm_Medico="+crm_medico+";";
            }
            else{/*e com paciente*/
                query = "SELECT * " +
                       "FROM consulta " +
                       "WHERE cpf_Paciente="+cpf_paciente+" " +
                       "AND crm_Medico="+crm_medico+";";
            }
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

                c.setProtuario(ProntuarioDAO.getInstance().buscar(c.getCodigoConsulta()));

                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));
                c.setPagamento(PagamentoDAO.getInstance().buscar(c.getCodigoConsulta()));
                
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


                c.setProtuario(ProntuarioDAO.getInstance().buscar(c.getCodigoConsulta()));

                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));

                c.setPagamento(PagamentoDAO.getInstance().buscar(c.getCodigoConsulta()));
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}

        return c;
    }

    /*Corrigir: Pouco eficiente, evite usar, médico pode ter centenas de consultas cadastradas*/
    public ArrayList<Consulta> buscarIntervalo(String data_inicio, String data_fim, Consulta consulta){

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

                c.setProtuario(ProntuarioDAO.getInstance().buscar(c.getCodigoConsulta()));

                c.setPacienteCPF(rs.getString(3));
                c.setMedicoCRM(rs.getString(4));
                c.setDia(rs.getString(5));

                c.setPagamento(PagamentoDAO.getInstance().buscar(c.getCodigoConsulta()));

                a.add(c);
            }

	} catch (SQLException e) {
            e.printStackTrace();
	}

        return a;
    }
    public void removerExame(Consulta c, Exame e){
       Statement stm;

       ExameDAO.getInstance().excluir(e);

       /*Alterar consulta no banco aqui*/
       String query = "DELETE FROM Exame_Consulta " +
               "WHERE id_Exame="+e.getId()+
               " AND id_consulta="+c.getCodigoConsulta()+";";


	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception ex) {
            ex.printStackTrace();
	}
    }

     public void inserirExame(Consulta c, Exame exame){
       Statement stm;
       ResultSet rs;

       ExameDAO.getInstance().salvar(exame);
       Exame e = null;

       String query1 = "SELECT MAX(id) FROM exame;";
       
	try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query1);
            if(rs.next()){
                e = ExameDAO.getInstance().buscar(rs.getLong(1));
                e.setData(exame.getData());
                System.out.println(e.toString());
                
                String query = "INSERT INTO Exame_Consulta VALUES ("+
                        c.getCodigoConsulta()+", "+e.getId()+", '"+e.getData()+"');";
                stm.executeUpdate(query);

                exame = e;
            }
	} catch (Exception ex) {
            ex.printStackTrace();
	}
    }

    public ArrayList getAllExames(Consulta c) {
        return ExameDAO.getInstance().getAllExames(c.getCodigoConsulta());
    }
}
