package com.asccode.model;

public class Pedido {

	private String nomeComprador;
	private String telefoneComprador;
	private String emailComprador;
	private String valorTotalPedido;
	private String formaDePagamento;
	private String numeroDeParcelas;
	private String tipoDeFrete;
	private String pedidoStatus;
	
	public Pedido(){
		
	}

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

	public String getTelefoneComprador() {
		return telefoneComprador;
	}

	public void setTelefoneComprador(String telefoneComprador) {
		this.telefoneComprador = telefoneComprador;
	}

	public String getEmailComprador() {
		return emailComprador;
	}

	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}

	public String getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(String valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public String getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(String numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public String getTipoDeFrete() {
		return tipoDeFrete;
	}

	public void setTipoDeFrete(String tipoDeFrete) {
		this.tipoDeFrete = tipoDeFrete;
	}

	public String getPedidoStatus() {
		return pedidoStatus;
	}

	public void setPedidoStatus(String pedidoStatus) {
		this.pedidoStatus = pedidoStatus;
	}
	
	
}
