package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.daov2.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.daov2.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorCaixaDeBonusSaldoDec;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorLancamentoBonusDHDec;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;

public class AcumuloResgateMediator {
  private static AcumuloResgateMediator instance;

  public static AcumuloResgateMediator getInstancia() {
    if (instance == null) {
      instance = new AcumuloResgateMediator();
    }
    return instance;
  }

  private CaixaDeBonusDAO repositorioCaixaBonus;
  private LancamentoBonusDAO repositorioLancamento;

  private AcumuloResgateMediator() {
    this.repositorioCaixaBonus = new CaixaDeBonusDAO();
    this.repositorioLancamento = new LancamentoBonusDAO();
  }

  public long gerarCaixaDeBonus(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
    String cpf = vendedor.getCpf();
    LocalDate dataAtual = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    cpf = cpf.replaceAll("[^0-9]", "");

    cpf = cpf.substring(0, cpf.length() - 2);

    long cpfNumerico = Long.parseLong(cpf);

    String numCaixaDeBonusStr = String.valueOf(cpfNumerico) + dataAtual.format(formatter);

    long numCaixaDeBonus = Long.parseLong(numCaixaDeBonusStr);

    CaixaDeBonus caixa = new CaixaDeBonus(numCaixaDeBonus);

    try {
      repositorioCaixaBonus.incluir(caixa);
    } catch (ExcecaoObjetoJaExistente e) {
      throw new ExcecaoObjetoJaExistente("Caixa ja existente");
    }

    return numCaixaDeBonus;
  }

  public void acumularBonus(long numCaixaDeBonus, double valor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao, ExcecaoObjetoJaExistente {
    if (valor <= 0) {
      throw new ExcecaoValidacao("Valor menor ou igual a zero"); 
    }
    try{
      CaixaDeBonus caixa = repositorioCaixaBonus.buscar(numCaixaDeBonus);
      caixa.creditar(valor);
      repositorioCaixaBonus.alterar(caixa);
      LancamentoBonusCredito lancamento = new LancamentoBonusCredito(numCaixaDeBonus, valor, LocalDateTime.now());
      try{
        repositorioLancamento.incluir(lancamento);
      } catch (ExcecaoObjetoJaExistente e) {
        throw new ExcecaoObjetoJaExistente("Inconsistencia no cadastro de lancamento");
      }
    } catch (ExcecaoObjetoNaoExistente e) {
      throw new ExcecaoObjetoNaoExistente("Caixa nao existente");
    }
  }
  

  public void resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipoResgate) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao, ExcecaoObjetoJaExistente {
    if (valor <= 0) {
      throw new ExcecaoValidacao("Valor menor ou igual a zero");
    }

    try{
      CaixaDeBonus caixa = repositorioCaixaBonus.buscar(numeroCaixaDeBonus);
      if (caixa.getSaldo() >= valor) {
        caixa.debitar(valor);
        repositorioCaixaBonus.alterar(caixa);
        LancamentoBonusDebito lancamento = new LancamentoBonusDebito(tipoResgate, numeroCaixaDeBonus, valor, LocalDateTime.now());
        repositorioLancamento.incluir(lancamento);
      } else {
        throw new ExcecaoValidacao("Saldo insuficiente");
      }
    } catch (ExcecaoObjetoNaoExistente e) {
      throw new ExcecaoObjetoNaoExistente("Caixa nao existente");
    }
  }

  public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) {
    CaixaDeBonus[] caixas = repositorioCaixaBonus.buscarTodos();
    
    List<CaixaDeBonus> filteredCaixas = new ArrayList<>();

    for (CaixaDeBonus caixa : caixas) {
        if (caixa.getSaldo() >= saldoInicial) {
            filteredCaixas.add(caixa);
        }
    }

    CaixaDeBonus[] result = filteredCaixas.toArray(new CaixaDeBonus[0]);

    Ordenadora.ordenar(result, ComparadorCaixaDeBonusSaldoDec.getInstance());

    return result;
}

  public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) {
    LancamentoBonus[] todosLancamentos = repositorioLancamento.buscarTodos();
    LancamentoBonus[] lancamentosFiltradosTemp = new LancamentoBonus[todosLancamentos.length];

    int count = 0;
    for (LancamentoBonus lancamento : todosLancamentos) {
        if (!lancamento.getDataHoraLancamento().toLocalDate().isBefore(d1) &&
            !lancamento.getDataHoraLancamento().toLocalDate().isAfter(d2)) {
            lancamentosFiltradosTemp[count++] = lancamento;
        }
    }
    LancamentoBonus[] lancamentosFiltrados = new LancamentoBonus[count];
    System.arraycopy(lancamentosFiltradosTemp, 0, lancamentosFiltrados, 0, count);
    Collections.sort(Arrays.asList(lancamentosFiltrados), ComparadorLancamentoBonusDHDec.getInstance());

    return lancamentosFiltrados;
}
}
