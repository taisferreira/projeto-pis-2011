/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import model.Despesa;
import model.Funcao;
import model.Model;
import model.Paciente;
import model.Pagamento;
import model.Usuario;
import util.Misc;


/**
 *
 * @author Leonardo
 */
public class Controler {
    private Model model;
    public Controler(Model m){
        model = m;
    }
    
    public void salvarConvenio(String tfNome, String tfCnpj, double desconto){
        model.salvarConvenio(tfNome, tfCnpj, desconto);
    }
    
    public void salvarDespesa(String taDescricao, Double tfValor, int pg, String tfCpf){
        String pago;
        if (pg == 0) {
            pago = "S";
        } else {
            pago = "N";
        }
        model.salvarDespesa(taDescricao, tfValor, pago, tfCpf);
    }
    
    public void salvarFuncao(String tfNome){
        model.salvarFuncao(tfNome);
    }
    
    public boolean salvarPaciente(String tfCpf, String tfNome, String tfEndereco, String tfTelefone){
        if(Controler.pacienteCadastrado(Misc.getDigitos(tfCpf))){
            return false;
        }
        model.salvarPaciente(Misc.getDigitos(tfCpf), tfNome, tfEndereco, Misc.getDigitos(tfTelefone));
        return true;
    }
    
    public boolean salvarFuncionario(String tfCodFn,String nomeFn, String salario, String userCpf, String userName, char[] userPassword, String userType){
        Double tmp;
        int tfCodFnInt, userTypeInt;
        String password;
        password = new String(userPassword);
        if(salario.isEmpty()){
            tmp = 0.0;
        }
        else{
            tmp = Double.parseDouble(salario);
        }
        if(tfCodFn.equals("S")){
            tfCodFnInt = 1;
        }
        else{
            tfCodFnInt = Integer.parseInt(tfCodFn);
        }
        userTypeInt = Integer.parseInt(userType);
        if(Controler.usuarioCadastrado(Misc.getDigitos(userCpf))){
            return false;
        }
        else{
            model.salvarFuncionario(tfCodFnInt, nomeFn, tmp,
                    Misc.getDigitos(userCpf), userName, password, userTypeInt);
            return true;
        }
    }
    
    public boolean salvarMedico(String tfCrm, String tfCpf,String tfNome, char[] tfSenha,String tfTipo){
        int tfTipoInt;
        String tfSenhaString;
        tfTipoInt = Integer.parseInt(tfTipo);
        tfSenhaString = new String(tfSenha);
        if(Controler.usuarioCadastrado(Misc.getDigitos(tfCpf))){
            return false;
        }
        else{
           model.salvarMedico(tfCrm, Misc.getDigitos(tfCpf), tfNome, tfSenhaString, tfTipoInt);
           return true;
        }
        
    }
    
    
    public void excluirPaciente(String cpf){
        model.excluirPaciente(cpf);
    }
    
    public void excluirUsuario(String cpf){
        model.excluirUsuario(cpf);
    }
    
    public Paciente buscarPaciente(String cpf){
        return model.buscarPaciente(cpf);
    }
    
    public void atualizarPaciente(Paciente p) {
        model.atualizarPaciente(p);
    }
    
    public Usuario buscarUsuario(String cpf){
        return model.buscarUsuario(cpf);
    }
    
    public void atualizarUsuario(Usuario p) {
        model.atualizarUsuario(p);
    }
    
    public Usuario executarLogin(String cpf, String senha){
        return model.executarLogin(cpf, senha);
    }

    public ArrayList<Funcao> buscaFuncao() {
        return model.buscarFuncao();
    }
          
    public ArrayList<Despesa> buscaDespesa(String filtro, int status) {
        return model.buscarDespesa(filtro, status);
    }
    
    public ArrayList<Pagamento> buscaRecebimento(String filtro, int status) {
        return model.buscarRecebimento(filtro, status);
    }

    public static boolean pacienteCadastrado(String cpf) {
        if(dao.PacienteDao.buscar(cpf) == null){
            return false;
        }
        return true;
    }
    private static boolean usuarioCadastrado(String digitos) {
        return Usuario.usuarioCadastrado(digitos);
    }
}
