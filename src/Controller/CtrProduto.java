
package Controller;

import Model.Cliente;
import Model.Produto;
import Model.Recibo;
import View.gerarReciboo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CtrProduto {
    
    public boolean addProduto(Produto produto){
        
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = "INSERT INTO produtos(nome, lote, valorUnit, quantidade) VALUES(?,?,?,?)";
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getLote());
            stmt.setDouble(3, produto.getValor());
            stmt.setDouble(4, produto.getQtd());      
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false; 
        }
    }
    
    public static List<Produto> exibeProdutos(){
        String sql = "SELECT * FROM produtos";
        List<Produto> retorno = new LinkedList<Produto>();
        
        Model.DAO bd = Model.DAO.getInstancia();

        try{
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("idProdutos"));
                p.setNome(rs.getString("nome"));
                p.setLote(rs.getString("lote"));
                p.setValor(rs.getDouble("valorUnit"));
                p.setQtd(rs.getDouble("quantidade"));
                
                retorno.add(p);
                
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" + e);
        }
        
        return retorno;
    }

    public List<Produto> procuraProduto(int id, String nome){
        List<Produto> retorno = new LinkedList<Produto>();
        String sql = null;
        ResultSet rs = null;
        
        try{
            Model.DAO bd = Model.DAO.getInstancia();
            
            if(id == 0 && nome != null){
                sql = "SELECT * FROM produtos WHERE nome LIKE ?"; 
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setString(1, nome);    
                rs = stmt.executeQuery();
            }
        
            if(id>0 && nome == null){
                sql = "SELECT * FROM produtos WHERE idProdutos LIKE ?";  
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setInt(1, id); 
                rs = stmt.executeQuery();
            }
            
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("idProdutos"));
                p.setNome(rs.getString("nome"));
                p.setLote(rs.getString("lote"));
                p.setValor(rs.getDouble("valorUnit"));
                p.setQtd(rs.getInt("quantidade"));             
                
                retorno.add(p);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
        
        return retorno;
    }    
    
    public boolean editaProduto(Produto produto){
        
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = " UPDATE produtos SET lote = ?,valorUnit = ?, quantidade = ? WHERE idProdutos = "+produto.getId();
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, produto.getLote());
            stmt.setDouble(2, produto.getValor());
            stmt.setDouble(3, produto.getQtd());
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" +e);  
        }
        
        return false; 
    }
    
    public boolean deletaProduto(int id){
        Model.DAO bd =  Model.DAO.getInstancia();   
        try{
            String sql = " DELETE FROM produtos WHERE idProdutos = "+id; //linha de comando que será usado no mysql
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql); //conecta com o banco de dados
            stmt.execute(); //executa o comando
                
            return true;
        } catch (Exception e){  
            JOptionPane.showMessageDialog(null, "Erro ao deletar o produto!");
            return false;
        }
    }
    
    public boolean addProdutoRecibo(Recibo recibo, int id, String cliente){
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        
        String dataString = formatador.format(data);
        java.sql.Date dataa = null;  
        try {
            dataa = new java.sql.Date(formatador.parse(dataString).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(CtrProduto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
	String testaDataSQL = formatador.format(data.getTime());

        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = "INSERT INTO produtorecibo(idrecibo, nome, qtd, lote, preco, total, data, cliente) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, recibo.getNome());
            stmt.setDouble(3, recibo.getQtd());
            stmt.setString(4, recibo.getLote());
            stmt.setDouble(5, recibo.getPreco());    
            stmt.setDouble(6, recibo.getPreco()*recibo.getQtd()); 
            stmt.setDate(7, dataa);  
            stmt.setString(8, cliente);   
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false; 
        }
    }
    
    public boolean retiraEstoque(Produto produto){
        
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = " UPDATE produtos SET quantidade = ? WHERE idProdutos LIKE "+produto.getId();
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setDouble(1, produto.getQtd());
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" +e);  
        }
        
        return false; 
    }

}
