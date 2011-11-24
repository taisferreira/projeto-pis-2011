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

        Statement stm;
        String query = "INSERT INTO pagamento(codigoConsulta, idConv, valor, vencimento)" +
                " VALUES ("+c.getCodigoConsulta()+","+c.getIdConv()+", " +
                ""+c.getValor()+", \""+c.getVencimento()+"\" );";

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
       System.out.println("Pagamento.remover");
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

        System.out.println("pagamento.buscar");
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Pagamento();
                c.setIdPag(rs.getLong(1));
                c.setCodigoConsulta(rs.getLong(2));

                c.setIdConv(new Convenio("conv", "cnpj"));
                c.getIdConv().setDesconto(rs.getDouble(3));
                
                c.setValor(rs.getDouble(4));
                c.setVencimento(rs.getString(5));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }
}
