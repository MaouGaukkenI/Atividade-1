
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    Connection conn = null;
    String url = "jdbc:mysql://localhost/uc11";
    String user = "root";
    String senha = "1234";
    
    public Connection connectDB() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, this.user, this.senha);
            conn.setAutoCommit(false);

            System.err.println("Conexão bem sucedida, bem vindo usúario: " + user);
            
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Não foi possivel realizar a conexão, Codigó de erro: " + ex.getMessage());
            return null;
        }
    }
}
