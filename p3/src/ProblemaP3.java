import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ProblemaP3 {
    public static int overlap(String cadena1, String cadena2) {
        int distancia = 0;
        LinkedList<String> cadena1Sub = new LinkedList<>();
        LinkedList<String> cadena2Sub = new LinkedList<>();
        
        if(cadena1.contains(cadena2)) {
            return cadena2.length();
        }
        else if(cadena2.contains(cadena1)) {
            return cadena1.length();
        }
        else {
            for (int a = 0; a < Math.min(cadena1.length(), cadena2.length()); a++) {
                cadena1Sub.addFirst(String.valueOf(cadena1.charAt(cadena1.length() - 1 - a)));
                cadena2Sub.add(String.valueOf(cadena2.charAt(a)));
                
                if (cadena1Sub.equals(cadena2Sub)) {
                    distancia = a + 1; 
                }
            }
        }
        return distancia;    
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int numCasos = scanner.nextInt();
        
        for (int caso = 0; caso < numCasos; caso++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            scanner.nextLine();

            String[] cadenas = new String[n];      
            for (int j = 0; j < n; j++) {
                String cadena = scanner.nextLine();
                if(cadena.length()==k){
                    cadenas[j] = cadena;
                }
                else{
                    System.err.println("Las palabras no son todas del mismo tamanio");
                }
            }
            int[][] grafo = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (j != l) {
                        grafo[j][l] = overlap(cadenas[j], cadenas[l]);
                    }
                }
            }

            // Imprimir el grafo de forma bonita
            System.out.println("Grafo de solapamientos:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(grafo[i][j]);
                    if (j < n - 1) System.out.print(" ");
                }
                System.out.println();
            }
        }
        scanner.close();
    }
}
