/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;



/**
 *
 * @author nando
 */
public class Documento {

    private int ukid;
    private String nombre;
    private String fecha_vencimiento;
    private String fecha_str;
    private String estado;
    private int disparar_diariamente;
    private int dias_diariamente;
    private String grupo_destinatario;
    private int dias_faltantes;
    private int compania;
    private int sucursal;
    private String sucursal_str;
    private String usuario;
    private List dias_aviso;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the disparar_diariamente
     */
    public int getDisparar_diariamente() {
        return disparar_diariamente;
    }

    /**
     * @param disparar_diariamente the disparar_diariamente to set
     */
    public void setDisparar_diariamente(int disparar_diariamente) {
        this.disparar_diariamente = disparar_diariamente;
    }

    /**
     * @return the dias_diariamente
     */
    public int getDias_diariamente() {
        return dias_diariamente;
    }

    /**
     * @param dias_diariamente the dias_diariamente to set
     */
    public void setDias_diariamente(int dias_diariamente) {
        this.dias_diariamente = dias_diariamente;
    }

    /**
     * @return the grupo_destinatario
     */
    public String getGrupo_destinatario() {
        return grupo_destinatario;
    }

    /**
     * @param grupo_destinatario the grupo_destinatario to set
     */
    public void setGrupo_destinatario(String grupo_destinatario) {
        this.grupo_destinatario = grupo_destinatario;
    }

    /**
     * @return the dias_faltantes
     */
    public int getDias_faltantes() {
        return dias_faltantes;
    }

    /**
     * @param dias_faltantes the dias_faltantes to set
     */
    public void setDias_faltantes(int dias_faltantes) {
        this.dias_faltantes = dias_faltantes;
    }

    /**
     * @return the compania
     */
    public int getCompania() {
        return compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(int compania) {
        this.compania = compania;
    }

    /**
     * @return the sucursal
     */
    public int getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the fecha_vencimiento
     */
    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    /**
     * @param fecha_vencimiento the fecha_vencimiento to set
     */
    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    /**
     * @return the fecha_str
     */
    public String getFecha_str() {


        return fecha_str;
    }

    /**
     * @param fecha_str the fecha_str to set
     */
    public void setFecha_str(String fecha_str) {
        this.fecha_str = fecha_str;
    }

     public void setFecha_str(Timestamp fecha) {
         Date date = new Date(fecha.getTime());
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         
          try {
                this.fecha_str = formatter.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         
    }

   
    /**
     * @return the dias_aviso
     */
    public List getDias_aviso() {
        return dias_aviso;
    }

    /**
     * @param dias_aviso the dias_aviso to set
     */
    public void setDias_aviso(List dias_aviso) {
        this.dias_aviso = dias_aviso;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ukid
     */
    public int getUkid() {
        return ukid;
    }

    /**
     * @param ukid the ukid to set
     */
    public void setUkid(int ukid) {
        this.ukid = ukid;
    }

    /**
     * @return the sucursal_str
     */
    public String getSucursal_str() {
        return sucursal_str;
    }

    /**
     * @param sucursal_str the sucursal_str to set
     */
    public void setSucursal_str(String sucursal_str) {
        this.sucursal_str = sucursal_str;
    }


}
