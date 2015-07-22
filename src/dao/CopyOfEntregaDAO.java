package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;

import model.Entrega;

public class CopyOfEntregaDAO extends Conexao {
	public CopyOfEntregaDAO() {
		super();
	}

	public void cadastrarEntrega(Entrega entrega) throws SQLException {

		String sql = "INSERT INTO entrega (id_venda, nome_cliente, cpf, "
				+ "telefone, melhor_horario, endereco, data_cadastro, produto, id_entregador, data_entrega, status) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement st = conexao.prepareStatement(sql);
		st.setInt(1, entrega.getIdVenda());
		st.setString(2, entrega.getNomeCliente());
		st.setString(3, entrega.getCpf());
		st.setString(4, entrega.getTelefone());
		st.setString(5, entrega.getMelhorHorario());
		st.setString(6, entrega.getEndereco());
		st.setString(7, entrega.getDataCadastro());
		st.setString(8, entrega.getProduto());
		st.setInt(9, entrega.getEntregador());
		st.setString(10, entrega.getDataEntrega());
		st.setString(11, entrega.getStatus());

		boolean result = st.execute();
		st.close();
	}

	// cliente
	public String getStatusEntrega(int idVenda) throws SQLException {
		return getEntrega(idVenda).getStatus();
	}

	//gerente
	// @WebMethod String remarcarEntrega(int idEntrega);
	public boolean remarcarEntrega(int id_venda, Date data){
		return agendarEntrega(id_venda, data);
	}
	
//	Gerente (gerencia as entregas)
//		@WebMethod String agendarEntrega(int idEntrega, Date data);
	public boolean agendarEntrega(int id_venda, Date data){
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		
		String sql = "UPDATE entrega SET data_entega=? WHERE id_venda = ?";
		try {
			PreparedStatement st = conexao.prepareStatement(sql);
		st.setString(1, formatador.format(data) );
		st.setInt(2, id_venda);
		ResultSet rs = st.executeQuery();
		
		if (!rs.next()) {
			return false;
		}
			return true;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}
//		@WebMethod String associarEntregaAoEntregador(int idEntrega, int idEntregador);
	public boolean associarEntregaAoEntregador(int idVenda, int idEntregador){
		
		String sql = "UPDATE entrega SET id_entregador=? WHERE id_venda = ?";
		try {
			PreparedStatement st = conexao.prepareStatement(sql);
		st.setInt(1, idEntregador );
		st.setInt(2, idVenda);
		ResultSet rs = st.executeQuery();
		
		if (!rs.next()) {
			return false;
		}
			return true;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}

	//		@WebMethod String listarEntregares();
	
//		@WebMethod String agendarEntregasNaoConcluidas();
	private Entrega getEntregasNaoConcluidas() throws SQLException {

		PreparedStatement st = conexao
				.prepareStatement("select * from entrega where status !='entregue'");
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			return null;
		}
		return retornarEntrega(rs);
	}
//		@WebMethod String agendarEntregaNaoAgendadas();
	private Entrega agendarEntregaNaoAgendadas() throws SQLException {//TODO VER DATA

		PreparedStatement st = conexao
				.prepareStatement("select * from entrega where data_entrega =''");
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			return null;
		}
		return retornarEntrega(rs);
	}
//		@WebMethod String agendarEntregasEntreDatas(Date dInicial, Date dFinal);//TODO VER DATA OU NAO FAZER
//		remarcar? (vale a pena ou não)
	
	
	// // entregador
	// @WebMethod String getRota(int idEntregador);//data?
	private ArrayList<Entrega> getEntregaPorEntregador(int id_entregador, Date data) throws SQLException {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

		String sql = "SELECT * FROM entrega WHERE id_entregador = ? AND data_entrega = ?";
		PreparedStatement st = conexao.prepareStatement(sql);
		st.setInt(1, id_entregador);
		st.setString(2, formatador.format(data) );
		ResultSet rs = st.executeQuery();
		
		Entrega entrega; 
		ArrayList<Entrega> lista = new ArrayList<Entrega>();
		if (rs.next()) {
			entrega = retornarEntrega(rs);
			lista.add(entrega);
		} 
//		return null;
		return lista;
	}
	
