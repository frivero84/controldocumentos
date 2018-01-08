/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.flexbrokers;

import flex.messaging.io.amf.ASObject;
import helper.Util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import modelo.conexion.Conexion;
import modelo.data.DiasAviso;
import modelo.data.Documento;

import modelo.data.OrdenCarga;
import modelo.data.Udc;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author nando
 */
public class BrokerPrimario {

    private Connection cnn = null;
    private Statement st = null;
    private ResultSet rs = null;

    // ----- M. UDC ------
    public Udc getUDC(String sistema, String subsistema, String id) throws Exception {
        try {
            String query = "SELECT sistema,subsistema,id,valor1,valor2,valor3 "
                    + " FROM udc WHERE sistema = '" + sistema + "'"
                    + " AND subsistema = '" + subsistema + "'"
                    + " AND id = '" + id + "'";
            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            Udc udc = null;
            if (rs.next()) {
                udc = new Udc();
                udc.setSistema(rs.getString("sistema"));
                udc.setSubSistema(rs.getString("subsistema"));
                udc.setId(rs.getString("id"));
                udc.setValor1(rs.getString("valor1"));
                udc.setValor2(rs.getString("valor2"));
                udc.setValor3(rs.getFloat("valor3"));
            }
            rs.close();
            st.close();
            cnn.close();
            return udc;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar la UDC con ID: " + id + "\n" + ex.getMessage());
        }
    }

    public void guardarUDC(Object object) throws Exception {
        Udc udc = new Udc();
        boolean result = false;
        try {

            ASObject asobject = (ASObject) object;
            BeanUtils.populate(udc, asobject);

            PreparedStatement ps = null;
            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            String query = "INSERT INTO orden(sistema, subsistema,id,valor1,valor2,valor3) "
                    + " VALUES (?, ?, ?, ?, ?, ?) ";

            ps = cnn.prepareStatement(query);
            ps.setString(1, udc.getSistema());
            ps.setString(2, udc.getSubSistema());
            ps.setString(3, udc.getId());
            ps.setString(4, udc.getValor1());
            ps.setString(5, udc.getValor2());
            ps.setFloat(6, udc.getValor3());

            result = ps.executeUpdate() == 0 ? false : true;
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar guardar la udc " + "\n" + ex.getMessage());
        }
    }

    public void actualizarUDC(Object object) throws Exception {
        Udc udc = new Udc();
        boolean result = false;
        try {

            ASObject asobject = (ASObject) object;
            BeanUtils.populate(udc, asobject);

            PreparedStatement ps = null;
            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            String query = "UPDATE UDC set valor1= ?,valor2= ? ,valor3= ? "
                    + " WHERE sistema=? AND subsistema=? AND id=? ";

            ps = cnn.prepareStatement(query);
            ps.setString(1, udc.getValor1());
            ps.setString(2, udc.getValor2());
            ps.setFloat(3, udc.getValor3());
            ps.setString(4, udc.getSistema());
            ps.setString(5, udc.getSubSistema());
            ps.setString(6, udc.getId());


            result = ps.executeUpdate() == 0 ? false : true;
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar guardar la udc " + "\n" + ex.getMessage());
        }
    }

    public void actualizarUDC(Udc udc) throws Exception {

        boolean result = false;
        try {
            PreparedStatement ps = null;
            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            String query = "UPDATE UDC set valor1= ?,valor2= ? ,valor3= ? "
                    + " WHERE sistema=? AND subsistema=? AND id=? ";

            ps = cnn.prepareStatement(query);
            ps.setString(1, udc.getValor1());
            ps.setString(2, udc.getValor2());
            ps.setFloat(3, udc.getValor3());
            ps.setString(4, udc.getSistema());
            ps.setString(5, udc.getSubSistema());
            ps.setString(6, udc.getId());


            result = ps.executeUpdate() == 0 ? false : true;
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar guardar la udc " + "\n" + ex.getMessage());
        }
    }

