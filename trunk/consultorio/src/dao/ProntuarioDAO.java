/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Medicacao;
import model.Prontuario;

/**
 *
 * @author tais
 */
public class ProntuarioDAO {
    private static ProntuarioDAO prontuarioDAO = null;
    private Connection conn;

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private ProntuarioDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProntuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ProntuarioDAO getInstance(){
        if(prontuarioDAO == null){
            prontuarioDAO = new ProntuarioDAO();
        }
        return prontuarioDAO;
    }

    /*-------------- CONSULTAS NO BANCO -----------------*/
    public int salvar(Prontuario p){
        Statement stm;

        String query1 = "INSERT INTO prontuario (diagnostico, sintomas, codigoConsulta)" +
                " VALUES ('"+p.getDiagnostico()+"', '"+p.getSintomas()+"'" +
                ", "+p.getCodigoConsulta()+");";


	try {
            stm = conn.createStatement();
            stm.executeUpdate(query1);
	} catch (Exception e) {
            e.printStackTrace();
            return 0;
	}

        return 1;
    }

    public Prontuario buscar (long id_consulta){
        /*Alterar consulta no banco aqui*/
       String query = "SELECT * FROM prontuario WHERE codigoConsulta="+id_consulta+";";

       ResultSet rs;
       Statement stm;
       Prontuario c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Prontuario();
                c.setIdPront(rs.getLong(1));
                c.setDiagnostico(rs.getString(2));
                c.setSintomas(rs.getString(3));
                c.setCodigoConsulta(rs.getInt(4));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }

     public void remover(Prontuario c){
       System.out.println("Prontuario.remover");
       Statement stm;
       ResultSet rs;
       Medicacao m;

       /*Alterar consulta no banco aqui*/
       String query1 = "SELECT id_medicacao FROM medicacao, medicacao_prontuario" +
               " WHERE medicacao_prontuario.id_prontuario="+c.getIdPront()+";";

       String query2 = "DELETE FROM medicacao_prontuario WHERE id_prontuario="+c.getIdPront()+";";

       String query3 = "DELETE FROM prontuario WHERE idPront="+c.getIdPront()+";";



	try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query1);
            if(rs.next()){
                m = new Medicacao();
                m.setIdMedicacao(rs.getLong(1));
                MedicacaoDAO.getInstance().remover(m);
                
                stm.executeUpdate(query2);
            }
            stm.executeUpdate(query3);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }
}
