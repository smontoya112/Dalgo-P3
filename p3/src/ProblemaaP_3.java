// Autores: Antonio Muñoz y Samuel Montoya

import java.util.*;

public class ProblemaaP_3 {

    // Optimizado a O(k) usando comparación directa
    public static int overlap(String s1, String s2) {
        int max = 0;
        int maxLen = Math.min(s1.length(), s2.length());
        for (int len = 1; len <= maxLen; len++) {
            boolean match = true;
            for (int i = 0; i < len; i++) {
                if (s1.charAt(s1.length() - len + i) != s2.charAt(i)) {
                    match = false;
                    break;
                }
            }
            if (match) max = len;
        }
        return max;
    }

    public static String[] mezclarCadenas(String[] original) {
        List<String> lista = new ArrayList<>(Arrays.asList(original.clone()));
        Collections.shuffle(lista, new Random());
        return lista.toArray(new String[0]);
    }

    public static String greedySuperstring(String[] cadenas) {
        int n = cadenas.length;
        boolean[] visitados = new boolean[n];
        Set<String> yaIncluidas = new HashSet<>();

        String superstring = cadenas[0];
        visitados[0] = true;
        yaIncluidas.add(cadenas[0]);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visitados[j] && superstring.contains(cadenas[j])) {
                    visitados[j] = true;
                    yaIncluidas.add(cadenas[j]);
                }
            }

            int siguiente = -1;
            int maxSolapamiento = -1;

            for (int j = 0; j < n; j++) {
                if (!visitados[j]) {
                    int solap = overlap(superstring, cadenas[j]);
                    if (solap > maxSolapamiento) {
                        maxSolapamiento = solap;
                        siguiente = j;
                    }
                }
            }

            if (siguiente != -1) {
                String sub = cadenas[siguiente];
                superstring += sub.substring(maxSolapamiento);
                visitados[siguiente] = true;
                yaIncluidas.add(sub);
            } else {
                break;
            }
        }

        return superstring;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCasos = Integer.parseInt(scanner.nextLine());

        for (int caso = 0; caso < numCasos; caso++) {
            String[] params = scanner.nextLine().split(" ");
            int n = Integer.parseInt(params[0]);
            int k = Integer.parseInt(params[1]);

            String[] cadenas = new String[n];
            for (int i = 0; i < n; i++) {
                cadenas[i] = scanner.nextLine();
            }

            String mejorResultado = null;
            int intentos = 1;

            for (int intento = 0; intento < intentos; intento++) {
                String[] cns = mezclarCadenas(cadenas);
                String resultado = greedySuperstring(cns);
                if (mejorResultado == null || resultado.length() < mejorResultado.length()) {
                    mejorResultado = resultado;
                }
            }

            System.out.println(mejorResultado);
        }

        scanner.close();
    }
}
