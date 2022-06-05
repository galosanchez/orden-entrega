package com.galosanchez.mymethods;

import java.util.Scanner;

/**
 * @author Galo Sánchez
 */
public class LeerDatos {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static int opcion(){
        int op;
        try{
             op = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            return 0;
        }
        return op;
    }
    
    public static String datoCadena(){
        String dato = null;
        
        try{
             dato = String.valueOf(scanner.nextLine());
        }catch(NumberFormatException e){
            return null;
        }
        if(dato.length()<1){
            return null;
        }
        return dato;
    }
    
    public static char datoChar(){
        char dato;
        try{
             dato = scanner.nextLine().charAt(0);
        }catch(Exception e){
            return '"';
        }
        return dato;
    }
    
    public static char datoOpcionSN(){
        char dato;
        try{
             dato = scanner.nextLine().charAt(0);
        }catch(Exception e){
            return '"';
        }
        if(dato!='S' && dato!='s' && dato!='n' && dato!='N'){
            return '"';
        }
        return dato;
    }
    
    public static String datoCadenaVacio(){
        String dato = null;
        
        try{
             dato = String.valueOf(scanner.nextLine());
        }catch(NumberFormatException e){
            return null;
        }
        
        return dato;
    }
    
    public static int datoIntMayorCero(){
        int dato = 0;
        
        try{
             dato = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            return 0;
        }
        if(dato<1){
            return 0;
        }
        return dato;
    }
    
    public static int datoIntPositivoVacio(){
        int dato;
        
        try{
             dato = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            if((e.getMessage()).equals("For input string: \"\"")){
                return -1;
            }
            return -2;
        }
        if(dato<0){
            return -2;
        }
        return dato;
    }
    
    public static float datoFloatMayorCero(){
        float dato = 0;
        
        try{
             dato = Float.parseFloat(scanner.nextLine());
        }catch(NumberFormatException e){
            return 0;
        }
        if(dato<=0){
            return 0;
        }
        return dato;
    }
    
    public static float datoFloatMayorCeroVacio(){
        float dato;
        
        try{
             dato = Float.parseFloat(scanner.nextLine());
        }catch(NumberFormatException e){
            if((e.getMessage()).equals("empty String")){
                return 0;
            }
            return -1;
        }catch(Exception e){
            return -1;
        }
        if(dato<=0){
            return -1;
        }
        return dato;
    }
}
