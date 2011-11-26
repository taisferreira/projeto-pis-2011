/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Funcao;
import model.Model;
import model.Paciente;
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
    
    public void salvarPaciente(String tfCpf, String tfNome, String tfEndereco, String tfTelefone){
        model.salvarPaciente(Misc.getDigitos(tfCpf), tfNome, tfEndereco, Misc.getDigitos(tfTelefone));
    }
    
    public void salvarFuncionario(String tfCodFn,String nomeFn, String salario, String userCpf, String userName, char[] userPassword, String userType){
        Double tmp;
        int tfCodFnInt, userTypeInt;
        String password;
        password = new String(userPassword);
        tmp = Double.parseDouble(salario);
        tfCodFnInt = Integer.parseInt(tfCodFn);
        userTypeInt = Integer.parseInt(userType);
        model.salvarFuncionario(tfCodFnInt, nomeFn, tmp, Misc.getDigitos(userCpf), userName, password, userTypeInt);
    }
    
    public void salvarMedico(String tfCrm, String tfCpf,String tfNome, char[] tfSenha,String tfTipo){
        int tfTipoInt;
        String tfSenhaString;
        tfTipoInt = Integer.parseInt(tfTipo);
        tfSenhaString = new String(tfSenha);
        model.salvarMedico(tfCrm, Misc.getDigitos(tfCpf), tfNome, tfSenhaString, tfTipoInt);
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
}
