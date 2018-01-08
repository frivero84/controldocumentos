package helper;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author nando
 */
public class Util {

    public static Timestamp obtenerTimeStamp(Date fecha) throws Exception {
        try {

            return new Timestamp(fecha.getYear(), fecha.getMonth(), fecha.getDate(), 0, 0, 0, 0);
        } catch (Exception ex) {

            throw new Exception("Error en fecha nula:\n" + ex.getMessage());
        }
    }

    public static long diferenciaFechas(Date fechaC) {

        final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
        java.util.Date hoy = new Date(); //Fecha de hoy

        int año = fechaC.getYear();
        int mes = fechaC.getMonth();
        int dia = fechaC.getDate(); //Fecha anterior
        Calendar calendar = new GregorianCalendar(año, mes - 1, dia);
        java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());

        long diferencia = (fechaC.getTime()- hoy.getTime()  ) / MILLSECS_PER_DAY;
       
        return diferencia;

    }


     public static Date obtenerDate(String str_fecha) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        GregorianCalendar hoy = new GregorianCalendar();

        try {
            if (str_fecha.compareTo("") == 0) {
                fecha = hoy.getTime();
            } else {
                fecha = formatter.parse(str_fecha);
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }


        try {

            return fecha;
        } catch (Exception ex) {

            throw new Exception("Error en fecha nula:\n" + ex.getMessage());
        }
    }


    public static Timestamp obtenerTimeStamp(String str_fecha) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        GregorianCalendar hoy = new GregorianCalendar();

        try {
            if (str_fecha.compareTo("") == 0) {
                fecha = hoy.getTime();
            } else {
                fecha = formatter.parse(str_fecha);
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }


        try {

            return new Timestamp(fecha.getYear(), fecha.getMonth(), fecha.getDate(), 0, 0, 0, 0);
        } catch (Exception ex) {

            throw new Exception("Error en fecha nula:\n" + ex.getMessage());
        }
    }

    public static String obtenerHora() throws Exception {

        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int hora;
        int minutos;
        GregorianCalendar hoy = new GregorianCalendar();
        try {
            // fecha = formatter.parse(hoy.getTime().toString());
            hora = hoy.get(hoy.HOUR_OF_DAY);
            minutos = hoy.get(hoy.MINUTE);

            DecimalFormat twoDForm = new DecimalFormat("00");

            return String.valueOf(twoDForm.format(hora)).concat(":").concat(String.valueOf(twoDForm.format(minutos)));

        } catch (Exception ex) {

            throw new Exception("Error en Hora nula:\n" + ex.getMessage());
        }
    }

    public static String obtenerFecha() throws Exception {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatter.format(new Date());
        } catch (Exception ex) {
            throw new Exception("Error en Hora nula:\n" + ex.getMessage());
        }
    }

    public static String obtenerStringTimeStamp(String timestamp) throws Exception {
        try {

            String nueva_fecha = timestamp.substring(8, 10).concat("/").concat(timestamp.substring(5, 7)).concat("/").concat(timestamp.substring(0, 4));

            return nueva_fecha;
        } catch (Exception ex) {

            throw new Exception("Error en fecha nula:\n" + ex.getMessage());
        }
    }
}
