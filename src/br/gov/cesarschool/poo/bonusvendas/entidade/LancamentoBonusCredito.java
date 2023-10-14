package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDate;

public class LancamentoBonusCredito extends LancamentoBonus {
    public LancamentoBonusCredito(long numeroCaixaDeBonus, double valor, LocalDate dataHoraLancamento) {
        super(numeroCaixaDeBonus, valor, dataHoraLancamento);
    }
}

