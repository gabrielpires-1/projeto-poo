package br.gov.cesarschool.poo.bonusvendas.excecoes;

public class ErroValidacao {
  int codigo;
  String mensagem;

  public ErroValidacao(int codigo, String mensagem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getMensagem() {
    return mensagem;
  }

}
