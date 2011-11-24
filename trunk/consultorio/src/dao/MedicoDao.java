/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Medico;
import model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Fabricio
 */
public class MedicoDao {
    
    private static final String SQL_FIND_DOCTOR = 
                "select * from medico md, usuario us where"
            + " md.cpf_Usuario = us.cpf and"
            + " us.cpf=(?)";
    
    private static final String SQL_CREATE_DOCTOR =
            "insert into medico (CRM, CPF_USUARIO) values ((?),(?))";

     private static final String SQL_FIND_ALL_DOCTORS =
                "select * from medico md, usuario us where"
            + " md.cpf_Usuario = us.cpf";
    
    private Conexao conexao;
    
    public MedicoDao(Conexao con) {
        this.conexao = con;
    }

    public MedicoDao() {
        this.conexao = new Conexao();
    }
    
    public Medico findDoctor(Medico user){
        Medico medico = new Medico(null, null, null, null, Integer.SIZE);
        Connection c;
        ResultSet rs;
        PreparedStatement pst;
        try{
            c = conexao.getCon();
            rs = null;
            pst = c.prepareStatement(SQL_FIND_DOCTOR);
            pst.setString(1, user.getUserCpf());
            rs = pst.executeQuery();
            while(rs.next()){
                medico = new Medico(rs.getString("crm"), rs.getString("cpf"),
                        rs.getString("nome"), rs.getString("senha"),
                        rs.getInt("tipo"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medico;
    }
    
    public void createDoctor(Medico medico) throws DAOException, ClassNotFoundException {
	
        UsuarioDao userDao = new UsuarioDao(conexao);
        Usuario user = new Usuario(medico.getUserCpf(),
                     medico.getUserName(), medico.getUserPassword(),
                    medico.getUserType());
        userDao.create(user);
        
        Connection c = null;
        PreparedStatement ps = null;

        boolean transactionState = false;
        try {
            c = conexao.getCon();
            transactionState = c.getAutoCommit();

            c.setAutoCommit(false);

            ps = c.prepareStatement(SQL_CREATE_DOCTOR);
            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getUserCpf());
            
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new DAOException("MedicoDAO: insert failed");
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


    /*Inserido para listar médicos a serem escolhidos
     quando for cadastrar uma consulta */
    public ArrayList<Object> findAllMedicos(){
        Medico medico = new Medico(null, null, null, null, Integer.SIZE);
        Connection c;
        ResultSet rs;
        ArrayList<Object> medicos = new ArrayList<Object>();
        PreparedStatement pst;

        try{
            c = conexao.getCon();
            rs = null;
            pst = c.prepareStatement(SQL_FIND_ALL_DOCTORS);
            rs = pst.executeQuery();
            while(rs.next()){
                medico = new Medico(rs.getString("crm"), rs.getString("cpf"),
                        rs.getString("nome"), rs.getString("senha"),
                        rs.getInt("tipo"));
                medicos.add(medico);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return medicos;
    }
}
