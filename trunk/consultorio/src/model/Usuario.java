/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Conexao;
import dao.UsuarioDao;

/**
 *
 * @author Fabricio
 */
public class Usuario {
    private String userCpf;
    private String userName;
    private String userPassword;
    private Integer userType;

    public Usuario() {

    }
    
    public Usuario(String userCpf, String userName, String userPassword, Integer userType) {
        this.userCpf = userCpf;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Usuario{" + "userCpf=" + userCpf + ", userName=" + userName + ", userPassword=" + userPassword + ", userType=" + userType + '}';
    }


    public static boolean usuarioCadastrado(String digitos){
        UsuarioDao u = new UsuarioDao(new Conexao());
        if(u.getUser(new Usuario(digitos, null, null, Integer.MIN_VALUE)).getUserName() == null){
                return false;
        }
        return true;
    }
}
