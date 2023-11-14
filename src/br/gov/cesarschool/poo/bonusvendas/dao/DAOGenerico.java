package br.gov.cesarschool.poo.bonusvendas.dao;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class DAOGenerico {
    CadastroObjetos cadastro;
    public DAOGenerico(Class<?> tipo) {
        cadastro = new CadastroObjetos(tipo);        
    }

    public boolean incluir(Registro reg){
        String idUnico = reg.getIdUnico();
        Registro regBusca = buscar(idUnico);
        if (regBusca != null) { 
            return false;
        } else {
            cadastro.incluir(reg, idUnico);
            return true;
        }
    }

    public boolean alterar(Registro reg){
        String idUnico = reg.getIdUnico();
        Registro regBusca = buscar(idUnico);
        if (regBusca == null) { 
            return false;
        } else {
            cadastro.alterar(reg, idUnico);
            return true;
        }
    }

    public boolean excluir(String id){
        Registro regBusca = buscar(id);
        if (regBusca == null) { 
            return false;
        } else {
            cadastro.excluir(id);
            return true;
        }
    }

    public Registro buscar(String id){
        return (Registro) cadastro.buscar(id);
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
