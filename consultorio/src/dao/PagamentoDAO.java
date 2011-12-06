/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Convenio;
import model.Pagamento;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author tais
 */
public class PagamentoDAO {
    private static PagamentoDAO pagamentoDAO = null;
    private Connection conn;
    
    private static final String SQL_FIND_PAGAMENTO = "select * from pagamento p, convenio c where p.idConv = c.idConv";

    /*Evitar de ficar criando classes DAO.
     Neste casso apenas 1 é necessária para as operaçẽos com consulta
     */
    private PagamentoDAO(){
        try {
            conn = new Conexao().getCon();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PagamentoDAO getInstance(){
        if(pagamentoDAO == null){
            pagamentoDAO = new PagamentoDAO();
        }
        return pagamentoDAO;
    }

    /*-------------- CONSULTAS NO BANCO -----------------*/
    public int salvar(Pagamento c){
        System.out.println("Pagamento.salvar");
        String query;

        Statement stm;
        Convenio convenio = c.getIdConv();
        if(convenio == null){
            query = "INSERT INTO pagamento (codigoConsulta, idConv, valor, vencimento)" +
                " VALUES ("+c.getCodigoConsulta()+",0 , " +
                ""+c.getValor()+", '"+c.getVencimento()+"');";
        }
        else{
            query = "INSERT INTO pagamento (codigoConsulta, idConv, valor, vencimento)" +
                " VALUES ("+c.getCodigoConsulta()+", '"+convenio.getCnpj()+"', " +
                ""+c.getValor()+", '"+c.getVencimento()+"');";
        }
        

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
            return 0;
	}

        return 1;
    }

    public void remover(Pagamento c){
       Statement stm;

       /*Alterar consulta no banco aqui*/
       String query = "DELETE FROM pagamento WHERE idPag="+c.getIdPag()+";";

	try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
	} catch (Exception e) {
            e.printStackTrace();
	}
    }

    public Pagamento buscar (long idconsulta){
        
       String query = "SELECT * FROM pagamento WHERE codigoConsulta="+idconsulta+";";

       ResultSet rs;
       Statement stm;
       Pagamento c = null;

        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Pagamento();
                c.setIdPag(rs.getLong(1));
                c.setCodigoConsulta(rs.getLong(2));

                c.setIdConv(new ConvenioDao().getConvenio(rs.getDouble(3)+""));
                
                c.setValor(rs.getDouble(4));
                c.setVencimento(rs.getString(5));
            }

	} catch (Exception e) {
            e.printStackTrace();
	}

        return c;
    }
    
    public ArrayList<Pagamento> buscarPagamento(String filtro, int status) {

        int i;
        ArrayList<Pagamento> pagtos = new ArrayList<Pagamento>();

        //KARINA CRIAR AQUI UMA CONSULTA PARA PREENCHER O ARRAY LIST pagtos COM TODOS OS PAGTOS DA TABELA PAGAMENTO

        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        Pagamento p = null;
        Convenio conv = null;

        try {
            c = new Conexao().getCon();

            ps = c.prepareStatement(SQL_FIND_PAGAMENTO);

            rs = ps.executeQuery();

            while (rs.next()) {
                conv = new Convenio();
                p = new Pagamento();
                conv.setNome(rs.getString("c.nome"));
                p.setCodigoConsulta(rs.getInt("p.codigoConsulta"));
                p.setIdConv(conv);
                p.setValor(rs.getDouble("p.valor"));
                p.setVencimento(rs.getString("p.vencimento"));
                pagtos.add(p);
            }
        } catch (Exception sqlx) {
        }

        if (status
                == 1) {
            //retorna todos os pagamentos do banco, portanto nada fazemos aqui!
        }

        if (status
                == 2) {
            //retorna todos os pagamentos cujo cod.Consulta = filtro
            for (i = 0; i < pagtos.size(); i++) {
                String codConsulta = "";
                codConsulta = codConsulta + pagtos.get(i).getCodigoConsulta();
                if (!codConsulta.equals(filtro)) {
                    pagtos.remove(i);
                    i--;
                }
            }
        }

        if (status
                == 3) {
            //retorna todos os pagamentos cujo nome do convenio = filtro
            for (i = 0; i < pagtos.size(); i++) {
                if (!pagtos.get(i).getIdConv().getNome().equals(filtro)) {
                    pagtos.remove(i);
                    i--;
                }
            }
        }

        if (status
                == 4) {
            //retorna todos os pagamentos recebidos
            String dataSist;
            dataSist = getDateTime();
            int dataSistema, venc;

            dataSistema = converteDataParaInteiro(dataSist);

            for (i = 0; i < pagtos.size(); i++) {
                venc = converteDataParaInteiro(pagtos.get(i).getVencimento());
                if (venc >= dataSistema) { // remove os pagtos nao recebidos
                    pagtos.remove(i);
                    i--;
                }
            }
        }

        if (status
                == 5) {
            //retorna todos os pagamentos a receber
            String dataSist;
            dataSist = getDateTime();
            int dataSistema, venc;

            dataSistema = converteDataParaInteiro(dataSist);

            for (i = 0; i < pagtos.size(); i++) {
                venc = converteDataParaInteiro(pagtos.get(i).getVencimento());
                if (venc < dataSistema) { // remove os pagtos recebidos
                    pagtos.remove(i);
                    i--;
                }
            }
        }

        return pagtos;
    }
//esse metodo converte uma data para inteiro para poder compara-la com outra data
//EX: 12/11/2010 return 20101112 (de traz para frente: ano,mes,dia)

    int converteDataParaInteiro(String data) {
        String dia, mes, ano, dataInvertida;
        int dataInv;

        dia = data.substring(0, 2);
        mes = data.substring(3, 5);
        ano = data.substring(6, 10);

        dataInvertida = ano + mes + dia;

        dataInv = Integer.parseInt(dataInvertida);
        return dataInv;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }
}
