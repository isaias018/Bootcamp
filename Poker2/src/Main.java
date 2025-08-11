public class Main {
    public static void main(String[] args) {
        // Crear y barajar mazo
        var mazo = EvaluadorPoker.crearMazo();

        // Repartir manos
        Carta[] mano1 = EvaluadorPoker.repartirMano(mazo);
        Carta[] mano2 = EvaluadorPoker.repartirMano(mazo);

        // Evaluar manos
        String jugada1 = EvaluadorPoker.evaluarMano(mano1);
        String jugada2 = EvaluadorPoker.evaluarMano(mano2);

        // Mostrar resultados
        System.out.println("Mano 1:");
        for (Carta c : mano1) System.out.println(c);
        System.out.println("Jugada: " + jugada1);

        System.out.println("\nMano 2:");
        for (Carta c : mano2) System.out.println(c);
        System.out.println("Jugada: " + jugada2);

        // Determina ganador
        int resultado = compararJugadas(jugada1, jugada2);
        if (resultado > 0) {
            System.out.println("\nGana Mano 1");
        } else if (resultado < 0) {
            System.out.println("\nGana Mano 2");
        } else {
            System.out.println("\nEmpate");
        }
    }

    //compara la jerarquia
    public static int compararJugadas(String j1, String j2) {
        String[] ranking = {
            "Carta Alta", "Par", "Doble Pareja", "Trío", "Escalera",
            "Color", "Full", "Póker", "Escalera de Color", "Escalera Real"
        };

        int valor1 = java.util.Arrays.asList(ranking).indexOf(j1);
        int valor2 = java.util.Arrays.asList(ranking).indexOf(j2);

        return Integer.compare(valor1, valor2);
    }
}

