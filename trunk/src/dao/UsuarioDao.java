
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

/**
 *
 * @author Fabricio
 */
public class UsuarioDao {
    
    private static final String SQL_FIND_USER = 
		"select * from usuario where cpf=(?)";
    private static final String SQL_CREATE_USER = 
		"insert into usuario (CPF, SENHA, NOME, TIPO)"
              + " values((?),(?),(?),(?))";

    private Conexao conexao;
    
    public UsuarioDao(Conexao con) {
        this.conexao = con;
    }
    
    public Usuario getUser(Usuario user){
        Usuario usuario = new Usuario(null, null, null, Integer.SIZE);
        Connection c;
        ResultSet rs;
        PreparedStatement pst;
        try{
            c = conexao.getCon();
            rs = null;
            pst = c.prepareStatement(SQL_FIND_USER);
            pst.setString(1, user.getUserCpf());
            rs = pst.executeQuery();
            while(rs.next()){
                usuario = new Usuario(rs.getString("cpf"),
                        rs.getString("nome"), rs.getString("senha"),
                        rs.getInt("tipo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    
    public void create(Usuario user) throws DAOException, ClassNotFoundException {
	
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_USER);
            ps.setString(1, user.getUserCpf());
            ps.setString(2, user.getUserPassword());
            ps.setString(3, user.getUserName());
            ps.setInt(4, user.getUserType());
            
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("UserarioDAO: insert failed");
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
