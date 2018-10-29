/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import actionForm.ClienteActionForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.ClienteMan;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import persistencia.Cliente;

/**
 *
 * @author DanyDanny
 */
public class ClienteAction extends org.apache.struts.action.Action {

    private static final String GUARDAR = "agregarCli";
    private static final String MODIFICAR = "modificarCli";
    private static final String LISTA = "listaCli";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ClienteActionForm bean = (ClienteActionForm) form;
        Integer idCliente = bean.getIdCliente();
        String nombreCliente = bean.getNombreCliente();
        String apellidoCliente = bean.getApellidoCliente();
        String direccionCliente = bean.getDireccionCliente();
        String fechaNacimiento = bean.getFechaNacimiento();
        String telefono = bean.getTelefono();
        String email = bean.getEmail();

        String action = bean.getAction();

        String IR = null;
        String msg = null;
        String error = null;
        String info = null;
        ClienteMan cman = new ClienteMan();
        Cliente c;

        System.out.println("action = " + action);

        System.out.println("idProducto = " + idCliente);
        System.out.println("nombreCliente = " + nombreCliente);
        System.out.println("apellidoCliente = " + apellidoCliente);
        System.out.println("direccionCliente = " + direccionCliente);
        System.out.println("fechaNacimiento = " + fechaNacimiento);
        System.out.println("telefono = " + telefono);
        System.out.println("email = " + email);

        if (action.equalsIgnoreCase("nuevo")) {
            IR = GUARDAR;
        }

        if (action.equalsIgnoreCase("guardar")) {
            if (nombreCliente.equals("")
                    || apellidoCliente.equals("")
                    || direccionCliente.equals("")
                    || fechaNacimiento.equals("")
                    || telefono.equals("")
                    || email.equals("")) {
                error = "complete los campos vacios ";
                IR = GUARDAR;
            } else {
                if (cman.consultarExistencia(nombreCliente) == 0) {
                    error = "ya existe un Cliente registrado como (" + nombreCliente + ")";
                    IR = GUARDAR;
                } else {
                    String a = "@";
                    boolean r = email.contains(a);
                    System.out.println("ver " + r);
                    if (r) {
                        cman.guardar(nombreCliente, apellidoCliente, direccionCliente, fechaNacimiento, telefono, email);
                        msg = "Cliente " + nombreCliente + " guardado";
                        bean.setListaCliente(cman.consultarTodos());
                        IR = LISTA;
                    } else {
                        error = "el correo debe contener un \"@\"";
                        IR = GUARDAR;
                    }
                }
            }
        }
            if (action.equalsIgnoreCase("modificar")) {
                System.out.println("en modificar");
                if (nombreCliente.equals("")
                        || apellidoCliente.equals("")
                        || direccionCliente.equals("")
                        || fechaNacimiento.equals("")
                        || telefono.equals("")
                        || email.equals("")) {
                    error = "complete los campos vacios ";
                    IR = MODIFICAR;
                } else {
                    if (cman.consultarExistencia(nombreCliente) == 0
                            && !nombreCliente.equals(cman.consultarId(idCliente).getNombreCliente())) {
                        error = "ya existe un Cliente registrado como (" + nombreCliente + ")";
                        IR = MODIFICAR;
                    } else {
                        if (email.contains("@")) {
                            cman.modificar(idCliente, nombreCliente, apellidoCliente, direccionCliente, fechaNacimiento, telefono, email);
                            msg = "Cliente " + nombreCliente + " modificado";
                            bean.setListaCliente(cman.consultarTodos());
                            IR = LISTA;
                        } else {
                            error = "el correo debe contener un \"@\"";
                            IR = MODIFICAR;
                        }
                    }
                }
            }
        

        if (action.equalsIgnoreCase("eliminar")) {
            nombreCliente = cman.consultarId(idCliente).getNombreCliente();
            int v = cman.eliminar(idCliente);
            if (v == 1) {
                msg = "Cliente " + nombreCliente + " eliminado";
            } else {
                error = "Cliente (" + nombreCliente + ") no puede eliminarse, esta siendo utilizado en otra area";
            }
            List<Cliente> lc = cman.consultarTodos();
            if (lc.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaCliente(lc);
            }
            IR = LISTA;
        }

        if (action.equalsIgnoreCase("detalle")) {
            c = cman.consultarId(idCliente);
            bean.setIdCliente(c.getIdCliente());
            bean.setNombreCliente(c.getNombreCliente());
            bean.setApellidoCliente(c.getApellidoCliente());
            bean.setDireccionCliente(c.getDireccionCliente());
            bean.setFechaNacimiento(c.getFechaNacimiento());
            bean.setTelefono(c.getTelefono());
            bean.setEmail(c.getEmail());
            IR = MODIFICAR;
        }

        if (action.equalsIgnoreCase("consultar")) {
            List<Cliente> lc = cman.consultarTodos();
            if (lc.isEmpty()) {
                info = "La lista está vacia ";
            } else {
                bean.setListaCliente(lc);
            }
            IR = LISTA;
        }

        request.setAttribute("error", error);
        request.setAttribute("info", info);
        request.setAttribute("mensaje", msg);
        return mapping.findForward(IR);
    }
}
