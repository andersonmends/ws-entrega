package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoDAO{
	
    protected Connection conexao;
    private ConexaoDAO conexaoDAO= null;
    
    private ConexaoDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/m_entrega","admin","admin");
            System.out.println("conectado");
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
    }
    
    public Connection getConexao(){
    	if (conexaoDAO == null){
    		conexaoDAO = new ConexaoDAO();
    	}
    	return conexao;
    }
    
}
