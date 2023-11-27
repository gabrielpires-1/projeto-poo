package br.gov.cesarschool.poo.bonusvendas.excecoes;

public class ExcecaoObjetoNaoExistente extends Exception {

  public ExcecaoObjetoNaoExistente() {
    super("Objeto não existente");
  }

  public ExcecaoObjetoNaoExistente(String mensagem) {
    super(mensagem);
  }
}
