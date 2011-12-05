
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Medicacao;

/**
 *
 * @author tais
 */
public class MedicacaoDAO {
    private static MedicacaoDAO medicamentoDAO = null;
    private Connection conn;

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private MedicacaoDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MedicacaoDAO getInstance(){
        if(medicamentoDAO == null){
            medicamentoDAO = new MedicacaoDAO();
        }
        return medicamentoDAO;
    }

    /*-------------- CONSULTAS NO BANCO -----------------*/
    public int salvar(Medicacao c){

        Statement stm;
        String query = "INSERT INTO medicacao(descricao, posologia, duracao)" +
                " VALUES ('"+c.getDescricao()+"', '"+c.getPosologia()+"', '"+c.getDuracao()+"');";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
            return 0;
	}

        return 1;
    }

    public void remover(Medicacao c){
       System.out.println("medicacao.remover");
       Statement stm;

       /*Alterar consulta no banco aqui*/
       String query = "DELETE FROM medicacao WHERE id="+c.getIdMedicacao()+";";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }

    public Medicacao buscarDescricao (String descricao){
        /*Alterar consulta no banco aqui*/
       String query = "SELECT * FROM medicacao WHERE descricao='"+descricao+"';";

       ResultSet rs;
       Statement stm;
       Medicacao c = null;


        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Medicacao();
                c.setIdMedicacao(rs.getLong(1));
                c.setDescricao(rs.getString(2));
                c.setPosologia(rs.getString(3));
                c.setDuracao(rs.getString(4));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }


    public Medicacao buscarId (Long id){
        /*Alterar consulta no banco aqui*/
       String query = "SELECT * FROM medicacao WHERE id="+id+";";

       ResultSet rs;
       Statement stm;
       Medicacao c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Medicacao();
                c.setIdMedicacao(rs.getLong(1));
                c.setDescricao(rs.getString(2));
                c.setPosologia(rs.getString(3));
                c.setDuracao(rs.getString(4));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }
}
