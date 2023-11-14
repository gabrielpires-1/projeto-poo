package br.gov.cesarschool.poo.bonusvendas.util;

public class Ordenadora {
  private Ordenadora(){};

  public static void ordenar(Object[] lista, Comparador comp) {
    for (int i = 0; i < lista.length; i++) {
      for (int j = 0; j < lista.length - 1; j++) {
        if (comp.comparar(lista[j], lista[j + 1]) > 0) {
          Object temp = lista[j];
          lista[j] = lista[j + 1];
          lista[j + 1] = temp;
        }
      }
    }
  }
}
