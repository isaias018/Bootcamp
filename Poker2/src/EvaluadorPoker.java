import java.util.*;

public class EvaluadorPoker {

    private static final List<String> valores = Arrays.asList(
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    private static final List<String> palos = Arrays.asList(
            "Corazones", "Diamantes", "Tréboles", "Picas");

    public static String evaluarMano(Carta[] mano) {
        List<String> valoresMano = new ArrayList<>();
        List<String> palosMano = new ArrayList<>();

        for (Carta c : mano) {
            valoresMano.add(c.getValor());
            palosMano.add(c.getPalo());
        }

        Map<String, Integer> conteoValores = new HashMap<>();
        for (String v : valoresMano) {
            conteoValores.put(v, conteoValores.getOrDefault(v, 0) + 1);
        }

        boolean mismoPalo = palosMano.stream().distinct().count() == 1;
        List<Integer> indices = new ArrayList<>();
        for (String v : valoresMano) {
            indices.add(valores.indexOf(v));
        }
        Collections.sort(indices);

        boolean consecutivos = true;
        for (int i = 0; i < indices.size() - 1; i++) {
            if (indices.get(i) + 1 != indices.get(i + 1)) {
                consecutivos = false;
                break;
            }
        }

        if (mismoPalo && consecutivos && valoresMano.contains("A")) return "Escalera Real";
        if (mismoPalo && consecutivos) return "Escalera de Color";
        if (conteoValores.containsValue(4)) return "Póker";
        if (conteoValores.containsValue(3) && conteoValores.containsValue(2)) return "Full";
        if (mismoPalo) return "Color";
        if (consecutivos) return "Escalera";
        if (conteoValores.containsValue(3)) return "Trío";
        if (Collections.frequency(new ArrayList<>(conteoValores.values()), 2) == 2) return "Doble Pareja";
        if (conteoValores.containsValue(2)) return "Par";

        return "Carta Alta";
    }

    public static Carta[] repartirMano(List<Carta> mazo) {
        Carta[] mano = new Carta[5];
        for (int i = 0; i < 5; i++) {
            mano[i] = mazo.remove(0);
        }
        return mano;
    }

    public static List<Carta> crearMazo() {
        List<Carta> mazo = new ArrayList<>();
        for (String valor : valores) {
            for (String palo : palos) {
                mazo.add(new Carta(valor, palo));
            }
        }
        Collections.shuffle(mazo);
        return mazo;
    }
}