	// @WebMethod String informarProblema(int idEntrega);
	private boolean informarProblema(int id_venda, String observacao) throws SQLException {//TODO MELHORAR A MENSAGEM
		String sql = "UPDATE entrega SET observacao=? WHERE id_venda = ?";
		PreparedStatement st = conexao.prepareStatement(sql);
		st.setString(1, observacao);
		st.setInt(2, id_venda);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			return false;
		}
		return true;
	}

	
	// @WebMethod String setEntregaConcluida(int idEntrega);
	private boolean setEntregue(int id_venda) throws SQLException {
		String sql = "UPDATE entrega SET status='entregue' WHERE id_venda = ?";
		PreparedStatement st = conexao.prepareStatement(sql);
		st.setInt(1, id_venda);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			return false;
		}
		return true;
	}
	
	
	//AUXILIARES
	private Entrega getEntrega(int id_venda) throws SQLException {

		PreparedStatement st = conexao
				.prepareStatement("select * from entrega where id_venda = ?");
		st.setInt(1, id_venda);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {
			return null;
		}
		return retornarEntrega(rs);
	}
	
	private Entrega retornarEntrega(ResultSet rs) throws NullPointerException, SQLException{
        Entrega entrega = new Entrega();            
        entrega.setIdEntrega(rs.getInt("id_entrega"));
        entrega.setIdVenda(rs.getInt("id_venda"));
       
        entrega.setNomeCliente(rs.getString("nome_cliente"));
        entrega.setCpf(rs.getString("cpf"));
        entrega.setTelefone(rs.getString("telefone"));
        
        entrega.setMelhorHorario(rs.getString("melhor_horario"));
        entrega.setEndereco(rs.getString("endereco"));
        entrega.setDataCadastro(rs.getString("data_cadastro"));
        entrega.setProduto(rs.getString("produto"));
        entrega.setEntregador(rs.getInt("id_entregador"));
        entrega.setDataEntrega(rs.getString("data_entrega"));
        return entrega;        
    }
}

//public boolean alterarDados(){        
//this.conecta();     
// boolean testa = false;        
//   
// try {        
//	    String sql = "UPDATE entrada SET status='realizada' WHERE id_venda = 'a string'";
////    String query = "update entrada "+        
////                "set status = '', " +      
////                 '"\"' " +        
////                "where nome = \"" + nome + "\"";        
//   
//    int linhas = stm.executeUpdate(query);        
//            
//    if (linhas > 0)        
//       testa = true;        
//    else        
//       testa = false;        
//   
// }catch (SQLException e){System.out.println("Erro na inserção:" + e.getMessage());}        
//         
// return testa;        
//}  

//public void alterarProduto(Produto prod){
//  try {
//      PreparedStatement sst = conexao.prepareStatement("update produto set codigoBarra = ?, descricao = ?, estoque = ?, "
//              +" estoqueMin = ?, ativo = ?, preco = ?, custo = ? where codigo = ?");
//      sst.setString    (1, prod.getCodigoDeBarras());
//      sst.setString    (2, prod.getDescricao());
//      sst.setInt       (3, prod.getQuantidade());
//      sst.setInt       (4, prod.getQuantidadeMinima());
//      sst.setBoolean   (5, prod.getAtivo());
//      sst.setFloat     (6, prod.getPreco());
//      sst.setFloat     (7, prod.getCusto());
//      sst.setInt       (8, prod.getCodigo());
//      sst.execute();            
//      JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
//  } catch (SQLException ex) {
//      JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar excluir o produto "+ex.getMessage());
//  }catch (NullPointerException ex) {
//      JOptionPane.showMessageDialog(null, "Favor informar um produto");
//  }
//}

//boolean testa = false;        
//try {        
//String query = "update cliente "+        
//          "set nome = '" + nome + "', " +      
//           '"\"' " +        
//          "where nome = \"" + nome + "\"";        
//
//int linhas = stm.executeUpdate(query);        
//      
//if (linhas > 0)        
// testa = true;        
//else        
// testa = false;        
//}catch (SQLException e){System.out.println("Erro na inserção:" + e.getMessage());}        
//return testa;  

//ver
//String sql = "UPDATE my_table SET col_string='a new string' WHERE col_string = 'a string'";
//PreparedStatement query = p.prepareStatement("update cadastrados set nome='NomeTeste' where email = ? and senha = ?");