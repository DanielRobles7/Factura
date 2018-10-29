/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.FacturaActionForm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.ClienteMan;
import mantenimiento.DetalleMan;
import mantenimiento.FacturaMan;
import mantenimiento.ModoPagoMan;
import mantenimiento.ProductoMan;
import metodos.SumarTotalFactura;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.Detalle;
import persistencia.Factura;

/**
 *
 * @author DanyDanny
 */
public class FacturaAction extends org.apache.struts.action.Action {

    private static final String GUARDAR = "agregarFac";
    private static final String MODIFICAR = "modificarFac";
    private static final String LISTA = "listaFac";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        FacturaActionForm bean = (FacturaActionForm) form;
//factura        
        Integer idFactura = bean.getIdFactura();
        Integer idCliente = bean.getIdCliente();
        Integer idPago = bean.getIdPago();
        String fechaFactura = bean.getFechaFactura();
        double totalFactura = bean.getTotalFactura();
//detalle    
        Integer idDetalle = bean.getIdDetalle();
        Integer idProducto = bean.getIdProducto();
        int cantidad = bean.getCantidad();
        double precio = bean.getPrecio();
        double total = bean.getTotal();

        String action = bean.getAction();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SumarTotalFactura suma = new SumarTotalFactura();
        String IR = null;
        String msg = null;
        String error = "";
        String info = null;
        FacturaMan fman = new FacturaMan();
        DetalleMan dman = new DetalleMan();
        ClienteMan cman = new ClienteMan();
        ProductoMan pman = new ProductoMan();
        ModoPagoMan mman = new ModoPagoMan();
        Factura f;
        Detalle d;

        System.out.println("action = " + action);

//factura        
        System.out.println("idFactura = " + idFactura);
        System.out.println("idCliente = " + idCliente);
        System.out.println("idPago = " + idPago);
        System.out.println("fechaFactura = " + fechaFactura);
        System.out.println("totalFactura = " + totalFactura);
//detalle        
        System.out.println("idDetalle = " + idDetalle);
        System.out.println("idProducto = " + idProducto);
        System.out.println("cantidad = " + cantidad);
        System.out.println("precio = " + precio);
        System.out.println("total = " + total);

