/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.ModoPagoActionForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.ModoPagoMan;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.ModoPago;

/**
 *
 * @author DanyDanny
 */
public class ModoPagoAction extends org.apache.struts.action.Action {

    private static final String GUARDAR = "agregarMod";
    private static final String MODIFICAR = "modificarMod";
    private static final String LISTA = "listaMod";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModoPagoActionForm bean = (ModoPagoActionForm) form;
        Integer idPago = bean.getIdPago();
        String nombreModoPago = bean.getNombreModoPago();
        String otrosDetalles = bean.getOtrosDetalles();

        String action = bean.getAction();

        String IR = null;
        String msg = null;
        String error = null;
        String info = null;
        ModoPagoMan mman = new ModoPagoMan();
        ModoPago m;

        System.out.println("action = " + action);

        System.out.println("idPago = " + idPago);
        System.out.println("nombre = " + nombreModoPago);
        System.out.println("descripcion = " + otrosDetalles);

        if (action.equalsIgnoreCase("nuevo")) {
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("guardar")) {
            if (nombreModoPago.equals("") || otrosDetalles.equals("")) {
                error = "complete los campos vacios ";
                IR = GUARDAR;
            } else {
                if (mman.consultarExistencia(nombreModoPago) == 0) {
                    error = "ya existe Modo de Pago registrad como (" + nombreModoPago + ")";
                    IR = GUARDAR;
                } else {
                    mman.guardar(nombreModoPago, otrosDetalles);
                    msg = "Modo de Pago " + nombreModoPago + " guardado";
                    bean.setListaModoPago(mman.consultarTodos());
                    IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("modificar")) {
            if (nombreModoPago.equals("") || otrosDetalles.equals("")) {
                error = "complete los campos vacios ";
                IR = MODIFICAR;
            } else {
                if (mman.consultarExistencia(nombreModoPago) == 0 &&
                        !nombreModoPago.equals(mman.consultarId(idPago).getNombreModoPago())) {
                    error = "ya existe Modo de Pago registrad como (" + nombreModoPago + ")";
                    IR = MODIFICAR;
                } else {
                        mman.modificar(idPago, nombreModoPago, otrosDetalles);
                        msg = "Modo de Pago " + nombreModoPago + " modificado";
                        bean.setListaModoPago(mman.consultarTodos());
                        IR = LISTA;
                }
            }
        }

        if (action.equalsIgnoreCase("eliminar")) {
            nombreModoPago = mman.consultarId(idPago).getNombreModoPago();
            int v = mman.eliminar(idPago);
            if (v == 1) {
                msg = "Modo de Pago " + nombreModoPago + " eliminado";
            }else {
                error = "Modo de Pago (" + nombreModoPago + ") no puede eliminarse, esta siendo utilizado en otra area";
            }
            List<ModoPago> mp = mman.consultarTodos();
            if (mp.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaModoPago(mp);
            }
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("detalle")) {
            m = mman.consultarId(idPago);
            bean.setIdPago(m.getIdPago());
            bean.setNombreModoPago(m.getNombreModoPago());
            bean.setOtrosDetalles(m.getOtrosDetalles());
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("consultar")) {
            List<ModoPago> mp = mman.consultarTodos();
            if (mp.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaModoPago(mp);
            }
            IR = LISTA;
        }

        request.setAttribute("error", error);
        request.setAttribute("info", info);
        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
