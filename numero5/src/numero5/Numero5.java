package numero5;

import java.util.Scanner;

public class Numero5 {

    public static void main(String[] args) {
        int nume1, nume2, division;
        System.out.println("Ingrese el numero");
        Scanner teclado = new Scanner(System.in);
        nume1 = teclado.nextInt();
        if (nume1 % 2 == 0) {
            System.out.println("El numero es divisble entre 2");
        } else {
            System.out.println("El numero no es divisible entre 2");
        }

    }

}
