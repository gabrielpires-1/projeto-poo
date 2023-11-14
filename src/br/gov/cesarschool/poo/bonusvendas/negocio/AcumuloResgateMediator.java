package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
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

  public long gerarCaixaDeBonus(Vendedor vendedor) {
    String cpf = vendedor.getCpf();
    LocalDate dataAtual = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    cpf = cpf.replaceAll("[^0-9]", "");

    cpf = cpf.substring(0, cpf.length() - 2);

    long cpfNumerico = Long.parseLong(cpf);

    String numCaixaDeBonusStr = String.valueOf(cpfNumerico) + dataAtual.format(formatter);

    long numCaixaDeBonus = Long.parseLong(numCaixaDeBonusStr);

    CaixaDeBonus caixa = new CaixaDeBonus(numCaixaDeBonus);

    if (repositorioCaixaBonus.buscar(numCaixaDeBonus) == null) {
      repositorioCaixaBonus.incluir(caixa);
      return numCaixaDeBonus;
    } else {
      return 0;
    }
  }

  public String acumularBonus(long numCaixaDeBonus, double valor) {
    if (valor <= 0) {
      return "Valor menor ou igual a zero";
    }
    CaixaDeBonus caixa = repositorioCaixaBonus.buscar(numCaixaDeBonus);
    if (caixa != null) {
      caixa.creditar(valor);
      repositorioCaixaBonus.alterar(caixa);
      LancamentoBonusCredito lancamento = new LancamentoBonusCredito(numCaixaDeBonus, valor, LocalDate.now());
      repositorioLancamento.incluir(lancamento);
      return null;
    } else {
      return "Caixa de bonus inexistente";
    }
  }

  public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipoResgate) {
    if (valor <= 0) {
      return "Valor menor ou igual a zero";
    }
    CaixaDeBonus caixa = repositorioCaixaBonus.buscar(numeroCaixaDeBonus);

    if (caixa != null) {
      if (caixa.getSaldo() >= valor) {
        caixa.debitar(valor);
        repositorioCaixaBonus.alterar(caixa);
        LancamentoBonusDebito lancamento = new LancamentoBonusDebito(tipoResgate, numeroCaixaDeBonus, valor, LocalDate.now());
        repositorioLancamento.incluir(lancamento);
        return null;
      } else {
        return "Saldo insuficiente";
      }
    } else {
      return "Caixa de bonus inexistente";
    }
  }

  public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) {
    CaixaDeBonus[] caixas = repositorioCaixaBonus.buscarTodos();
    for(int i = 0; i < caixas.length; i++){
      if(caixas[i].getSaldo() < saldoInicial){
        caixas[i] = null;
      }
    }
    Ordenadora.ordenar(caixas, ComparadorCaixaDeBonusSaldoDec.getInstancia());
    return caixas;
  }

  
  public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) {
    LancamentoBonus[] todosLancamentos = repositorioLancamento.buscarTodos();
    LancamentoBonus[] lancamentosFiltradosTemp = new LancamentoBonus[todosLancamentos.length];

    int count = 0;
    for (LancamentoBonus lancamento : todosLancamentos) {
      if (!lancamento.getDataHoraLancamento().isBefore(d1) && 
        !lancamento.getDataHoraLancamento().isAfter(d2)) {
        lancamentosFiltradosTemp[count++] = lancamento;
      }
    }

    LancamentoBonus[] lancamentosFiltrados = new LancamentoBonus[count];
    System.arraycopy(lancamentosFiltradosTemp, 0, lancamentosFiltrados, 0, count);

    Ordenadora.ordenar(lancamentosFiltrados, new ComparadorLancamentoBonusDHDec());

    return lancamentosFiltrados;
  }
}
