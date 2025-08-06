package ejercico4array;

import java.util.Scanner;

public class Ejercico4Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una cadena de dígitos: ");
        String entrada = scanner.nextLine();

        int[] numeros = new int[entrada.length()];

        for (int i = 0; i < entrada.length(); i++) {
            numeros[i] = Character.getNumericValue(entrada.charAt(i));
        }

        // Mostrar el array
        System.out.print("Array de números: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }

    }
    
}
