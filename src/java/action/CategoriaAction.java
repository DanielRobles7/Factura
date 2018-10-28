/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.CategoriaActionForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.CategoriaMan;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.Categoria;

/**
 *
 * @author DanyDanny
 */
public class CategoriaAction extends org.apache.struts.action.Action {

    private static final String GUARDAR = "agregarCat";
    private static final String MODIFICAR = "modificarCat";
    private static final String LISTA = "listaCat";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CategoriaActionForm bean = (CategoriaActionForm) form;
        Integer idCategoria = bean.getIdCategoria();
        String nombre = bean.getNombre();
        String descripcion = bean.getDescripcion();

        String action = bean.getAction();

        String IR = null;
        String msg = null;
        String info = null;
        String error = null;
        CategoriaMan cman = new CategoriaMan();
        Categoria c;

        System.out.println("action = " + action);

        System.out.println("idCategoria = " + idCategoria);
        System.out.println("nombre = " + nombre);
        System.out.println("descripcion = " + descripcion);

        if (action.equalsIgnoreCase("nuevo")) {
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("guardar")) {
            if (nombre.equals("") || descripcion.equals("")) {
                error = "complete los campos vacios ";
                IR = GUARDAR;
            } else {
                if (cman.consultarExistencia(nombre) == 0) {
                    error = "ya existe una categoria registrada como (" + nombre + ")";
                    IR = GUARDAR;
                } else {
                    cman.guardar(nombre, descripcion);
                    msg = "Categoria " + nombre + " guardada";
                    bean.setListaCategoria(cman.consultarTodos());
                    IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("modificar")) {
            if (nombre.equals("") || descripcion.equals("")) {
                error = "complete los campos vacios ";
                IR = MODIFICAR;
            } else {
                if (cman.consultarExistencia(nombre) == 0 
                        && !nombre.equals(cman.consultarId(idCategoria).getNombre())) {
                    error = "no puede cambiar el nombre a (" + nombre + "); ya hay una categoria llamada así";
                    IR = MODIFICAR;
                } else {
                cman.modificar(idCategoria, nombre, descripcion);
                msg = "Categoria " + nombre + " modificada";
                bean.setListaCategoria(cman.consultarTodos());
                IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("eliminar")) {
            nombre = cman.consultarId(idCategoria).getNombre();
            int v = cman.eliminar(idCategoria);
            if (v == 1) {
                msg = "Categoria " + nombre + " eliminada";
            }else {
                error = "Categoria (" + nombre + ") no puede eliminarse, esta siendo utilizada en otra area";
            }
            List<Categoria> lc = cman.consultarTodos();
            if (lc.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaCategoria(lc);
            }
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("detalle")) {
            c = cman.consultarId(idCategoria);
            bean.setIdCategoria(c.getIdCategoria());
            bean.setNombre(c.getNombre());
            bean.setDescripcion(c.getDescripcion());
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("consultar")) {
            List<Categoria> lc = cman.consultarTodos();
            if (lc.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaCategoria(lc);
            }
            IR = LISTA;
        }

        request.setAttribute("info", info);
        request.setAttribute("error", error);
        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
