/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Conexao;
import dao.ConvenioDao;
import dao.DAOException;
import dao.DespesasDao;
import dao.FuncaoDao;
import dao.FuncionarioDao;
import dao.MedicoDao;
import dao.PacienteDao;
import dao.UsuarioDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.CadastrarConvenio;
import view.CadastrarDespesa;
import view.CadastrarFuncao;
import view.CadastrarPaciente;
import view.CadastroFuncionario;
import view.CadastroMedico;

/**
 *
 * @author Leonardo
 */
public class Model {
    Conexao con;
    public Model(){
        con = new Conexao();
        
    }
    
    public void salvarConvenio(String tfNome, String tfCnpj, double desconto){
        ConvenioDao dao = new ConvenioDao(con);

    Convenio conv = new Convenio(tfNome, tfCnpj);
    conv.setDesconto(desconto);

    try {
        dao.cadConvenio(conv);
    } catch (DAOException ex) {
        Logger.getLogger(CadastrarConvenio.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CadastrarConvenio.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void salvarDespesa(String taDescricao, Double tfValor, String pago, String tfCpf){
        DespesasDao dao = new DespesasDao(con);
        Despesa desp = new Despesa(taDescricao, tfValor, pago, tfCpf);
        try {
            dao.cadDespesa(desp);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarDespesa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarDespesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvarFuncao(String tfNome){
        FuncaoDao fDao = new FuncaoDao(con);
        Funcao fn = new Funcao(tfNome);
        try {
            fDao.cadastrarFn(fn);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarFuncao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarFuncao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvarPaciente(String tfCpf, String tfNome, String tfEndereco, String tfTelefone){
        PacienteDao dao = new PacienteDao(con);
        Paciente paciente = new Paciente(tfCpf, tfNome, tfEndereco, tfTelefone);
        try {
            dao.create(paciente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    
    public void salvarFuncionario(int tfCodFn,String nomeFn, Double salario, String userCpf, String userName, String userPassword, Integer userType){
        Funcionario func = new Funcionario(tfCodFn, nomeFn, salario, userCpf, userName,userPassword, userType);
        FuncionarioDao dao = new FuncionarioDao(con);
        try {
            dao.createEmployee(func);
        } catch (DAOException ex) {
            Logger.getLogger(CadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvarMedico(String tfCrm, String tfCpf,String tfNome, String tfSenha,int tfTipo){
        Medico medico = new Medico(tfCrm, tfCpf, tfNome, tfSenha, tfTipo);
        MedicoDao dao = new MedicoDao(con);
        try {
            dao.createDoctor(medico);
        } catch (DAOException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirPaciente(String cpf){
        PacienteDao dao = new PacienteDao(con);
        try {
            dao.delete(cpf);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    public void excluirUsuario(String cpf){
        UsuarioDao dao = new UsuarioDao(con);
        try {
            dao.delete(cpf);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    public void atualizarPaciente(Paciente paciente){
        PacienteDao dao = new PacienteDao(con);
        try {
            dao.update(paciente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    public void atualizarUsuario(Usuario usuario){
        UsuarioDao dao = new UsuarioDao(con);
        try {
            dao.update(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(CadastrarPaciente.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    public Paciente buscarPaciente(String cpf){
        PacienteDao dao = new PacienteDao(con);
        Paciente p = new Paciente();
        try {
            p = dao.buscar(cpf);
        }
        finally{
            return p;
        }
    }
    
    public Usuario buscarUsuario(String cpf){
        UsuarioDao dao = new UsuarioDao(con);
        Usuario p = new Usuario();
        p.setUserCpf(cpf);
        try {
            p = dao.getUser(p);
        }
        finally{
            return p;
        }
    }
    
    
    public Usuario executarLogin(String cpf, String senha){
        Usuario user = new Usuario(cpf, null, senha, 3);
        UsuarioDao userDao = new UsuarioDao(con);
        Usuario foundUser = userDao.getUser(user);
        if( (foundUser.getUserCpf()==null) || !(foundUser.getUserPassword().equals(user.getUserPassword())) ){
            return null;
        } else {
            return user;
        }
    }

    public Conexao getCon() {
        return con;
    }

    public void setCon(Conexao con) {
        this.con = con;
    }
}
