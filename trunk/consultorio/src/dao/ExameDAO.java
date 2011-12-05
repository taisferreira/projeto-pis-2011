/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exame;

/**
 *
 * @author tais
 */
public class ExameDAO {
    private static ExameDAO exameDAO = null;
    private Connection conn;

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private ExameDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ExameDAO getInstance(){
        if(exameDAO == null){
            exameDAO = new ExameDAO();
        }
        return exameDAO;
    }

    public ArrayList getAllExames(long codigoConsulta) {
        ArrayList<Exame> ae = new ArrayList<Exame>();
        Exame e = null;
        ResultSet rs;
        Statement stm;

        String query = "SELECT id, nome, valor, local, data" +
                " FROM exame, Exame_Consulta" +
                " WHERE id=id_Exame AND id_consulta="+codigoConsulta+";";

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            while(rs.next()){
                e = new Exame();
                e.setId(rs.getLong(1));
                e.setNome(rs.getString(2));
                e.setValor(rs.getDouble(3));
                e.setLocal(rs.getString(4));
                e.setData(rs.getTimestamp(5));
                ae.add(e);
            }

	} catch (Exception ex) {
            ex.printStackTrace();
	}

        return ae;
    }

    public void salvar(Exame e) {
        Statement stm;

        String query = "INSERT INTO exame (nome, valor, local) VALUES "+
                "('"+e.getNome()+"', "+e.getValor()+", '"+e.getLocal()+"');";

        try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception ex) {
            ex.printStackTrace();
	}
    }

    public void excluir(Exame e) {
        Statement stm;
        String query = "DELETE FROM exame WHERE id="+e.getId()+";";

        try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception ex) {
            ex.printStackTrace();
	}
    }

    public Exame buscar(long aLong) {
        String query = "SELECT nome, valor, local" +
                " FROM exame WHERE id="+aLong+";";
        Exame e = null;
        ResultSet rs;
        Statement stm;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
            if(rs.next()){
                e = new Exame();
                e.setId(aLong);
                e.setNome(rs.getString(1));
                e.setLocal(rs.getString(2));
                e.setValor(rs.getDouble(3));
            }
	} catch (Exception ex) {
            ex.printStackTrace();
	}

        return e;
    }
}
