package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    Connection conn = null;
    String url = "jdbc:mysql://localhost/uc11?useTimezone=true&serverTimezone=UTC";
    String user = "root";
    String senha = "1234";
    
    public Connection connectDB() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, this.user, this.senha);
            conn.setAutoCommit(false);

            System.out.println("Conexão bem sucedida, bem vindo usúario: " + user);
            
            return conn;
        }  catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado: " + ex.getMessage());
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a conexão, Código de erro: " + ex.getMessage());
            return null;
        }
    }
}
