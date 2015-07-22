package service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import model.Entrega;

@WebService
public interface IBaseService {

		//  cliente
		@WebMethod @WebResult(name="Status") String getStatusEntrega(int idEntrega);

		//	entregador 
		@WebMethod String getRota(int idEntregador, String data);//data?
		@WebMethod String setObservacao(int idVenda, String observacao);
		@WebMethod String setEntregaConcluida(int idVenda);

		//	Vendedor
		@WebMethod String cadastrarEntrega(Entrega entrega);

		//	Gerente (gerencia as entregas)
		@WebMethod String agendarEntrega(int idVenda, String data);
		@WebMethod String associarEntregaAoEntregador(int idVenda, int idEntregador);
		@WebMethod ArrayList<Entrega> getEntregasNaoConcluidas();
		@WebMethod ArrayList<Entrega> getEntregasNaoAgendadas();
		@WebMethod String remarcarEntrega(int idVenda, String data);
		@WebMethod String cancelarEntrega(int idVenda, String motivo);

}
