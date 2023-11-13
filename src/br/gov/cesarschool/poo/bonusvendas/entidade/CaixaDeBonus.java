package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class CaixaDeBonus extends Registro {
    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;

    public String getIdUnico(){
        return String.valueOf(this.numero);
    }

    public CaixaDeBonus(long numero) {
        this.dataHoraAtualizacao = LocalDateTime.now();
        this.numero = numero;
    }

    public CaixaDeBonus(Object numero2) {
    }

    public void creditar(double valor) {
        this.saldo = this.saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public void debitar(double valor) {
        this.saldo = this.saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public long getNumero() {
        this.dataHoraAtualizacao = LocalDateTime.now();
        return this.numero;
    }

    public void setNumero(long numero) {
        this.dataHoraAtualizacao = LocalDateTime.now();
        this.numero = numero;
    }

    public double getSaldo() {
        this.dataHoraAtualizacao = LocalDateTime.now();
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.dataHoraAtualizacao = LocalDateTime.now();
        this.saldo = saldo;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        this.dataHoraAtualizacao = LocalDateTime.now();
        return this.dataHoraAtualizacao;
    }
}