/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionForm;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import persistencia.ModoPago;

/**
 *
 * @author DanyDanny
 */
public class ModoPagoActionForm extends org.apache.struts.action.ActionForm {
    
    Integer idPago;
    String nombreModoPago;
    String otrosDetalles;
    List<ModoPago> listaModoPago;
    String action;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public String getNombreModoPago() {
        return nombreModoPago;
    }

    public void setNombreModoPago(String nombreModoPago) {
        this.nombreModoPago = nombreModoPago;
    }

    public String getOtrosDetalles() {
        return otrosDetalles;
    }

    public void setOtrosDetalles(String otrosDetalles) {
        this.otrosDetalles = otrosDetalles;
    }

    public List<ModoPago> getListaModoPago() {
        return listaModoPago;
    }

    public void setListaModoPago(List<ModoPago> listaModoPago) {
        this.listaModoPago = listaModoPago;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
       /* if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }*/
        return errors;
    }
}
