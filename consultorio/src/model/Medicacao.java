/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import dao.*;
import java.util.ArrayList;

/**
 *
 * @author tais
 */
public class Medicacao {
    private long idMedicacao;
    private String descricao;
    private String posologia;
    private String duracao;

    public String getDescricao() {
        return descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public long getIdMedicacao() {
        return idMedicacao;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setIdMedicacao(long idMedicacao) {
        this.idMedicacao = idMedicacao;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public int salvarMedicacao(){
        return MedicacaoDAO.getInstance().salvar(this);
    }

    public void removerMedicacao(){
        MedicacaoDAO.getInstance().remover(this);
    }

    public ArrayList<Medicacao> buscarMedicacao(String descricao){
        return MedicacaoDAO.getInstance().buscar(descricao);
    }
}
