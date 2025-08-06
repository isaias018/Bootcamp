
package numerosaleatorio;

import java.util.Scanner;
import java.util.Scanner;


public class NumerosAleatorio {

   
    public static void main(String[] args) {
        import *java.util.;

public class TorosYVacas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)
        
        String numeroSecreto = generarNumeroSecreto();

        System.out.println("Bienvenido al juego de Toros y Vacas.");
        System.out.println("Adivina el número de 4 dígitos (sin dígitos repetidos):");

        while (true) {
            System.out.print("Tu intento: ");
            String intento = scanner.nextLine();

            // Validación del intento
            if (!esValido(intento)) {
                System.out.println("Entrada inválida. Debe ser un número de 4 dígitos, sin dígitos repetidos.");
                continue;
            }

            int toros = contarToros(numeroSecreto, intento);
            int vacas = contarVacas(numeroSecreto, intento);

            if (toros == 4) {
                System.out.println("¡Felicidades! El número secreto era: " + numeroSecreto);
                break;
            } else {
                System.out.println(toros + " toros, " + vacas + " vacas.");
            }
        }

        scanner.close();
    }

    // Generar número secreto de 4 dígitos únicos
    public static String generarNumeroSecreto() {
        List<Integer> digitos = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            digitos.add(i);
        }

        Collections.shuffle(digitos);

        // El primer dígito no puede ser 0
        if (digitos.get(0) == 0) {
            for (int i = 1; i < digitos.size(); i++) {
                if (digitos.get(i) != 0) {
                    Collections.swap(digitos, 0, i);
                    break;
                }
            }
        }

        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            numero.append(digitos.get(i));
        }

        return numero.toString();
    }

    // Verifica si el intento es válido
    public static boolean esValido(String intento) {
        if (intento.length() != 4 || !intento.matches("\\d+")) {
            return false;
        }

        Set<Character> set = new HashSet<>();
        for (char c : intento.toCharArray()) {
            set.add(c);
        }

        return set.size() == 4 && intento.charAt(0) != '0';
    }

    // Contar toros (posición y número correcto)
    public static int contarToros(String secreto, String intento) {
        int toros = 0;
        for (int i = 0; i < 4; i++) {
            if (secreto.charAt(i) == intento.charAt(i)) {
                toros++;
            }
        }
        return toros;
    }

    // Contar vacas (número correcto en posición incorrecta)
    public static int contarVacas(String secreto, String intento) {
        int vacas = 0;
        for (int i = 0; i < 4; i++) {
            if (secreto.contains(String.valueOf(intento.charAt(i))) &&
                secreto.charAt(i) != intento.charAt(i)) {
                vacas++;
            }
        }
        return vacas;
    }
}

    }
    
}
