package Controller;

import Model.Cliente;
import Model.Recibo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CtrRecibo {
    private int lastid;
    
    public boolean addRecibo(Recibo recibo){
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = "INSERT INTO recibos(contato, telefone, email, cliente, previsaopagamento) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, recibo.getContato());
            stmt.setString(2, recibo.getTelefone());
            stmt.setString(3, recibo.getEmail());
            stmt.setString(4, recibo.getCliente());
            stmt.setString(5, recibo.getPrevisao());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                lastid = rs.getInt(1);
                recibo.setIdrecibo(lastid);
            }
                      
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false; 
        }
    }
    
    public static List<Recibo> exibeRecibos(){
        String sql = "SELECT * FROM recibos";
        List<Recibo> retorno = new LinkedList<Recibo>();
        
        Model.DAO bd = Model.DAO.getInstancia();

        try{
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Recibo r = new Recibo();
                r.setIdrecibo(rs.getInt("idrecibo"));
                r.setContato(rs.getString("contato"));
                r.setTelefone(rs.getString("telefone"));
                r.setEmail(rs.getString("email"));
                r.setCliente(rs.getString("cliente"));
                r.setData(rs.getDate("data"));
                r.setStatus(rs.getString("status"));
                r.setPrevisao(rs.getString("previsaopagamento"));
                
                retorno.add(r);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" + e);
        }
        
        return retorno;
    }   
    
    public List<Recibo> procuraRecibo(String id, String nome){
        List<Recibo> retorno = new LinkedList<Recibo>();
        String sql = null;
        ResultSet rs = null;
        
        try{
            Model.DAO bd = Model.DAO.getInstancia();
            
            if(id.equals("0") && nome != "0"){
                sql = "SELECT * FROM recibos WHERE cliente LIKE ?"; 
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setString(1, nome);    
                rs = stmt.executeQuery();
            }
        
            if(id != "0" && nome.equals("0")){
                sql = "SELECT * FROM recibos WHERE idrecibo LIKE ?";  
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setString(1, id); 
                rs = stmt.executeQuery();
            }
            
            while(rs.next()){
                Recibo r = new Recibo();
                r.setIdrecibo(rs.getInt("idrecibo"));
                r.setContato(rs.getString("contato"));
                r.setTelefone(rs.getString("telefone"));
                r.setEmail(rs.getString("email"));
                r.setCliente(rs.getString("cliente"));
                r.setData(rs.getDate("data"));
                r.setStatus(rs.getString("status"));
                r.setPrevisao(rs.getString("previsaopagamento"));
                
                retorno.add(r);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
        
        return retorno;
    }
    
    public boolean editaStatus(int id){      
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = " UPDATE recibos SET status = ? WHERE idrecibo = "+id;
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, "Pago");
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" +e);  
        }
        
        return false; 
    }

   
}
