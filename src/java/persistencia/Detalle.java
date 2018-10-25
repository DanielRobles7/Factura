package persistencia;
// Generated 24-oct-2018 21:51:39 by Hibernate Tools 4.3.1



/**
 * Detalle generated by hbm2java
 */
public class Detalle  implements java.io.Serializable {


     private Integer idDetalle;
     private Factura factura;
     private Producto producto;
     private Integer cantidad;
     private Double precio;

    public Detalle() {
    }

	
    public Detalle(Factura factura, Producto producto) {
        this.factura = factura;
        this.producto = producto;
    }
    public Detalle(Factura factura, Producto producto, Integer cantidad, Double precio) {
       this.factura = factura;
       this.producto = producto;
       this.cantidad = cantidad;
       this.precio = precio;
    }
   
    public Integer getIdDetalle() {
        return this.idDetalle;
    }
    
    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
    public Factura getFactura() {
        return this.factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }




}

