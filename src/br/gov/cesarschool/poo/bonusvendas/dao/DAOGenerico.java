package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class DAOGenerico {
    CadastroObjetos cadastro;
    public DAOGenerico(Class<?> tipo) {
        cadastro = new CadastroObjetos(tipo);        
    }

    public boolean incluir(Registro reg){
        return cadastro.incluir(reg.getIdUnico(), reg);
    }

    public boolean alterar(Registro reg){
        return cadastro.alterar(reg.getIdUnico(), reg);
    }

    public Registro buscar(String id){
        return cadastro.buscar(id);
    }

    public Registro[] buscarTodos() {
        return cadastro.buscarTodos();
    }
}
