package persistencia;
// Generated 24-oct-2018 21:51:39 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private String nombreCliente;
     private String apellidoCliente;
     private String direccionCliente;
     private String fechaNacimiento;
     private String telefono;
     private String email;
     private Set facturas = new HashSet(0);

    public Cliente() {
    }

    public Cliente(String nombreCliente, String apellidoCliente, String direccionCliente, String fechaNacimiento, String telefono, String email, Set facturas) {
       this.nombreCliente = nombreCliente;
       this.apellidoCliente = apellidoCliente;
       this.direccionCliente = direccionCliente;
       this.fechaNacimiento = fechaNacimiento;
       this.telefono = telefono;
       this.email = email;
       this.facturas = facturas;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombreCliente() {
        return this.nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getApellidoCliente() {
        return this.apellidoCliente;
    }
    
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
    public String getDireccionCliente() {
        return this.direccionCliente;
    }
    
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set facturas) {
        this.facturas = facturas;
    }




}


