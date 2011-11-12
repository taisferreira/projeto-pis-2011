/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Convenio;

/**
 *
 * @author Fabricio
 */
public class ConvenioDao {
    
    private static final String SQL_CREATE_CONVENIO =
            "insert into convenio (NOME, CNPJ) values ((?),(?))";
    
    private Conexao conexao;
    
    public ConvenioDao(Conexao con) {
        this.conexao = con;
    }
    
    public void cadConvenio(Convenio conv) throws DAOException, ClassNotFoundException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_CONVENIO);
            ps.setString(1, conv.getNome());
            ps.setString(2, conv.getCnpj());

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("ConvenioDAO: insert failed");
            }
            c.commit();
        } catch (SQLException sqlx) {
            try {
                c.rollback();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
            throw new DAOException(sqlx.getMessage());
        } finally {
            try {
                c.setAutoCommit(transactionState);
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }
    
}
