package poker1.pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Poker11 {

    static String[] valores = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
    static String[] palos = {"S","C","H","D"};
    static Random random = new Random();

    public static void main(String[] args) {
        int totalManos = 1_000_000;
        int escaleraColor = 0, poker = 0, full = 0, escalera = 0, trio = 0, doblePar = 0, par = 0, nada = 0;

        for (int i = 0; i < totalManos; i++) {
            List<Carta> mano = generarMano();
            if (esEscaleraColor(mano)) escaleraColor++;
            else if (esPoker(mano)) poker++;
            else if (esFull(mano)) full++;
            else if (esEscalera(mano)) escalera++;
            else if (esTrio(mano)) trio++;
            else if (esDoblePar(mano)) doblePar++;
            else if (esPar(mano)) par++;
            else nada++;
        }

        System.out.println("Probabilidades aproximadas:");
        System.out.printf("Escalera de Color: %.6f%%%n", (escaleraColor * 100.0 / totalManos));
        System.out.printf("Poker: %.6f%%%n", (poker * 100.0 / totalManos));
        System.out.printf("Full House: %.6f%%%n", (full * 100.0 / totalManos));
        System.out.printf("Escalera: %.6f%%%n", (escalera * 100.0 / totalManos));
        System.out.printf("Trio: %.6f%%%n", (trio * 100.0 / totalManos));
        System.out.printf("Doble Par: %.6f%%%n", (doblePar * 100.0 / totalManos));
        System.out.printf("Par: %.6f%%%n", (par * 100.0 / totalManos));
        System.out.printf("Nada: %.6f%%%n", (nada * 100.0 / totalManos));
    }

    static List<Carta> generarMano() {
        List<Carta> baraja = new ArrayList<>();
        for (String v : valores) {
            for (String p : palos) {
                baraja.add(new Carta(v, p));
            }
        }
        Collections.shuffle(baraja);
        return baraja.subList(0, 5);
    }

    static boolean esEscaleraColor(List<Carta> mano) {
        return esColor(mano) && esEscalera(mano);
    }

    static boolean esColor(List<Carta> mano) {
        String palo = mano.get(0).palo;
        for (Carta c : mano) {
            if (!c.palo.equals(palo)) return false;
        }
        return true;
    }

    static boolean esEscalera(List<Carta> mano) {
        int[] nums;
        nums = mano.stream().mapToInt(c -> valorNumerico(c.valor)).sorted().toArray();     
        if (Arrays.equals(nums, new int[]{2,3,4,5,14})) return true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i+1] - nums[i] != 1) return false;
        }
        return true;
    }

    static boolean esPoker(List<Carta> mano) {
        Map<Integer, Long> conteo = contarValores(mano);
        return conteo.containsValue(4L);
    }

    static boolean esFull(List<Carta> mano) {
        Map<Integer, Long> conteo = contarValores(mano);
        return conteo.containsValue(3L) && conteo.containsValue(2L);
    }

    static boolean esTrio(List<Carta> mano) {
        Map<Integer, Long> conteo = contarValores(mano);
        return conteo.containsValue(3L) && conteo.size() == 3;
    }

    static boolean esDoblePar(List<Carta> mano) {
        Map<Integer, Long> conteo = contarValores(mano);
        return conteo.size() == 3 && Collections.frequency(conteo.values(), 2L) == 2;
    }

    static boolean esPar(List<Carta> mano) {
        Map<Integer, Long> conteo = contarValores(mano);
        return conteo.size() == 4;
    }

    static Map<Integer, Long> contarValores(List<Carta> mano) {
        Map<Integer, Long> conteo = new HashMap<>();
        for (Carta c : mano) {
            int num = valorNumerico(c.valor);
            conteo.put(num, conteo.getOrDefault(num, 0L) + 1);
        }
        return conteo;
    }

    static int valorNumerico(String valor) {
        switch (valor) {
            case "A": return 14;
            case "K": return 13;
            case "Q": return 12;
            case "J": return 11;
            case "T": return 10;
            default: return Integer.parseInt(valor);
        }
    }
}

    
    

