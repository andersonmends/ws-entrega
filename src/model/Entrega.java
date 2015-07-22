package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;

public class Entrega {

	private int idEntrega;
	private int idVenda;

	private String nomeCliente;
	private String telefone;
	private String cpf;
	private String status;
	private String melhorHorario;
	private String endereco;
	private String dataCadastro;
	private String dataEntrega;
	private String produto;
	private int entregador;
	private int ordemEntrega;
	private String observacao;

	public Entrega(){
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		this.dataCadastro = formatador.format(
				Calendar.getInstance().getTime() );
		
		this.ordemEntrega = 0;
		this.nomeCliente = ""; 
		this.telefone = ""; 
		this.cpf = "";
		this.status = ""; 
		this.melhorHorario = ""; 
		this.endereco = ""; 
		this.dataEntrega = ""; 
		this.produto = ""; 
		this.observacao = "";

	}
	
	public Entrega(String json){
		Entrega entrega = new Gson().fromJson(json, Entrega.class);
	
		this.ordemEntrega = entrega.getOrdemEntrega();
		this.nomeCliente = entrega.getNomeCliente(); 
		this.telefone = entrega.getTelefone(); 
		this.cpf = entrega.getCpf();
		this.status = entrega.getStatus(); 
		this.melhorHorario = entrega.getMelhorHorario(); 
		this.endereco = entrega.getEndereco(); 
		this.dataEntrega = entrega.getDataEntrega(); 
		this.produto = entrega.getProduto(); 
		this.observacao = entrega.getObservacao();

	}

	

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMelhorHorario() {
		return melhorHorario;
	}

	public void setMelhorHorario(String melhorHorario) {
		this.melhorHorario = melhorHorario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getEntregador() {
		return entregador;
	}

	public void setEntregador(int entregador) {
		this.entregador = entregador;
	}

	public int getOrdemEntrega() {
		return ordemEntrega;
	}

	public void setOrdemEntrega(int ordemEntrega) {
		this.ordemEntrega = ordemEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void imprimir(){
		System.out.println(
				"nomeCliente: " + nomeCliente +
				"telefone: " + telefone +
				"cpf: " + cpf +
				"status: " + status +
				"melhorHorario: " + melhorHorario +
				"endereco: " + endereco + 
				"dataCadastro: " + dataCadastro +
				"dataEntrega: " + dataEntrega +
				"produto: " + produto +
				"entregador: " + entregador +
				"ordemEntrega: " + ordemEntrega 
				);
	}
	
	public String toJson(){
		return new Gson().toJson(this);
//		return (
//		"{nomeCliente: " + nome_cliente +
//		", telefone: " + telefone +
//		", cpf: " + cpf +
//		", status: " + status +
//		", melhorHorario: " + melhorHorario +
//		", endereco: " + endereco + 
//		", dataCadastro: " + dataCadastro +
//		", dataEntrega: " + dataEntrega +
//		", produto: " + produto +
//		", entregador: " + entregador +
//		", ordemEntrega: " + ordemEntrega +"}"
//		);
	}

}
