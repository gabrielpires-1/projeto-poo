package br.gov.cesarschool.poo.bonusvendas.telas;

public class EntidadeMediator {
	public String incluir(Entidade ent) {
		String msg = validar(ent);
		if (msg == null) {
			// incluir no DAO
		}
		return msg;
	}
	public String alterar(Entidade ent) {
		String msg = validar(ent);
		if (msg == null) {
			// alterar no DAO
		}
		return msg;		
	}
	private String validar(Entidade ent) {
		if (ent.getNome() == null || ent.getNome().trim().equals("")) {
			return "Nome deve ser preenchido";
		} else {
			return null;
		}
	}
	public Entidade buscar(String codigo) {
		if (codigo == null || !codigo.equals("001")) {
			return null;
		} else {
			return new Entidade("001", "Carlos", 1000.00);
		}
	}
}
