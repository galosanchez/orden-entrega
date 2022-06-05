package com.galosanchez.mymethods;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Galo
 */
public class Fechas {
    public static String formatoDMA(Date fecha){
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        } catch (Exception e) {
            return "";
        }
    }
}
