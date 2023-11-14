package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class LancamentoBonusDAO {
    private DAOGenerico dao;

    public LancamentoBonusDAO() {
        this.dao = new DAOGenerico(LancamentoBonus.class);
    }

    public boolean incluir(LancamentoBonus lancamento) {
<<<<<<< HEAD
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento()); 
        if (lancamentoBusca != null) { 
            return false;
        } else {
            cadastro.incluir(lancamento, generateUniqueId(lancamento));
            return true;
        }
    }
=======
        return dao.incluir(lancamento);
        }         
>>>>>>> dc9dc4d5ba3da17dcf456ebbd9b19b2f6aec2665

    public boolean alterar(LancamentoBonus lancamento) {
        return dao.alterar(lancamento);      
    }

    public boolean excluir(LancamentoBonus lancamento) {
        return dao.excluir(lancamento.getIdUnico());       
    }

    public LancamentoBonus buscar(String codigo) {
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
