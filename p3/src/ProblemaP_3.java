import java.util.LinkedList;
import java.util.Scanner;

public class ProblemaP_3 {
    
    // Clase interna para manejar el camino hamiltoniano
    private static class HamiltonianPath {
        private int[][] graph;
        private int numVertices;
        private int maxOverlap = -1;
        private int[] bestPath;
        private String[] words;

        public HamiltonianPath(int[][] graph, String[] words) {
            this.graph = graph;
            this.numVertices = graph.length;
            this.bestPath = new int[numVertices];
            this.words = words;
        }

        public void findMaxOverlapPath() {
            int[] path = new int[numVertices];
            boolean[] visited = new boolean[numVertices];
            findMaxOverlapPathRecursive(0, path, visited, 0, 0);
        }

        private void findMaxOverlapPathRecursive(int currentPosition, int[] path, 
                                               boolean[] visited, int currentOverlap, int depth) {
            if (depth == numVertices) {
                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                    System.arraycopy(path, 0, bestPath, 0, numVertices);
                }
                return;
            }

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    path[depth] = i;
                    
                    int newOverlap = currentOverlap;
                    if (depth > 0) {
                        int prevVertex = path[depth-1];
                        newOverlap += graph[prevVertex][i];
                    }
                    
                    findMaxOverlapPathRecursive(i, path, visited, newOverlap, depth + 1);
                    visited[i] = false;
                }
            }
        }

        public int getMaxOverlap() {
            return maxOverlap;
        }

        public int[] getBestPath() {
            return bestPath;
        }
        
        public String getMergedString() {
            if (bestPath == null || bestPath.length == 0) return "";
            
            StringBuilder result = new StringBuilder(words[bestPath[0]]);
            
            for (int i = 1; i < bestPath.length; i++) {
                int prev = bestPath[i-1];
                int current = bestPath[i];
                int overlap = graph[prev][current];
                String word = words[current];
                result.append(word.substring(overlap));
            }
            
            return result.toString();
        }
    }

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
            
            // Construir el grafo de solapamientos
            int[][] grafo = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (j != l) {
                        grafo[j][l] = overlap(cadenas[j], cadenas[l]);
                    }
                }
            }

            // Imprimir el grafo de solapamientos
            /*
            System.out.println("Grafo de solapamientos:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(grafo[i][j]);
                    if (j < n - 1) System.out.print(" ");
                }
                System.out.println();
            }
            */
            
            // Encontrar el camino hamiltoniano con máximo solapamiento
            HamiltonianPath hamiltonian = new HamiltonianPath(grafo, cadenas);
            hamiltonian.findMaxOverlapPath();
            
            System.out.println("Máximo solapamiento total: " + hamiltonian.getMaxOverlap());
            System.out.print("Orden óptimo: ");
            for (int vertex : hamiltonian.getBestPath()) {
                System.out.print(cadenas[vertex] + " ");
            }
            System.out.println();
            System.out.println("Cadena resultante: " + hamiltonian.getMergedString());
        }
        scanner.close();
    }
}