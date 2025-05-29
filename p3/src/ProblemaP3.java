// Antonio Munos Samuel Montoya 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProblemaP3 {

    public static int overlap(String s1, String s2) {
        int max = 0;
        int maxLen = Math.min(s1.length(), s2.length());
        
        for (int len = maxLen; len > 0; len--) {
            boolean match = true;
            for (int i = 0; i < len; i++) {
                if (s1.charAt(s1.length() - len + i) != s2.charAt(i)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return len; 
            }
        }
        return max;
    }

    public static String[] mezclarCadenas(String[] original) {
        List<String> lista = new ArrayList<>(Arrays.asList(original));
        Collections.shuffle(lista);
        return lista.toArray(new String[0]);
    }

    public static String greedySuperstring(String[] cadenas, int[][] grafo, int inicio) {
        int n = cadenas.length;
        boolean[] visitados = new boolean[n];
        StringBuilder superstring = new StringBuilder(cadenas[inicio]);
        visitados[inicio] = true;
        int actual = inicio;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitados[j] && superstring.toString().contains(cadenas[j])) {
                    visitados[j] = true;
                }
            }

            int siguiente = -1;
            int maxSolapamiento = -1;

            for (int j = 0; j < n; j++) {
                if (!visitados[j] && grafo[actual][j] > maxSolapamiento) {
                    maxSolapamiento = grafo[actual][j];
                    siguiente = j;
                }
            }

            if (siguiente != -1) {
                superstring.append(cadenas[siguiente].substring(maxSolapamiento));
                visitados[siguiente] = true;
                actual = siguiente;
            } else {
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (!visitados[j] && superstring.toString().contains(cadenas[j])) {
                visitados[j] = true;
            }
        }

        return superstring.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCasos = Integer.parseInt(scanner.nextLine());

        for (int caso = 0; caso < numCasos; caso++) {
            String[] params = scanner.nextLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int k = Integer.parseInt(params[1]);

            String[] cadenas = new String[n];
            boolean valid = true;
            
            for (int j = 0; j < n; j++) {
                cadenas[j] = scanner.nextLine();
                if (cadenas[j].length() != k) {
                    valid = false;
                }
            }

            if (!valid) {
                System.err.println("Las palabras no son todas del mismo tamaño");
                scanner.close();
                return;
            }

            String mejorResultado = null;
            int intentos = Math.min(n + 10, 100);

            List<String[]> mezclas = new ArrayList<>();
            for (int i = 0; i < intentos; i++) {
                mezclas.add(mezclarCadenas(cadenas));
            }

            for (String[] cns : mezclas) {
                int[][] grafo = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            grafo[i][j] = overlap(cns[i], cns[j]);
                        }
                    }
                }

                String resultado = greedySuperstring(cns, grafo, 0);
                if (mejorResultado == null || resultado.length() < mejorResultado.length()) {
                    mejorResultado = resultado;
                }
            }

            System.out.println(mejorResultado);
        }

        scanner.close();
    }
}