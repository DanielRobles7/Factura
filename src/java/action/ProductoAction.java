/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.ProductoActionForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.CategoriaMan;
import mantenimiento.ProductoMan;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.Categoria;
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
        String error = null;
        String info = null;
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
            List<Categoria> lc = cman.consultarTodos();
            if (lc.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaCategoria(lc);
            }
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("guardar")) {
            if (nombre.equals("")
                    || precio <= 0
                    || stock <= 0
                    || idCategoria <= 0
                    || idCategoria == null) {
                bean.setListaCategoria(cman.consultarTodos());
                error = "complete los campos vacios ";
                IR = GUARDAR;
            } else {
                if (pman.consultarExistencia(nombre) == 0) {
                    error = "ya existe una Producto registrado como (" + nombre + ")";
                    bean.setListaCategoria(cman.consultarTodos());
                    IR = GUARDAR;
                } else {
                    pman.guardar(nombre, precio, stock, idCategoria);
                    msg = "Producto " + nombre + " guardado";
                    bean.setListaProducto(pman.consultarTodos());
                    IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("modificar")) {
            if (nombre.equals("")
                    || precio <= 0
                    || stock <= 0
                    || idCategoria <= 0
                    || idCategoria == null) {
                bean.setListaCategoria(cman.consultarTodos());
                error = "complete los campos vacios ";
                IR = MODIFICAR;
            } else {
                if (pman.consultarExistencia(nombre) == 0
                        && !nombre.equals(pman.consultarId(idProducto).getNombre())) {
                    error = "no puede cambiar el nombre a (" + nombre + "); ya hay un producto registrado así";
                    bean.setListaCategoria(cman.consultarTodos());
                    IR = MODIFICAR;
                } else {
                    pman.modificar(idProducto, nombre, precio, stock, idCategoria);
                    msg = "Categoria " + nombre + " modificados";
                    bean.setListaProducto(pman.consultarTodos());
                    IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("eliminar")) {
            nombre = pman.consultarId(idProducto).getNombre();
            int v = pman.eliminar(idProducto);
            if (v == 1) {
                msg = "Producto " + nombre + " eliminado";
            }else {
                error = "Producto (" + nombre + ") no puede eliminarse, esta siendo utilizado en otra area";
            }
             List<Producto> lp = pman.consultarTodos();
            if (lp.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaProducto(lp);
            }
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
            List<Producto> lp = pman.consultarTodos();
            if (lp.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaProducto(lp);
            }
            IR = LISTA;
        }

        request.setAttribute("error", error);
        request.setAttribute("info", info);
        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
