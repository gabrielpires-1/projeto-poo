package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class ComparadorLancamentoBonusDHDec implements Comparator<LancamentoBonus>{
  private static ComparadorLancamentoBonusDHDec instance = null;
  
  public static ComparadorLancamentoBonusDHDec getInstance(){
    if(instance == null){
      instance = new ComparadorLancamentoBonusDHDec();
    }
    return instance;
  }

  @Override
  public int compare(LancamentoBonus arg0, LancamentoBonus arg1) {
    LancamentoBonus l1 = (LancamentoBonus) arg0;
    LancamentoBonus l2 = (LancamentoBonus) arg1;
    if (l1.getDataHoraLancamento().compareTo(l2.getDataHoraLancamento()) > 0) {
      return -1;
    } else if (l1.getDataHoraLancamento().compareTo(l2.getDataHoraLancamento()) < 0) {
      return 1;
    } else {
      return 0;
    }
  }

}
