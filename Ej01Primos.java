package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Ej01Primos {
  
  static int n;
  static List<Integer> listaPrimos = new ArrayList<>();
  
  public static int getN() {
    return n;
  }

  public static void setN(int n) {
    Ej01Primos.n = n;
  }

  public static List<Integer> getListaPrimos() {
    return listaPrimos;
  }

  public static void setListaPrimos(List<Integer> listaPrimos) {
          
    Ej01Primos.listaPrimos = listaPrimos;
  }

  static void comprobarPrimo() {
    
    int z = 1;
    int cont = 0;

    do {
      z++;
      if(esPrimo(z)){
        listaPrimos.add(z);
        cont++;
      }
    } while(cont < n);
  }

  private static boolean esPrimo(int x) {
 
    for(int i = 2; i < x; i++) {
      if((x%i)==0) {
        return false;
      }
    }
    return true;
  }
}
