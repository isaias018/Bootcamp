package juegodenim;

import java.util.Scanner;

public class JuegodeNim {
    public static void main(String[] args) {
        Scanner teclado= new Scanner(System.in);
         System.out.print("Ingrese el nombre del Jugador 1: ");
         String jugador1 = teclado.nextLine();
         System.out.print("Ingrese el nombre del Jugador 2: ");
         String jugador2 = teclado.nextLine();

        int[] pilas = {10, 9, 15}; 
        int turno = 1; 

        while (true) {
            System.out.println("\nEstado actual de las pilas:");
            mostrarPilas(pilas);

            if (todasVacias(pilas)) {
             String perdedor = (turno == 1) ? jugador1 : jugador2;
            String ganador = (turno == 1) ? jugador2 : jugador1;
            System.out.println("¡" + perdedor + " quitó el último contador!");
            System.out.println(" El ganador es " + ganador );
                break;
            }

            System.out.println("Turno de " + (turno == 1 ? jugador1 : jugador2));
            int pilaIndex = -1;

            while (true) {
                System.out.print("Elija una pila (A, B o C): ");
                String input = teclado.nextLine().toUpperCase();

                if (input.equals("A")) pilaIndex = 0;
                else if (input.equals("B")) pilaIndex = 1;
                else if (input.equals("C")) pilaIndex = 2;
                else {
                    System.out.println("Pila inválida. Intente nuevamente.");
                    continue;
                    
                }

                if (pilas[pilaIndex] == 0) {
                    System.out.println("La pila está vacía. Elija otra.");
                } else {
                    break;
                }
            }

            int quitar = 0;
            while (true) {
                System.out.print("¿Cuántos contadores desea quitar de la pila? ");
                try {
                    quitar = Integer.parseInt(teclado.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Ingrese un número.");
                    continue;
                }

                if (quitar < 1 || quitar > pilas[pilaIndex]) {
                    System.out.println("Cantidad inválida. Debe ser entre 1 y " + pilas[pilaIndex]);
                } else {
                    break;
                }
            }

           
            pilas[pilaIndex] -= quitar;

            turno = (turno == 1) ? 2 : 1;
        }

        teclado.close();
    }

    public static void mostrarPilas(int[] pilas) {
        System.out.println("Pila A: " + pilas[0]);
        System.out.println("Pila B: " + pilas[1]);
        System.out.println("Pila C: " + pilas[2]);
    }

    public static boolean todasVacias(int[] pilas) {
        return pilas[0] == 0 && pilas[1] == 0 && pilas[2] == 0;
        
    
    }
}
    
