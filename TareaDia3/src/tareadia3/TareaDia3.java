package tareadia3;

import java.util.Scanner;

public class TareaDia3 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
      
        System.out.print("Ingrese tiempo en segundos desde medianoche: ");
        int segundos = teclado.nextInt();

        Reloj reloj1 = new Reloj(segundos);
     
        System.out.println("Incrementando tiempo con tick():");
        for (int i = 0; i < 10; i++) {
            reloj1.tick();
            System.out.println(reloj1.toString());
        }
     
        Reloj reloj2 = new Reloj(1, 30, 15); 
       
        Reloj diferencia = reloj1.restaReloj(reloj2);
        System.out.println("Diferencia entre los dos Relojes:");
        System.out.println(diferencia.toString());

        teclado.close();
        

    }
    
}
