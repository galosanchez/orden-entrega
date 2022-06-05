package com.galosanchez.mymethods;

/**
 * @author Galo Sánchez
 */
public class ImprimirCadena {
    public static void titulo(String cadena) {
        int k, n, e; //k contador ; e espacios
        n = cadena.length() + 8; //cantidad de letras
        e = (50 - n) / 2; //espacios en blanco
        System.out.println();
        for (k = 1; k <= e; k++) {   // 
            System.out.print(" ");
        }
        for (k = 1; k <= n; k++) {
            System.out.print("-");
        }
        System.out.println();
        for (k = 1; k <= e; k++) {
            System.out.print(" ");
        }
        System.out.println("|   " + cadena + "   |");
        for (k = 1; k <= e; k++) {
            System.out.print(" ");
        }
        for (k = 1; k <= n; k++) {
            System.out.print("-");
        }
        System.out.print("\n\n");
    }
    
    public static void subTitulo(String cadena) {
        int k, n, e; //k contador ; e espacios
        n = cadena.length() + 8; //cantidad de letras
        e = (50 - n) / 2; //espacios en blanco
        System.out.println();
        for (k = 1; k <= e; k++) {   // 
            System.out.print(" ");
        }
        System.out.println("|| " + cadena + " ||");
    }
    
    public static void itemTitulo(String cadena) {
        int k, n, e; //k contador ; e espacios
        n = cadena.length() + 8; //cantidad de letras
        e = (60 - n) / 2; //espacios en blanco
        System.out.println();
        for (k = 1; k <= e; k++) {   // 
            System.out.print(" ");
        }
        System.out.println("---- " + cadena + " ----");
    }
    
    public static void itemTituloCliente(String cadena) {
        int k, n, e; //k contador ; e espacios
        n = cadena.length() + 8; //cantidad de letras
        e = (80 - n) / 2; //espacios en blanco
        System.out.println();
        for (k = 1; k <= e; k++) {   // 
            System.out.print(" ");
        }
        System.out.println("---- " + cadena + " ----");
    }
    
    public static void tituloOrdenEntrega(String cadena) {
        int k, n, e; //k contador ; e espacios
        n = cadena.length() + 8; //cantidad de letras
        e = (80 - n) / 2; //espacios en blanco
        System.out.println();
        for (k = 1; k <= e; k++) {   // 
            System.out.print(" ");
        }
        System.out.println("------ " + cadena + " ------");
    }
    
    public static void itemsMenu(String... items){
        for (String item : items) {
            itemMenu(item);
        }
    }
    
    public static void itemMenu(String cadena) {
        System.out.printf("%10s%s\n",
                "", cadena);
    }
    
    public static void pedirOpcion() {
        System.out.printf("%10s%s",
                "", "Opción ==> ");
    }
    
    public static void pedirOpcion(String cadena) {
        System.out.printf("%10s%s",
                "", cadena+" ==> ");
    }
    
    public static void errorOpcion() {
        System.out.printf("%8s%s\n",
                "", "** Opción no reconocida **");
    }
    
    public static void bye() {
        System.out.printf("%8s%s\n",
                "", "Adios !!");
    }
    
    public static void atras() {
        System.out.printf("%10s%s\n",
                "", "<<<");
    }
    
    public static void pedirDato(String cadena) {
        System.out.printf("%10s%s",
                "", cadena+": ");
    }
    
    public static void errorDato(String cadena) {
        System.out.printf("%8s%s\n",
                "", "** "+cadena+" **");
    }

    public static void aviso(String cadena) {
        System.out.printf("%8s%s\n",
                "", "¡ "+cadena+" !");
    }
    
    public static void completado(String cadena) {
        System.out.printf("%8s%s\n",
                "", cadena+" !!");
    }
    
}
