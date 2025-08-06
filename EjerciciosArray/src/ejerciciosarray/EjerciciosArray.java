package ejerciciosarray;

import java.util.Random;

public class EjerciciosArray {
    public static void main(String[] args) {
        int []numero= new int[10];
        Random teclado= new Random();
        
        for(int i=0; i< numero.length; i++){
            numero[i]=teclado.nextInt(11)-5;
        }
        System.out.println("Array generado:");
        for(int i=0; i< numero.length; i++){
            System.out.println(numero[i]+ "");
        }
            System.out.println("");
            int mayor= numero[0];
            for(int i=0; i< numero.length; i++){
                if(numero[i]> mayor){
                mayor=numero[i];
                }
            }
            System.out.println("El numero mayor es: "+ mayor);
    }
    
}
