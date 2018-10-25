package persistencia;
// Generated 24-oct-2018 21:51:39 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private Integer idProducto;
     private Categoria categoria;
     private String nombre;
     private Double precio;
     private Integer stock;
     private Set detalles = new HashSet(0);

    public Producto() {
    }

	
    public Producto(Categoria categoria) {
        this.categoria = categoria;
    }
    public Producto(Categoria categoria, String nombre, Double precio, Integer stock, Set detalles) {
       this.categoria = categoria;
       this.nombre = nombre;
       this.precio = precio;
       this.stock = stock;
       this.detalles = detalles;
    }
   
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Integer getStock() {
        return this.stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Set getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(Set detalles) {
        this.detalles = detalles;
    }




}

