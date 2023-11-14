package br.gov.cesarschool.poo.bonusvendas.util;

import java.util.Comparator;

public class Ordenadora {
    private Ordenadora() {};

  public static <T> void ordenar(T[] lista, Comparador<T> comp) {
    for (int i = 0; i < lista.length; i++) {
      for (int j = 0; j < lista.length - 1; j++) {
        if (comp.comparar(lista[j], lista[j + 1]) > 0) {
          T temp = lista[j];
          lista[j] = lista[j + 1];
          lista[j + 1] = temp;
        }
      }
    }
  }

  public static <T> void ordenar(T[] lista, Comparator<T> comp) {
    for (int i = 0; i < lista.length; i++) {
      for (int j = 0; j < lista.length - 1; j++) {
        if (comp.compare(lista[j], lista[j + 1]) > 0) {
          T temp = lista[j];
          lista[j] = lista[j + 1];
          lista[j + 1] = temp;
        }
      }
    }
  }
}

