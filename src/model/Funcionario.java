/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabricio
 */
public class Funcionario extends Usuario {
    private Integer idFn;
    private String nomeFn;
    private Double salario;

    public Funcionario(Integer idFn, String nomeFn, Double salario, String userCpf, String userName, String userPassword, Integer userType) {
        super(userCpf, userName, userPassword, userType);
        this.idFn = idFn;
        this.nomeFn = nomeFn;
        this.salario = salario;
    }

    public Integer getIdFn() {
        return idFn;
    }

    public void setIdFn(Integer idFn) {
        this.idFn = idFn;
    }

    public String getNomeFn() {
        return nomeFn;
    }

    public void setNomeFn(String nomeFn) {
        this.nomeFn = nomeFn;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFn=" + idFn + ", nomeFn=" + nomeFn + ", salario=" + salario + '}';
    }
    
}
