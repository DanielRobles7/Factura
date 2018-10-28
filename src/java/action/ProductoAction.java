/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.ProductoActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.CategoriaMan;
import mantenimiento.ProductoMan;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.Producto;

/**
 *
 * @author DanyDanny
 */
public class ProductoAction extends org.apache.struts.action.Action {

    private static final String GUARDAR = "agregarPro";
    private static final String MODIFICAR = "modificarPro";
    private static final String LISTA = "listaPro";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ProductoActionForm bean = (ProductoActionForm) form;
        Integer idProducto = bean.getIdProducto();
        String nombre = bean.getNombre();
        Double precio = bean.getPrecio();
        int stock = bean.getStock();
        Integer idCategoria = bean.getIdCategoria();

        String action = bean.getAction();

        String IR = null;
        String msg = null;
        ProductoMan pman = new ProductoMan();
        CategoriaMan cman = new CategoriaMan();
        Producto p;

        System.out.println("action = " + action);

        System.out.println("idProducto = " + idProducto);
        System.out.println("nombre = " + nombre);
        System.out.println("precio = " + precio);
        System.out.println("stock = " + stock);
        System.out.println("idCategoria = " + idCategoria);

        if (action.equalsIgnoreCase("nuevo")) {
            bean.setListaCategoria(cman.consultarTodos());
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("guardar")) {
            pman.guardar(nombre, precio, stock, idCategoria);
            msg = "Producto " + nombre + " guardado";
            bean.setListaProducto(pman.consultarTodos());
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("modificar")) {
            pman.modificar(idProducto, nombre, precio, stock, idCategoria);
            msg = "Categoria " + nombre + " modificados";
            bean.setListaProducto(pman.consultarTodos());
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("eliminar")) {
            nombre = pman.consultarId(idProducto).getNombre();
            pman.eliminar(idProducto);
            msg = "Producto " + nombre + " eliminado";
            bean.setListaProducto(pman.consultarTodos());
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("detalle")) {
            p = pman.consultarId(idProducto);
            bean.setIdProducto(p.getIdProducto());
            bean.setNombre(p.getNombre());
            bean.setPrecio(p.getPrecio());
            bean.setStock(p.getStock());
            bean.setIdCategoria(p.getCategoria().getIdCategoria());
            bean.setListaCategoria(cman.consultarTodos());
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("consultar")) {
            bean.setListaProducto(pman.consultarTodos());
            IR = LISTA;
        }

        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
