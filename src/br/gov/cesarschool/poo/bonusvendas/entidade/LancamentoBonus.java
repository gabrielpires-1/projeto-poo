package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDate;
import java.io.Serializable;

public class LancamentoBonus implements Serializable {

    private long numeroCaixaDeBonus;
    private double valor;
    private LocalDate dataHoraLancamento;

    public LancamentoBonus(long numeroCaixaDeBonus, double valor, LocalDate dataHoraLancamento) {
        this.numeroCaixaDeBonus = numeroCaixaDeBonus;
        this.valor = valor;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    // public LancamentoBonus(long numCaixaDeBonus, double valor2, LocalDate now) {
    // }

    public long getNumeroCaixaDeBonus() {
        return numeroCaixaDeBonus;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataHoraLancamento() {
        return dataHoraLancamento;
    }
}