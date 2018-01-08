/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.conexion;

import java.sql.SQLException;

/**
 *
 * @author nando
 */
public class Conexion {

     public static  java.sql.Connection getConexion() {

     String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost/vencimiento";
    String user = "postgres";
    String password = "123456";

    java.sql.Connection cnn=null;
    if (cnn==null)
    {
        try {
          Class.forName(driver);
          try {
         cnn    = java.sql.DriverManager.getConnection(url, user, password);

          } catch (SQLException ex) {
            ex.printStackTrace();
          }
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
        }
    }

    return cnn;

   }

}
