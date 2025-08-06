package ejercicio9;

import java.io.InputStream;
import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {
        final String contrasena= "isaias123";
        Scanner teclado= new Scanner(System.in);
        String intento;
        int intentos=0;
        boolean acertado= false;
        while(intentos<3 && !acertado){
            System.out.println("Ingrese la contrasenha: ");
            intento=teclado.nextLine();
            if(intento.equals(contrasena)){
                acertado=true;
            }else{
            intentos++;
            }
        }
        if(acertado){
            System.out.println("Correcto!!");
        }else{
            System.out.println("Fallaste jaja!!");
        }

    }

    
}
