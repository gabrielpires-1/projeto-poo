package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDate;     

public class LancamentoBonusDebito extends LancamentoBonus {
  private TipoResgate tipoResgate;

  public LancamentoBonusDebito(TipoResgate tipoResgate, long numeroCaixaDeBonus, double valor, LocalDate dataHoraLancamento) {
    super(numeroCaixaDeBonus, valor, dataHoraLancamento);
    this.tipoResgate = tipoResgate;
  }

  public TipoResgate getTipoResgate() {
    return tipoResgate;
  }
}
