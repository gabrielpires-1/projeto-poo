package br.gov.cesarschool.poo.bonusvendas.excecoes;

public class ExcecaoValidacao extends Exception {
  private java.util.List<ErroValidacao> errosValidacao = new java.util.ArrayList<ErroValidacao>();

  public ExcecaoValidacao(String mensagem) {
    super(mensagem);
  }

  public ExcecaoValidacao(java.util.List<ErroValidacao> errosValidacao) {
    if (errosValidacao != null) {
      this.errosValidacao = errosValidacao;
    }
  }

  public java.util.List<ErroValidacao> getErrosValidacao() {
    return errosValidacao;
  }

}
