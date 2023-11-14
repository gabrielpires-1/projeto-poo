package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class VendedorDAO {
    DAOGenerico dao;

    public VendedorDAO() {
        this.dao = new DAOGenerico(Vendedor.class);
    }

    public boolean incluir(Vendedor vendedor) {
        Vendedor vendedorBusca = buscar(vendedor.getCpf()); 
        if (vendedorBusca != null) {
            return false;
        } else {
            dao.incluir(vendedor);
            return true;
        }      
    }

    public boolean alterar(Vendedor vendedor) {
        Vendedor vendedorBusca = buscar(vendedor.getCpf());
        if (vendedorBusca == null) {
            return false;
        } else {
            return dao.alterar(vendedor);
        }        
    }

    public boolean excluir(String cpf) {
        Vendedor vendedorBusca = buscar(cpf);
        if (vendedorBusca == null) {
            return false;
        } else {
            dao.excluir(cpf);
            return true;
        }        
    }

    public Vendedor buscar(String cpf) {
        return (Vendedor) dao.buscar(cpf);
    }

    public Vendedor[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        Vendedor[] vendedores = new Vendedor[registros.length];
        for(int i=0; i<registros.length; i++) {
            vendedores[i] = (Vendedor)registros[i];
        }
        return vendedores;
    } 
}
