package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class CaixaDeBonusDAO {
    private DAOGenerico dao; 

    public CaixaDeBonusDAO() {
        this.dao = new DAOGenerico(CaixaDeBonus.class);
    }
     
    public boolean incluir(CaixaDeBonus caixa) {
        return dao.incluir(caixa); 
    }

    public boolean alterar(CaixaDeBonus caixa) {
        return dao.alterar(caixa);
    }

    public boolean excluir(long numero) {
        return dao.excluir(numero + "");
    }

    public CaixaDeBonus buscar(long numero) {
        return (CaixaDeBonus) dao.buscar(numero + "");
    }

    public CaixaDeBonus[] buscarTodos() {
        Registro[] rets = dao.buscarTodos();
        CaixaDeBonus[] caixas = new CaixaDeBonus[rets.length];
        for(int i=0; i<rets.length; i++) {
            caixas[i] = (CaixaDeBonus)rets[i];
        }
        return caixas;
    } 
}