        if (action.equalsIgnoreCase("nuevo")) {
            
            System.out.println("ver la consulta " +fman.consultarTodos().size());
   
                idFactura = fman.maxIdFactura();
            f = fman.consultarId(idFactura);
            if (0==f.getTotalFactura()) {
                bean.setIdCliente(f.getCliente().getIdCliente());
                bean.setIdPago(f.getModoPago().getIdPago());
                bean.setFechaFactura(f.getFechaFactura());
                bean.setListaDetalle(dman.consultaDetalleEspecifico(idFactura));
                info = "Factura Pendiente de Guardar";
                request.setAttribute("num", idFactura);
            }
            else{
                request.setAttribute("num", idFactura +1);
            }
            bean.setListaCliente(cman.consultarTodos());
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            bean.setFechaFactura(formato.format(new Date()));
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("agregar")) {
            if (idPago <=0) {
                error += "seleccione un Modo de Pago, ";
            }
            if (idCliente <=0) {
                error +="seleccione un Cliente, ";
            }
            if (idProducto <= 0) {
                error += "seleccione un Producto, ";
            }
            if (cantidad <= 0) {
                error += "seleccione una Cantidad mayor a cero ";
            }
            if (error.equals("")) {

//evitar duplicado en factura
                List<Factura> lf = fman.consultarTodos();

                if (lf.isEmpty()) {
                    fman.guardar(idCliente, fechaFactura, idPago, 0.0);
                    System.out.println("se creo factura porq no existia");
                } else {
                    idFactura = fman.maxIdFactura();
                    totalFactura = fman.consultarId(idFactura).getTotalFactura();
                    System.out.println("totalFactura trae = " + totalFactura);
                    if (totalFactura > 0 || lf.size() < 2) {
                        fman.guardar(idCliente, fechaFactura, idPago, 0.0);
                    }
                }
                idFactura = fman.maxIdFactura();
                bean.setIdCliente(fman.consultarId(idFactura).getCliente().getIdCliente());
                bean.setIdPago(fman.consultarId(idFactura).getModoPago().getIdPago());
//total detalle 
                if (idProducto > 0) {
                    total = pman.consultarId(idProducto).getPrecio() * cantidad;
                    int v = dman.guardar(idFactura, idProducto, cantidad, precio, total);

                    if (v == 1) {
                        msg = "Detalle Agregado";
                    }
                }

            }
//------------------------------------------------------------------------------------------            
//fecha            
            if (fechaFactura.equals("")) {
                fechaFactura = formato.format(new Date());
            }
            bean.setFechaFactura(fechaFactura);

// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
            request.setAttribute("totalFactura", totalFactura);
// listaDetalle
                List<Detalle> listaDetalle = dman.consultaDetalleEspecifico(idFactura);
                bean.setListaDetalle(listaDetalle);
//para cargar la factura
            bean.setListaCliente(cman.consultarTodos());
            
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            request.setAttribute("num", idFactura);
            bean.setFechaFactura(fechaFactura);

            IR = GUARDAR;
        }
        if (action.equalsIgnoreCase("agregar ")) {
//fecha            
            if (fechaFactura.equals("")) {
                fechaFactura = formato.format(new Date());
            }
            bean.setFechaFactura(fechaFactura);

//total detalle            
            total = pman.consultarId(idProducto).getPrecio() * cantidad;
            int v = dman.guardar(idFactura, idProducto, cantidad, precio, total);

            if (v == 1) {
                msg = "Detalle Agregado";
            }
// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
            request.setAttribute("totalFactura", totalFactura);
// listaDetalle            
            List<Detalle> listaDetalle = dman.consultaDetalleEspecifico(idFactura);
            bean.setListaDetalle(listaDetalle);
//para cargar la factura
            bean.setListaCliente(cman.consultarTodos());
            bean.setIdCliente(fman.consultarId(idFactura).getCliente().getIdCliente());
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            bean.setIdPago(fman.consultarId(idFactura).getModoPago().getIdPago());
            bean.setFechaFactura(fechaFactura);
            request.setAttribute("num", idFactura);
            IR = MODIFICAR;
        }
        if (action.equalsIgnoreCase("guardar")) {
            idFactura = fman.maxIdFactura();
// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
//modificar FacturaEncabezado ya que se creo en agregar.             
            f = fman.consultarId(idFactura);
            idCliente = f.getCliente().getIdCliente();
            fechaFactura = f.getFechaFactura();
            idPago = f.getModoPago().getIdPago();
            fman.modificar(idFactura, idCliente, fechaFactura, idPago, totalFactura);
//llenado de la lista
            bean.setListaFactura(fman.consultarTodos());
            IR = LISTA;
        }
        if (action.equalsIgnoreCase("Actualizar")) {
// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
//modificar FacturaEncabezado ya que se creo en agregar.             
            f = fman.consultarId(idFactura);
            idCliente = f.getCliente().getIdCliente();
            fechaFactura = f.getFechaFactura();
            idPago = f.getModoPago().getIdPago();
            fman.modificar(idFactura, idCliente, fechaFactura, idPago, totalFactura);
//llenado de la lista
            bean.setListaFactura(fman.consultarTodos());
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("x")) {
//modificar stock            
            idProducto = dman.consultarId(idDetalle).getProducto().getIdProducto();
            String nombreProducto = pman.consultarId(idProducto).getNombre();
            double precioProducto = pman.consultarId(idProducto).getPrecio();
            Integer categoria = pman.consultarId(idProducto).getCategoria().getIdCategoria();
            int stock = pman.consultarId(idProducto).getStock() + dman.consultarId(idDetalle).getCantidad();
            pman.modificar(idProducto, nombreProducto, precioProducto, stock, categoria);
// eliminar
            dman.eliminar(idDetalle);
// calcular totalFactura
            idFactura = fman.maxIdFactura();
            totalFactura = suma.sumarTotalFactura(idFactura);
            request.setAttribute("totalFactura", totalFactura);
// listaDetalle            
            List<Detalle> listaDetalle = dman.consultaDetalleEspecifico(idFactura);
            bean.setListaDetalle(listaDetalle);
//para cargar la factura
            bean.setListaCliente(cman.consultarTodos());
            bean.setIdCliente(fman.consultarId(idFactura).getCliente().getIdCliente());
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            bean.setIdPago(fman.consultarId(idFactura).getModoPago().getIdPago());
            bean.setFechaFactura(fman.consultarId(idFactura).getFechaFactura());
            request.setAttribute("num", idFactura);
            IR = GUARDAR;
        }
        if (action.equalsIgnoreCase("x ")) {

//modificar stock            
            idProducto = dman.consultarId(idDetalle).getProducto().getIdProducto();
            idFactura = dman.consultarId(idDetalle).getFactura().getIdFactura();
            bean.setIdFactura(idFactura);
            String nombreProducto = pman.consultarId(idProducto).getNombre();
            double precioProducto = pman.consultarId(idProducto).getPrecio();
            Integer categoria = pman.consultarId(idProducto).getCategoria().getIdCategoria();
            int stock = pman.consultarId(idProducto).getStock() + dman.consultarId(idDetalle).getCantidad();
            pman.modificar(idProducto, nombreProducto, precioProducto, stock, categoria);
// eliminar
            dman.eliminar(idDetalle);
// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
            request.setAttribute("totalFactura", totalFactura);
// listaDetalle            
            List<Detalle> listaDetalle = dman.consultaDetalleEspecifico(idFactura);
            bean.setListaDetalle(listaDetalle);
//para cargar la factura
            bean.setListaCliente(cman.consultarTodos());
            bean.setIdCliente(fman.consultarId(idFactura).getCliente().getIdCliente());
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            bean.setIdPago(fman.consultarId(idFactura).getModoPago().getIdPago());
            bean.setFechaFactura(fman.consultarId(idFactura).getFechaFactura());
            request.setAttribute("num", idFactura);
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("eliminar")) {

            totalFactura = fman.consultarId(idFactura).getTotalFactura();
            if (totalFactura > 0) {
                info = "No se puede eliminar la factura, porque tiene un saldo de "
                        + totalFactura;
            } else {
                msg = "Factura ID: " + idFactura + " Eliminada";
                fman.eliminar(idFactura);
            }
            bean.setListaFactura(fman.consultarTodos());
            IR = LISTA;
        }
        if (action.equalsIgnoreCase("detalle")) {
            f = fman.consultarId(idFactura);
            bean.setIdFactura(f.getIdFactura());
            bean.setIdCliente(f.getCliente().getIdCliente());
            bean.setFechaFactura(f.getFechaFactura());
            bean.setIdPago(f.getModoPago().getIdPago());
            bean.setTotal(f.getTotalFactura());
// calcular totalFactura
            totalFactura = suma.sumarTotalFactura(idFactura);
            request.setAttribute("totalFactura", totalFactura);
// listaDetalle            
            bean.setListaDetalle(dman.consultaDetalleEspecifico(idFactura));
//para cargar la factura
            bean.setListaCliente(cman.consultarTodos());
            bean.setIdCliente(fman.consultarId(idFactura).getCliente().getIdCliente());
            bean.setListaProducto(pman.consultarTodos());
            bean.setListaModoPago(mman.consultarTodos());
            bean.setIdPago(fman.consultarId(idFactura).getModoPago().getIdPago());
            bean.setFechaFactura(fman.consultarId(idFactura).getFechaFactura());
            request.setAttribute("num", idFactura);
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("consultar")) {
            bean.setListaFactura(fman.consultarTodos());
            IR = LISTA;
        }

        request.setAttribute("info", info);
        request.setAttribute("error", error);
        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
