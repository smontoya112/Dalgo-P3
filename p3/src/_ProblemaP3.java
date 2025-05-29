//Antonio Muñoz y Samuel Montoya
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public class _ProblemaP3 {
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
            int[][]     grafo = new int[n][n];
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


            // iplementacion aproximaada de camino hamiltoniano, con aproximacion greedy
            String[] stringsBusqueda = new String[grafo[0].length];
            for (int i = 0; i<grafo[0].length; i++){
                //implementar como se visitan los nodos
                int[] visitados = new int[cadenas.length];
                //buscar el maximo sobrelapamiento entre los indices.
                int piv = 0;
                int m =0;
                for(int j=0;j<grafo[i].length;j++){
                    //un numero aleatorio que sea diferente a j
                    if (piv == j && m==0 && j>0 && j<grafo[0].length){
                        piv = new Random().nextInt(j,grafo[0].length);
                    }
                    //caso que j es 0
                    else if(){

                    }
                    //caso que j es el extremo
                    else if(){

                    }
                    if(grafo[i][j]>m){
                        piv = j;
                        m = grafo[i][j];
                    }
                }
                visitados[i] = piv;
                StringBuilder stringFinalIteracion = new StringBuilder();
                for (int l=0 ; l<visitados.length;l++){
                    int peso = grafo[i][l];
                    stringFinalIteracion.append( cadenas[l].subSequence(0,k-peso)); 
                }
                stringsBusqueda[i] = stringFinalIteracion.toString();
            }
            //encontrar el minimo string
            System.out.println();
        }
        scanner.close();
    }
}
