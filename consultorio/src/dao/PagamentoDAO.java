/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Convenio;
import model.Pagamento;

/**
 *
 * @author tais
 */
public class PagamentoDAO {
private static PagamentoDAO pagamentoDAO = null;
    private Connection conn;

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private PagamentoDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PagamentoDAO getInstance(){
        if(pagamentoDAO == null){
            pagamentoDAO = new PagamentoDAO();
        }
        return pagamentoDAO;
    }

    /*-------------- CONSULTAS NO BANCO -----------------*/
    public int salvar(Pagamento c){
        System.out.println("Pagamento.salvar");
        String query;

        Statement stm;
        Convenio convenio = c.getIdConv();
        if(convenio == null){
            query = "INSERT INTO pagamento (codigoConsulta, idConv, valor, vencimento)" +
                " VALUES ("+c.getCodigoConsulta()+",0 , " +
                ""+c.getValor()+", '"+c.getVencimento()+"');";
        }
        else{
            query = "INSERT INTO pagamento (codigoConsulta, idConv, valor, vencimento)" +
                " VALUES ("+c.getCodigoConsulta()+", '"+convenio.getCnpj()+"', " +
                ""+c.getValor()+", '"+c.getVencimento()+"');";
        }
        

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
            return 0;
	}

        return 1;
    }

    public void remover(Pagamento c){
       Statement stm;

       /*Alterar consulta no banco aqui*/
       String query = "DELETE FROM pagamento WHERE idPag="+c.getIdPag()+";";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }

    public Pagamento buscar (long idconsulta){
        
       String query = "SELECT * FROM pagamento WHERE codigoConsulta="+idconsulta+";";

       ResultSet rs;
       Statement stm;
       Pagamento c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Pagamento();
                c.setIdPag(rs.getLong(1));
                c.setCodigoConsulta(rs.getLong(2));

                c.setIdConv(new ConvenioDao().getConvenio(rs.getDouble(3)+""));
                
                c.setValor(rs.getDouble(4));
                c.setVencimento(rs.getString(5));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }
}
