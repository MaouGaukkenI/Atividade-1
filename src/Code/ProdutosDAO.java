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
        }
    }
    
    public void venderProduto(int id, Connection conect){
        ps = null;
        rs = null;
        conn = conect;
        try {
            comand = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
            ps = conn.prepareStatement(comand, java.sql.Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nProduto vendido com sucesso");
                conn.commit();
            } else {
                System.err.println("\nNão foi possivel vender o produto, tente novamente");
            }
        } catch (SQLException ex) {
            System.err.println("\nNão foi possível alterar os dados, Código de erro: " + ex.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(Connection conect){
        ArrayList<ProdutosDTO> Result = new ArrayList();
        
        ps = null;
        rs = null;
        conn = conect;
        try {
            System.err.println("Definindo os comandos");
            comand = "SELECT * FROM produtos";
            ps = conn.prepareStatement(comand);
            rs = ps.executeQuery();
            System.err.println("Recuperando e exibindo os dados...");

            while (rs.next()) {
                int id = rs.getInt("id");
                String Nome = rs.getString("nome");
                int Valor = rs.getInt("valor");
                String Status = rs.getString("status");
                
                ProdutosDTO pd = new ProdutosDTO();
                pd.setId(id);
                pd.setNome(Nome);
                pd.setStatus(Status);
                pd.setValor(Valor);
                
                Result.add(pd);
            }
        } catch (SQLException ex) {
            System.err.println("erro ao carregar dados do banco de dados, Codigo de erro: " + ex.getMessage());
        }
        
        return Result;
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(Connection conect){
        ArrayList<ProdutosDTO> Result = new ArrayList();
        
        ps = null;
        rs = null;
        conn = conect;
        try {
            System.err.println("Definindo os comandos");
            comand = "SELECT * FROM produtos WHERE status = 'Vendido'";
            ps = conn.prepareStatement(comand);
            rs = ps.executeQuery();
            System.err.println("Recuperando e exibindo os dados...");

            while (rs.next()) {
                int id = rs.getInt("id");
                String Nome = rs.getString("nome");
                int Valor = rs.getInt("valor");
                String Status = rs.getString("status");
                
                ProdutosDTO pd = new ProdutosDTO();
                pd.setId(id);
                pd.setNome(Nome);
                pd.setStatus(Status);
                pd.setValor(Valor);
                
                Result.add(pd);
            }
        } catch (SQLException ex) {
            System.err.println("erro ao carregar dados do banco de dados, Codigo de erro: " + ex.getMessage());
        }
        
        return Result;
    }
}

