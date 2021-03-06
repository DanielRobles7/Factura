package persistencia;
// Generated 24-oct-2018 21:51:39 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * ModoPago generated by hbm2java
 */
public class ModoPago  implements java.io.Serializable {


     private Integer idPago;
     private String nombreModoPago;
     private String otrosDetalles;
     private Set facturas = new HashSet(0);

    public ModoPago() {
    }

    public ModoPago(String nombreModoPago, String otrosDetalles, Set facturas) {
       this.nombreModoPago = nombreModoPago;
       this.otrosDetalles = otrosDetalles;
       this.facturas = facturas;
    }
   
    public Integer getIdPago() {
        return this.idPago;
    }
    
    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }
    public String getNombreModoPago() {
        return this.nombreModoPago;
    }
    
    public void setNombreModoPago(String nombreModoPago) {
        this.nombreModoPago = nombreModoPago;
    }
    public String getOtrosDetalles() {
        return this.otrosDetalles;
    }
    
    public void setOtrosDetalles(String otrosDetalles) {
        this.otrosDetalles = otrosDetalles;
    }
    public Set getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set facturas) {
        this.facturas = facturas;
    }




}


