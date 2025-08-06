package ejercicio10.pkg1;

import java.util.Scanner;

public class Ejercicio101 {
    public static void main(String[] args) {
        Scanner teclado= new Scanner(System.in);

        System.out.print("Ingrese un día de la semana: ");
        String dia = teclado.nextLine().toLowerCase(); 

        switch (dia) {
            case "lunes":
            case "martes":
            case "miércoles":
            case "miercoles": 
            case "jueves":
            case "viernes":
                System.out.println("Es un día laboral.");
                break;
            case "sábado":
            case "sabado": 
            case "domingo":
                System.out.println("No es un día laboral.");
                break;
            default:
                System.out.println("Día no válido.");
        }

        teclado.close();
    }
    
}

    }
    
}
