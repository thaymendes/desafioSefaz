package br.com.thaymendes.desafiosefaz.entidade;

import java.util.Date;

public class Lancamento {
	private int id;
	private String descricao;
	private String tipo;
	private Date vencimento;
	private boolean status;
	private double valor;

	public Lancamento() {

	}

	public Lancamento(int id, String descricao, String tipo, Date vencimento, boolean status, double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.vencimento = vencimento;
		this.status = status;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", vencimento=" + vencimento
				+ ", status=" + status + ", valor=" + valor + "]";
	}

}
