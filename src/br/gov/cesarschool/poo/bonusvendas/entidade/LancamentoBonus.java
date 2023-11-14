package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class LancamentoBonus extends Registro {

    private long numeroCaixaDeBonus;
    private double valor;
    private LocalDate dataHoraLancamento;

    public LancamentoBonus(long numeroCaixaDeBonus, double valor, LocalDate dataHoraLancamento) {
        this.numeroCaixaDeBonus = numeroCaixaDeBonus;
        this.valor = valor;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public String getIdUnico(LancamentoBonus lancamento) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return lancamento.getNumeroCaixaDeBonus() + lancamento.getDataHoraLancamento().format(customFormatter);
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