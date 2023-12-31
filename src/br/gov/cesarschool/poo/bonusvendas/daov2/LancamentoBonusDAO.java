package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;

public class LancamentoBonusDAO {
    private DAOGenericoTp<LancamentoBonus> dao;

    public LancamentoBonusDAO() {
        this.dao = new DAOGenericoTp<>(LancamentoBonus.class, "Lancamento");
    }

    public void incluir(LancamentoBonus lancamento) throws ExcecaoObjetoJaExistente {
        dao.incluir(lancamento);
    }

    public void alterar(LancamentoBonus lancamento) throws ExcecaoObjetoNaoExistente {
        dao.alterar(lancamento);
    }

    public void excluir(LancamentoBonus lancamento) throws ExcecaoObjetoNaoExistente {
        dao.excluir(lancamento.getIdUnico());       
    }

    public LancamentoBonus buscar(String codigo) throws ExcecaoObjetoNaoExistente {
        return (LancamentoBonus) dao.buscar(codigo);
    }

    public LancamentoBonus[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        LancamentoBonus[] lancamentos = new LancamentoBonus[registros.length];
        for(int i=0; i<registros.length; i++) {
            lancamentos[i] = (LancamentoBonus)registros[i];
        }
        return lancamentos;
    }
}
