package br.gov.cesarschool.poo.bonusvendas.daov2;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;

public class DAOGenerico {
    private String nomeEntidade; 
    CadastroObjetos cadastro;
    public DAOGenerico(Class<?> tipo, String nomeEntidade) {
        cadastro = new CadastroObjetos(tipo);        
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(Registro reg) throws ExcecaoObjetoJaExistente {
        String idUnico = reg.getIdUnico();
        try{
            buscar(idUnico);
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
        } catch (ExcecaoObjetoNaoExistente e) {
            cadastro.incluir(reg, idUnico);
        }
    }

    public void alterar(Registro reg) throws ExcecaoObjetoNaoExistente {
        String idUnico = reg.getIdUnico();
        buscar(idUnico);
        cadastro.alterar(reg, idUnico);
    }

    public boolean excluir(String id) throws ExcecaoObjetoNaoExistente {
        try {
            Registro regBusca = buscar(id);
            if (regBusca == null) { 
                return false;
            } else {
                cadastro.excluir(id);
                return true;
            }
        } catch (ExcecaoObjetoNaoExistente e) {
            return false;
        }
    }

    public Registro buscar(String id) throws ExcecaoObjetoNaoExistente {
        Registro registroBusca = (Registro) cadastro.buscar(id);
        if(registroBusca == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        } else {
            return registroBusca;
        }
    }

    public Registro[] buscarTodos() {
        Serializable[] regs = cadastro.buscarTodos(Registro.class);
        Registro[] regsRet = new Registro[regs.length];
        for (int i = 0; i < regs.length; i++) {
            regsRet[i] = (Registro) regs[i];
        }
        return regsRet;
    }
}
