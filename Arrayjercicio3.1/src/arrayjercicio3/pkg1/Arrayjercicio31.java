package arrayjercicio3.pkg1;

import java.util.Scanner;

public class Arrayjercicio31 {
    public static void main(String[] args) {
        String reversa = "";
        System.out.println("Ingrese una palabra");
        Scanner teclado = new Scanner(System.in);
        
     
        // Leer entrada y limpiar caracteres no alfabéticos
        String palabra = teclado.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Invertir la cadena
        for (int i = palabra.length() - 1; i >= 0; i--) {
            reversa = reversa + palabra.charAt(i);
        }

        System.out.println(reversa);
        
        // Verificar si es palíndromo
        if (palabra.equals(reversa)) {
            System.out.println("Es un palíndromo.");
        } else {
            System.out.println("No es un palíndromo.");
        }
        teclado.close();
    }
    
}
