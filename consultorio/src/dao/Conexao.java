/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fabricio
 */
public class Conexao {
    private String url = "jdbc:mysql://localhost:3306/consultorio";
    private String user = "root";
    private String pass = "root";
    private Connection con;

    public Conexao() {
    }

    public Connection getCon() throws ClassNotFoundException {
        if(con!=null){
            return con;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url,user,pass);
            } catch (SQLException e){
                System.out.println("Problemas com o BD"+e);
            }
        }
        return con;
    }
    
}
