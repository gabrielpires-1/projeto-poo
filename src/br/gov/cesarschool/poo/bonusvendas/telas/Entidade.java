package br.gov.cesarschool.poo.bonusvendas.telas;

public class Entidade {
	private String codigo;
	private String nome;
	private double renda;
	public Entidade(String codigo, String nome, double renda) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.renda = renda;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getRenda() {
		return renda;
	}
	public void setRenda(double renda) {
		this.renda = renda;
	}
	
}
