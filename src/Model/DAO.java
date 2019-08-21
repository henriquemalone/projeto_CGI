package Model;
import java.sql.*;
import java.sql.DriverManager;
/**
 * @desc Classe singleton para acesso a Banco de Dados Mysql
 */
public final class DAO{

    private Connection con;
    private static DAO bd;
    private DAO() {
        //String url= "jdbc:mysql://192.168.15.31:3306/";
        //String url= "jdbc:mysql://192.168.15.254:3306/";
        String url= "jdbc:mysql://localhost:3306/";
        String banco = "cgi";
        String driver = "com.mysql.jdbc.Driver"; 
        String usuario = "root";
        String senha = "123456789";
        try {
            Class.forName(driver).newInstance();
            this.con = (Connection)DriverManager.getConnection(url+banco,usuario,senha);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    //Este método deve ser sincronizado, ou seja
    //preparado para lidar com Threads, visto que não se pode
    //abrir duas conexoes no caso do metodo ser chamado ao mesmo tempo
    //em dois lugares diferentes.
    public static synchronized DAO getInstancia() {
        if ( bd == null ) {
            bd = new DAO();
        }
        return bd;
 
    }
    
    public Connection getConexao(){
        return con;
    }
 
}