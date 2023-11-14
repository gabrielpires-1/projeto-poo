package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorLancamentoBonusDHDec implements Comparador{
  private static ComparadorLancamentoBonusDHDec instance = null;
  public static ComparadorLancamentoBonusDHDec getInstancia(){
    if(instance == null){
      instance = new ComparadorLancamentoBonusDHDec();
    }
    return instance;
  }

  public int comparar(Object o1, Object o2) {
    LancamentoBonus l1 = (LancamentoBonus) o1;
    LancamentoBonus l2 = (LancamentoBonus) o2;
    if (l1.getDataHoraLancamento().compareTo(l2.getDataHoraLancamento()) > 0) {
      return -1;
    } else if (l1.getDataHoraLancamento().compareTo(l2.getDataHoraLancamento()) < 0) {
      return 1;
    } else {
      return 0;
    }
  }
}
