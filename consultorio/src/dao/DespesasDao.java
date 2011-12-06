/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Despesa;

/**
 *
 * @author Fabricio
 */
public class DespesasDao {
    
    private static String SQL_CREATE_DESPESA = "insert into despesas"
            + " (DESCRICAO, VALOR, PAGO, CPF_USUARIO_FUNCIONARIO) "
            + " values((?),(?),(?),(?))";
    private static final String SQL_FIND_DESPESA = "select * from despesas";
    private Conexao conexao;
    
    public DespesasDao(Conexao con) {
        this.conexao = con;
    }
    
    public void cadDespesa(Despesa desp) throws DAOException, ClassNotFoundException{
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_DESPESA);
            ps.setString(1, desp.getDescricao());
            ps.setDouble(2, desp.getValor());
            ps.setString(3, desp.getPago());
            ps.setString(4, desp.getCpfUsuario());
            

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
    
    public ArrayList<Despesa> buscarDespesa(String filtro, int status) {

        int i;
        ArrayList<Despesa> despesas = new ArrayList<Despesa>();

        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Despesa d = null;

        try {
            c = new Conexao().getCon();

            ps = c.prepareStatement(SQL_FIND_DESPESA);
           

            rs = ps.executeQuery();

            while (rs.next()){
                d = new Despesa();
                d.setDescricao(rs.getString("descricao"));
                d.setValor(rs.getDouble("valor"));
                d.setPago(rs.getString("pago"));
                d.setCpfUsuario(rs.getString("cpf_Usuario_Funcionario"));
                despesas.add(d);
            }
        } catch (Exception sqlx) {}

        
        if (status == 1) {
            //retorna todas as despesas do banco, portando aqui nao fazemos nada!
        }
        if (status == 2) {
            //retorna todas as despesas cujo CPF = filtro
            for (i = 0; i < despesas.size(); i++) {
                if (!despesas.get(i).getCpfUsuario().equals(filtro)) {
                    despesas.remove(i);
                    i--;
                }
            }
        }
        if (status == 3) {
            //retorna todas as despesas pagas
            for (i = 0; i < despesas.size(); i++) {
                if (!despesas.get(i).getPago().equals("S")) {
                    despesas.remove(i);
                    i--;
                }
            }
        }
        if (status == 4) {
            //retorna todas as despesas nao pagas ainda
            for (i = 0; i < despesas.size(); i++) {
                if (!despesas.get(i).getPago().equals("N")) {
                    despesas.remove(i);
                    i--;
                }
            }
        }

        return despesas;
    }
}
