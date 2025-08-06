package ejercicio6;

import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
          double Iva= 0.10;
          System.out.println("Ingrese el precio del producto");
          Scanner teclado= new Scanner(System.in);
          double precioSinIva=teclado.nextDouble();
          double precioConIva= precioSinIva +(precioSinIva * Iva);
          System.out.println("El precio final es: "+precioConIva);
          teclado.close();
                   
          
    }
    
}