    public List getUDC(String sistema, String subsistema) throws Exception {
        List arrayUDC = new ArrayList();
        try {
            String query = "SELECT sistema,subsistema,id,valor1,valor2,valor3 "
                    + " FROM udc WHERE sistema = '" + sistema + "'"
                    + " AND subsistema = '" + subsistema + "'";

            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Udc udc = new Udc();
                udc.setSistema(rs.getString("sistema"));
                udc.setSubSistema(rs.getString("subsistema"));
                udc.setId(rs.getString("id"));
                udc.setValor1(rs.getString("valor1"));
                udc.setValor2(rs.getString("valor2"));
                udc.setValor3(rs.getFloat("valor3"));
                arrayUDC.add(udc);
            }
            rs.close();
            st.close();
            cnn.close();
            return arrayUDC;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar la UDC con ID: " + subsistema + "\n" + ex.getMessage());
        }
    }


     public Udc checkLogin(String usuario, String password) throws Exception {
        List arrayUDC = new ArrayList();
        Udc udc = null;
        try {
            String query = "SELECT * "
                    + " FROM udc WHERE sistema = 'TABLAS'"
                    + " AND subsistema = 'USUARIO' AND VALOR1='"+ usuario +"' AND VALOR2='"+ password +"'";

            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                udc = new Udc();
                udc.setSistema(rs.getString("sistema"));
                udc.setSubSistema(rs.getString("subsistema"));
                udc.setId(rs.getString("id"));
                udc.setValor1(rs.getString("valor1"));
                udc.setValor2(rs.getString("valor2"));
                udc.setValor3(rs.getFloat("valor3"));
                udc.setValor4(rs.getString("valor4"));
                udc.setEmpresa(rs.getString("empresa"));
                
            }
            rs.close();
            st.close();
            cnn.close();
            return udc;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar la UDC con ID: " + usuario + "\n" + ex.getMessage());
        }
    }




    // ----- M. DOCUMENTO ------
    public List getDocumentos(String filtro) throws Exception {
        List array = new ArrayList();
        try {
            String query = "SELECT * "
                    + " FROM documento  " + filtro + " order by 1";

            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Documento documento = new Documento();
                documento.setUkid(rs.getInt("ukid"));
                documento.setCompania(rs.getInt("compania"));
                documento.setDias_diariamente(rs.getInt("dias_diariamente"));
                documento.setDias_faltantes(rs.getInt("dias_faltantes"));
                documento.setDisparar_diariamente(rs.getInt("disparar_diariamente"));
                documento.setEstado(rs.getString("estado"));
                documento.setFecha_vencimiento(Util.obtenerStringTimeStamp(rs.getString("fecha_vencimiento")));
                documento.setGrupo_destinatario(rs.getString("grupo_destinatario"));
                documento.setNombre(rs.getString("nombre"));

                if (rs.getInt("sucursal")==1)
                {
                  documento.setSucursal_str("BARQUISIMETO");
                }

                if (rs.getInt("sucursal")==2)
                {
                  documento.setSucursal_str("ACARIGUA");
                }

                if (rs.getInt("sucursal")==3)
                {
                  documento.setSucursal_str("TURMERO");
                }

                documento.setSucursal(rs.getInt("sucursal"));
                documento.setFecha_str(documento.getFecha_vencimiento());
                documento.setDias_aviso(getDiasAviso(documento.getUkid()));
                array.add(documento);
            }
            rs.close();
            st.close();
            cnn.close();
            return array;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar el item !" + ex.getMessage());
        }
    }

    public Documento getDocumento(String filtro) throws Exception {
        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;
        Documento documento = null;
        try {
            String query = "SELECT * "
                    + " FROM documento  " + filtro + " order by 1";

            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                documento = new Documento();
                documento.setUkid(rs.getInt("ukid"));
                documento.setCompania(rs.getInt("compania"));
                documento.setDias_diariamente(rs.getInt("dias_diariamente"));
                documento.setDias_faltantes(rs.getInt("dias_faltantes"));
                documento.setDisparar_diariamente(rs.getInt("disparar_diariamente"));
                documento.setEstado(rs.getString("estado"));
                documento.setFecha_vencimiento(Util.obtenerStringTimeStamp(rs.getString("fecha_vencimiento")));
                documento.setGrupo_destinatario(rs.getString("grupo_destinatario"));
                documento.setNombre(rs.getString("nombre"));
                documento.setSucursal(rs.getInt("sucursal"));
                documento.setFecha_str(documento.getFecha_vencimiento());
                documento.setDias_aviso(getDiasAviso(documento.getUkid()));

            }
            rs.close();
            st.close();
            cnn.close();
            return documento;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar el item !" + ex.getMessage());
        }
    }

    public List getDiasAviso(int documento) throws Exception {

        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;

        List array = new ArrayList();
        try {
            String query = "SELECT * "
                    + " FROM dias_documento where ukid_documento='" + documento + "'  order by dias";

            cnn = Conexion.getConexion();
            st = cnn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                DiasAviso diasAviso = new DiasAviso();
                diasAviso.setDias_aviso(rs.getInt("dias"));
                // diasAviso.setId_documento(rs.getInt("ukid_documento"));

                array.add(diasAviso.getDias_aviso());
            }
            rs.close();
            st.close();
            cnn.close();
            return array;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error al intentar buscar el item !" + ex.getMessage());
        }
    }

    public int obtenerSiguienteNumeroDOC() throws Exception {
        float next = 0;
        int nextval = 0;
        BrokerPrimario brokerPrimario = new BrokerPrimario();
        Udc udc = brokerPrimario.getUDC("TABLAS", "CORRELATIVO", "DOC");

        next = udc.getValor3();
        udc.setValor3(udc.getValor3() + 1);

        brokerPrimario.actualizarUDC(udc);
        nextval = (int) next;
        return nextval;

    }

    public boolean guardarDocumento(Object object) throws Exception {
        boolean result = false;
        boolean insertar = true;
        Documento documento = new Documento();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        GregorianCalendar hoy = new GregorianCalendar();
        int hora;
        int minutos;
        int ukid_orden = 0;

        try {

            ASObject asobject = (ASObject) object;
            BeanUtils.populate(documento, asobject);

            PreparedStatement ps = null;


            if (getDocumento("where ukid=" + documento.getUkid() + "") != null) {
                insertar = false;
            }

            cnn = Conexion.getConexion();
            cnn.setAutoCommit(false);
            st = cnn.createStatement();

            if (insertar) {
                String query = "INSERT INTO documento(ukid,nombre, fecha_vencimiento, estado, dias_diariamente, grupo_destinatario, dias_faltantes, compania, sucursal, disparar_diariamente,fecha_transaccion,hora,usuario)"
                        + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                ps = cnn.prepareStatement(query);

                ps.setInt(1, documento.getUkid());
                ps.setString(2, documento.getNombre());
                ps.setTimestamp(3, Util.obtenerTimeStamp(documento.getFecha_vencimiento()));
                ps.setString(4, documento.getEstado());
                ps.setInt(5, documento.getDias_diariamente());
                ps.setString(6, documento.getGrupo_destinatario());
                ps.setLong(7, Util.diferenciaFechas(Util.obtenerDate(documento.getFecha_vencimiento())));
                ps.setInt(8, documento.getCompania());
                ps.setInt(9, documento.getSucursal());
                ps.setInt(10, documento.getDisparar_diariamente());
                ps.setTimestamp(11, Util.obtenerTimeStamp(Util.obtenerFecha()));
                ps.setString(12, Util.obtenerHora());
                ps.setString(13, documento.getUsuario());



                result = ps.executeUpdate() == 0 ? false : true;
                ps.close();

                query = "delete from dias_documento where ukid_documento=?";
                ps = cnn.prepareStatement(query);
                ps.setInt(1, documento.getUkid());
                result = ps.executeUpdate() == 0 ? false : true;
                ps.close();

                List items = (List) asobject.get("dias");
                Iterator itItems = items.iterator();
                while (itItems.hasNext()) {
                    ps = null;
                    // Item item = new Item();
                    int dias = new Integer((itItems.next()).toString()).intValue();
                    query = "INSERT INTO dias_documento(ukid_documento,dias) "
                            + " VALUES ( ?, ?) ";
                    ps = cnn.prepareStatement(query);
                    ps.setInt(1, documento.getUkid());
                    ps.setInt(2, dias);
                    result = ps.executeUpdate() == 0 ? false : true;
                    ps.close();
                }

                cnn.commit();

                st.close();
                cnn.close();

            } else {


                String query = "update documento set nombre=?, fecha_vencimiento=?, estado=?, dias_diariamente=?, grupo_destinatario=?, dias_faltantes=?, compania=?, sucursal=?, disparar_diariamente=?,fecha_transaccion=?,hora=?,usuario=? WHERE ukid=? ";
                ps = cnn.prepareStatement(query);
                ps.setString(1, documento.getNombre());
                ps.setTimestamp(2, Util.obtenerTimeStamp(documento.getFecha_vencimiento()));
                ps.setString(3, documento.getEstado());
                ps.setInt(4, documento.getDias_diariamente());
                ps.setString(5, documento.getGrupo_destinatario());
                ps.setLong(6, Util.diferenciaFechas(Util.obtenerDate(documento.getFecha_vencimiento())));
                ps.setInt(7, documento.getCompania());
                ps.setInt(8, documento.getSucursal());
                ps.setInt(9, documento.getDisparar_diariamente());
                ps.setTimestamp(10, Util.obtenerTimeStamp(Util.obtenerFecha()));
                ps.setString(11, Util.obtenerHora());
                ps.setString(12, documento.getUsuario());
                ps.setInt(13, documento.getUkid());

                result = ps.executeUpdate() == 0 ? false : true;
                ps.close();

                query = "delete from dias_documento where ukid_documento=?";
                ps = cnn.prepareStatement(query);
                ps.setInt(1, documento.getUkid());
                result = ps.executeUpdate() == 0 ? false : true;
                ps.close();

                List items = (List) asobject.get("dias");
                Iterator itItems = items.iterator();
                while (itItems.hasNext()) {
                    ps = null;
                    // Item item = new Item();
                    int dias = new Integer((itItems.next()).toString()).intValue();
                    query = "INSERT INTO dias_documento(ukid_documento,dias) "
                            + " VALUES ( ?, ?) ";
                    ps = cnn.prepareStatement(query);
                    ps.setInt(1, documento.getUkid());
                    ps.setInt(2, dias);
                    result = ps.executeUpdate() == 0 ? false : true;
                    ps.close();
                }

                cnn.commit();

                st.close();
                cnn.close();




            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            cnn.rollback();
            throw new Exception("Error al intentar guardar la orden n°: " + documento.getUkid() + "\n" + ex.getMessage());
        }
    }



     public boolean eliminarDocumento(Object object) throws Exception {
        boolean result = false;
        
        Documento documento = new Documento();
        
        

        try {

            ASObject asobject = (ASObject) object;
            BeanUtils.populate(documento, asobject);

            PreparedStatement ps = null;
            cnn = Conexion.getConexion();
            cnn.setAutoCommit(false);
            st = cnn.createStatement();

            
                String query = "delete from documento where ukid=?";
                ps = cnn.prepareStatement(query);
                ps.setInt(1, documento.getUkid());
                result = ps.executeUpdate() == 0 ? false : true;
                ps.close();

             
                cnn.commit();

                st.close();
                cnn.close();

           
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            cnn.rollback();
            throw new Exception("Error al intentar guardar el documentos n°: " + documento.getUkid() + "\n" + ex.getMessage());
        }
    }

}
