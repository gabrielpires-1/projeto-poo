package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class VendedorDAO {
    private DAOGenericoTp<Vendedor> dao;

    public VendedorDAO() {
        this.dao = new DAOGenericoTp<>(Vendedor.class, "Vendedor");
    }

    public void incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
        this.dao.incluir(vendedor);
    }

    public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente {
        this.dao.alterar(vendedor);
    }

    public void excluir(String cpf) throws ExcecaoObjetoNaoExistente {
        this.dao.excluir(cpf);
    }

    public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
        return this.dao.buscar(cpf);
    }

    public Vendedor[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        Vendedor[] vendedores = new Vendedor[registros.length];
        for (int i = 0; i < registros.length; i++) {
            vendedores[i] = (Vendedor) registros[i];
        }
        return vendedores;
    }

}

