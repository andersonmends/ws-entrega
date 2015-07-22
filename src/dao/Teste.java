package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.Entrega;

public class Teste {

	public static void main(String[] args) {
//		Date data = Calendar.getInstance().getTime();
//		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(data.toString());
//		System.out.println(formatador.format(data));
		
		int idVenda = 2;
		int idEntregador =1;
		String end = "Rua Miguel Leite, 67, Brasília, Arapiraca-AL, 573131-110";
		String horario = "MANHA";
		String cliente = "Anderson";
		String produto = "TV";
		String cpf = "22222222222";
		String telefone ="87999918952";
		String dataEntrega = "2015-07-05";
		String problema = "";
		
		Entrega entrega = new Entrega();
		entrega.setEndereco(end);
		entrega.setIdVenda(idVenda);
		entrega.setMelhorHorario(horario);
		entrega.setNomeCliente(cliente);
		entrega.setProduto(produto);
		entrega.setTelefone(telefone);
		entrega.setCpf(cpf);
		
		//INSERI NO BANCO
		EntregaDAO dao = new EntregaDAO();
		dao.cadastrarEntrega(entrega);
		
	}
}
