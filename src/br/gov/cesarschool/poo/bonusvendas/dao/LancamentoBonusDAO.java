package br.gov.cesarschool.poo.bonusvendas.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class LancamentoBonusDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(LancamentoBonus.class); 

    public boolean incluir(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento()); 
        if (lancamentoBusca != null) { 
            return false;
        } else {
            cadastro.incluir(lancamento, generateUniqueId(lancamento));
            return true;
        }         
    }

    public boolean alterar(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento());
        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.alterar(lancamento, generateUniqueId(lancamento));
            return true;
        }        
    }

    public boolean excluir(long numeroCaixaDeBonus, LocalDate dataHoraLancamento) {
        LancamentoBonus lancamentoBusca = buscar(numeroCaixaDeBonus, dataHoraLancamento);
        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.excluir(generateUniqueId(lancamentoBusca));
            return true;
        }        
    }

    public LancamentoBonus buscar(long numeroCaixaDeBonus, LocalDate dataHoraLancamento) {
        return (LancamentoBonus) cadastro.buscar(generateUniqueId(new LancamentoBonus(numeroCaixaDeBonus, 0, dataHoraLancamento)));
    }

    public LancamentoBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(LancamentoBonus.class);
        LancamentoBonus[] lancamentos = new LancamentoBonus[rets.length];
        for(int i=0; i<rets.length; i++) {
            lancamentos[i] = (LancamentoBonus)rets[i];
        }
        return lancamentos;
    }

    private String generateUniqueId(LancamentoBonus lancamento) {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return lancamento.getNumeroCaixaDeBonus() + lancamento.getDataHoraLancamento().format(customFormatter);
    }
}
