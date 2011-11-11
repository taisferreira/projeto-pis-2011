
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Funcionario;
import model.Usuario;

/**
 *
 * @author Fabricio
 */
public class FuncionarioDao {
    
    private static final String SQL_FIND_EMPLOYEE = 
                "select f.cpf_Usuario as CPF, f.salario as SALARIO, "+
		"f.id_Funcao as ID_FN, fn.nome as NOME_FN, "+
		"u.senha as SENHA, u.nome as NOME, u.tipo as TIPO "+
                "from funcionario f, usuario u, funcao fn "+
                "where u.cpf = '' and f.cpf_Usuario = u.cpf and "+
                "fn.id = f.id_Funcao";
    
    private static final String SQL_CREATE_EMPLOYEE =
            "insert into funcionario (CPF_USUARIO, SALARIO, ID_FUNCAO) values ((?),(?),(?))";
    
    private Conexao conexao;
    
    public FuncionarioDao(Conexao con) {
        this.conexao = con;
    }
    
    public Funcionario findFuncionario(Funcionario user){
        Funcionario func = new Funcionario(null, null, null, null, null, null, null);
        
        Connection c;
        ResultSet rs;
        PreparedStatement pst;
        try{
            c = conexao.getCon();
            rs = null;
            pst = c.prepareStatement(SQL_FIND_EMPLOYEE);
            pst.setString(1, user.getUserCpf());
            rs = pst.executeQuery();
            while(rs.next()){
                func = new Funcionario(rs.getInt("ID_FN"), rs.getString("NOME_FN"),
                        rs.getDouble("SALARIO"), rs.getString("CPF"),
                        rs.getString("NOME"), rs.getString("SENHA"),
                        rs.getInt("TIPO"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return func;
    }
    
    public void createEmployee(Funcionario func) throws DAOException, ClassNotFoundException {
        
        UsuarioDao userDao = new UsuarioDao(conexao);
        Usuario user = new Usuario(func.getUserCpf(),
                    func.getUserName(), func.getUserPassword(),
                    func.getUserType());
        userDao.create(user);
        
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_EMPLOYEE);
            ps.setString(1, func.getUserCpf());
            ps.setDouble(2, func.getSalario());
            ps.setInt(3, func.getIdFn());
            
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("FuncionarioDAO: insert failed");
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
