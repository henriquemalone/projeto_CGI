package Controller;

import Model.Cliente;
import View.gerarReciboo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;


public class CtrCliente {
    
    public CtrCliente(){
        
    }
    
    public boolean addCliente(Cliente cliente){
        
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = "INSERT INTO clientes(nome, contato, telefone, celular, email, cnpj, endereco, numero, cep, bairro, complemento, cidade, estado) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, cliente.getNomeC());
            stmt.setString(2, cliente.getContato());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getCnpj());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getNumero());
            stmt.setString(9, cliente.getCep());
            stmt.setString(10, cliente.getBairro());
            stmt.setString(11, cliente.getComplemento());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());
            stmt.executeUpdate();
            
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false; 
        }
    }
    
    public static List<Cliente> exibeClientes(){
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new LinkedList<Cliente>();
        
        Model.DAO bd = Model.DAO.getInstancia();

        try{
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
                c.setNomeC(rs.getString("nome"));
                c.setContato(rs.getString("contato"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setEmail(rs.getString("email"));
                c.setCnpj(rs.getString("cnpj"));
                c.setEndereco(rs.getString("endereco"));
                c.setNumero(rs.getString("numero"));
                c.setCep(rs.getString("cep"));
                c.setBairro(rs.getString("bairro"));
                c.setComplemento(rs.getString("complemento"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                
                retorno.add(c);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" + e);
        }
        
        return retorno;
    }    
    
    public boolean deletaCliente(int id){
        Model.DAO bd =  Model.DAO.getInstancia();   
        try{
            String sql = " DELETE FROM clientes WHERE idCliente LIKE "+id; //linha de comando que será usado no mysql
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql); //conecta com o banco de dados
            stmt.execute(); //executa o comando
                
            return true;
        } catch (Exception e){  
            JOptionPane.showMessageDialog(null, "Erro ao deletar o cliente!");
            return false;
        }
    }
    
    public List<Cliente> procuraCliente(int id, String nome){
        List<Cliente> retorno = new LinkedList<Cliente>();
        String sql = null;
        ResultSet rs = null;
        
        try{
            Model.DAO bd = Model.DAO.getInstancia();
            
            if(id == 0 && nome != null){
                sql = "SELECT * FROM clientes WHERE nome = ?"; 
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setString(1, nome);    
                rs = stmt.executeQuery();
            }
        
            if(id>0 && nome == null){
                sql = "SELECT * FROM clientes WHERE idCliente = ?";  
                PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
                stmt.setInt(1, id); 
                rs = stmt.executeQuery();
            }
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
                c.setNomeC(rs.getString("nome"));
                c.setContato(rs.getString("contato"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setCnpj(rs.getString("cnpj"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setNumero(rs.getString("numero"));
                c.setCep(rs.getString("cep"));
                c.setBairro(rs.getString("bairro"));
                c.setComplemento(rs.getString("complemento"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                
                retorno.add(c);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
        
        return retorno;
    }

    public boolean editaCliente(Cliente cliente){
        
        try{
            Model.DAO bd =  Model.DAO.getInstancia();
        
            String sql = " UPDATE clientes SET nome = ?, contato = ?, telefone = ?, celular = ?, email = ?, cnpj = ?, endereco = ?,"
                               + " numero = ?, cep = ?, bairro = ?,  complemento = ?, cidade = ?, estado = ? "
                               + "WHERE idCliente LIKE "+cliente.getId();
            PreparedStatement stmt = bd.getConexao().prepareStatement(sql);
            stmt.setString(1, cliente.getNomeC());
            stmt.setString(2, cliente.getContato());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCelular());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getCnpj());
            stmt.setString(7, cliente.getEndereco());
            stmt.setString(8, cliente.getNumero());
            stmt.setString(9, cliente.getCep());
            stmt.setString(10, cliente.getBairro());
            stmt.setString(11, cliente.getComplemento());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());
            stmt.executeUpdate();
         
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERRO:" +e);  
        }
        
        return false; 
    }
    
}
