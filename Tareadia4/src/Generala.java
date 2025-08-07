
import java.util.Random;
import java.util.Scanner;

public class Generala {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Seleccione una jugada a buscar:");
        System.out.println("G - Generala");
        System.out.println("P - Poker");
        System.out.println("F - Full");
        System.out.println("E - Escalera");
        System.out.println("N - Nada");

        String opcion = scanner.nextLine().toUpperCase();
        String objetivo = "";

        switch (opcion) {
            case "G" -> objetivo = "GENERALA";
            case "P" -> objetivo = "POKER";
            case "F" -> objetivo = "FULL";
            case "E" -> objetivo = "ESCALERA";
            case "N" -> objetivo = "NADA";
            default -> {
                System.out.println("Opcion invalida.");
                return;
            }
        }

        int intentos = 0;
        while (true) {
            intentos++;
            String dados = "";
            for (int i = 0; i < 5; i++) {
                dados += (random.nextInt(6) + 1);
            }
            String resultado = jugada(dados);
            System.out.println("Tirada N" + intentos + ": " + dados + " => " + resultado);

            if (resultado.equals(objetivo)) {
                System.out.println("\n¡Se encontro la jugada " + objetivo + " en " + intentos + " intento!");
                break;
            }
        }

        
        System.out.println("\n--- Probabilidades estimadas con 100000 tiradas ---");
        calcularProbabilidades(100000);
    }

    public static String jugada(String dados) {
        if (dados.length() != 5) {
            return "INVALIDO";
        }

        int[] conteo = new int[7]; 
        for (char c : dados.toCharArray()) {
            if (c < '1' || c > '6') {
                return "INVALIDO";
            }
            int valor = c - '0';
            conteo[valor]++;
        }

        boolean escalera12345 = dados.contains("1") && dados.contains("2") && dados.contains("3")
                             && dados.contains("4") && dados.contains("5");
        boolean escalera23456 = dados.contains("2") && dados.contains("3") && dados.contains("4")
                             && dados.contains("5") && dados.contains("6");
        boolean escalera34561 = dados.contains("3") && dados.contains("4") && dados.contains("5")
                             && dados.contains("6") && dados.contains("1");

        for (int i = 1; i <= 6; i++) {
            if (conteo[i] == 5) return "GENERALA";
        }

        for (int i = 1; i <= 6; i++) {
            if (conteo[i] == 4) return "POKER";
        }

        boolean hay3 = false, hay2 = false;
        for (int i = 1; i <= 6; i++) {
            if (conteo[i] == 3) hay3 = true;
            if (conteo[i] == 2) hay2 = true;
        }
        if (hay3 && hay2) return "FULL";

        if (escalera12345 || escalera23456 || escalera34561) return "ESCALERA";

        return "NADA";
    }
 
    public static void calcularProbabilidades(int totalTiradas) {
        Random random = new Random();

        int generala = 0;
        int poker = 0;
        int full = 0;
        int escalera = 0;
        int nada = 0;

        for (int i = 0; i < totalTiradas; i++) {
            String dados = "";
            for (int j = 0; j < 5; j++) {
                dados += (random.nextInt(6) + 1);
            }

            String resultado = jugada(dados);
            switch (resultado) {
                case "GENERALA" -> generala++;
                case "POKER" -> poker++;
                case "FULL" -> full++;
                case "ESCALERA" -> escalera++;
                case "NADA" -> nada++;
            }
        }

        System.out.printf("GENERALA: %.4f%%\n", generala * 100.0 / totalTiradas);
        System.out.printf("POKER:    %.4f%%\n", poker * 100.0 / totalTiradas);
        System.out.printf("FULL:     %.4f%%\n", full * 100.0 / totalTiradas);
        System.out.printf("ESCALERA: %.4f%%\n", escalera * 100.0 / totalTiradas);
        System.out.printf("NADA:     %.4f%%\n", nada * 100.0 / totalTiradas);
        
    }
    
}


