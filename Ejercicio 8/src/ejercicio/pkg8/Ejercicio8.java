package ejercicio.pkg8;

import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner teclado= new Scanner(System.in);
        int nume1;
        do{
        System.out.println("Ingrese el numero: ");
        nume1=teclado.nextInt();
        } while(nume1<=0);
        System.out.println("El numero ingresado es:"+nume1);
        teclado.close();

    }
    
}
