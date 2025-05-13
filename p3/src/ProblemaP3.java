import java.util.Scanner;

public class ProblemaP3 {
    public String scs(String[] cadenas){
        
        return null;
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
        }
        scanner.close();
    }
}
