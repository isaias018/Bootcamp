package ejercicio2array;

import java.util.Random;


public class Ejercicio2array {
    public static void main(String[] args) {
         int[] numeros = new int[100];
         int[] contador= new int[61];
         Random teclado= new Random();
         
         for(int i=0; i< numeros.length;i++){
             int valor= teclado.nextInt(61)-30;
             numeros[i]=valor;
             contador[valor+30]++;
                         
         }
         System.out.println("Array es");
         for(int num: numeros){
             System.out.println(num +"");
         }
         int cuantoSeRepite =0;
         int cuantosRepetidosHay= -30;
         
         for(int i=0; i< contador.length; i++){
             if(contador[i]>cuantoSeRepite ){
                 cuantoSeRepite=contador[i];
                 cuantosRepetidosHay=i-30;
             }
         }
        System.out.println("EL numero que mas se repite "+cuantosRepetidosHay);
        System.out.println("y se repite "+ cuantoSeRepite + " a la vez");
        
        System.out.println("Los numeros que no aparecen son: ");
        boolean faltan= false;
        for(int i=0; i<contador.length; i++){
            if(contador[i]== 0){
                System.out.println((i-30)+"");
                faltan=true;
            }
        }
        if(!faltan){
            System.out.println("Todos los numeros de -30 al 30 estan");
        }
    }
    
    
}
