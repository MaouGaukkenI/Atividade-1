package Code;



import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    String comand = "";
    
    public void cadastrarProduto (ProdutosDTO produto, Connection conect){
        
        String nome = produto.getNome();
        int valor = produto.getValor();
        String status = produto.getStatus();
        
        ps = null;
        rs = null;
        conn = conect;
        try {
            comand = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(comand, java.sql.Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nome);
            ps.setInt(2, valor);
            ps.setString(3, status);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nProduto adicionado com sucesso");
                conn.commit();
            } else {
                System.err.println("\nProdotu não cadastrado, tente novamente");
            }
        } catch (SQLException ex) {
            System.err.println("\nNão foi possível adicionar os dados. Código de erro: " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Erro: " + e.getMessage());
                }
            }
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
}

