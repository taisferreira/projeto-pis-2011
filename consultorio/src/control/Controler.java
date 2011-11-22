/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Model;
import model.Usuario;


/**
 *
 * @author Leonardo
 */
public class Controler {
    private Model model;
    public Controler(Model m){
        model = m;
    }
    
    public void salvarConvenio(String tfNome, String tfCnpj){
        model.salvarConvenio(tfNome, tfCnpj);
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
    
    public void salvarPaciente(String tfCpf, String tfNome, String tfEndereco, String tfTelefone){
        model.salvarPaciente(tfCpf, tfNome, tfEndereco, tfTelefone);
    }
    
    public void salvarFuncionario(String tfCodFn,String nomeFn, String salario, String userCpf, String userName, char[] userPassword, String userType){
        Double tmp;
        int tfCodFnInt, userTypeInt;
        String password;
        password = new String(userPassword);
        tmp = Double.parseDouble(salario);
        tfCodFnInt = Integer.parseInt(tfCodFn);
        userTypeInt = Integer.parseInt(userType);
        model.salvarFuncionario(tfCodFnInt, nomeFn, tmp, userCpf, userName, password, userTypeInt);
    }
    
    public void salvarMedico(String tfCrm, String tfCpf,String tfNome, char[] tfSenha,String tfTipo){
        int tfTipoInt;
        String tfSenhaString;
        tfTipoInt = Integer.parseInt(tfTipo);
        tfSenhaString = new String(tfSenha);
        model.salvarMedico(tfCrm, tfCpf, tfNome, tfSenhaString, tfTipoInt);
    }
    
    
    public void excluirPaciente(String cpf){
        model.excluirPaciente(cpf);
    }
    
    public void excluirUsuario(String cpf){
        model.excluirUsuario(cpf);
    }
    
    public Usuario executarLogin(String cpf, String senha){
        return model.executarLogin(cpf, senha);
    }
}
