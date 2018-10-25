package persistencia;
// Generated 24-oct-2018 21:51:39 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Factura generated by hbm2java
 */
public class Factura  implements java.io.Serializable {


     private Integer idFactura;
     private Cliente cliente;
     private ModoPago modoPago;
     private String fechaFactura;
     private Set detalles = new HashSet(0);

    public Factura() {
    }

	
    public Factura(Cliente cliente, ModoPago modoPago) {
        this.cliente = cliente;
        this.modoPago = modoPago;
    }
    public Factura(Cliente cliente, ModoPago modoPago, String fechaFactura, Set detalles) {
       this.cliente = cliente;
       this.modoPago = modoPago;
       this.fechaFactura = fechaFactura;
       this.detalles = detalles;
    }
   
    public Integer getIdFactura() {
        return this.idFactura;
    }
    
    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public ModoPago getModoPago() {
        return this.modoPago;
    }
    
    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }
    public String getFechaFactura() {
        return this.fechaFactura;
    }
    
    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    public Set getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(Set detalles) {
        this.detalles = detalles;
    }




}


