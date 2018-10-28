package metodos;

import java.util.List;
import mantenimiento.DetalleMan;
import persistencia.Detalle;


/**
 *
 * @author DanyDanny
 */
public class SumarTotalFactura {

    public Double sumarTotalFactura(int idFacturaEncabezado) {
        //suma la columna de "TotalFila" dentro de Factura Detalle tras recibir un id de Factura Encabezado.
        DetalleMan dman = new DetalleMan();
        double suma = 0.0;

        try {
            List<Detalle> listaDetalle = dman.consultaDetalleEspecifico(idFacturaEncabezado);
            //System.out.println("lista "+listaFactura.toString());
            //System.out.println("ver "+listaFactura.size());
            if (listaDetalle.size() > 0) {
                for (int i = 0; i < listaDetalle.size(); i++) {
                    suma += listaDetalle.get(i).getTotal();
                }
            }
        } catch (Exception e) {
            System.out.println("error en el catch de sumar total fila "+e);
            suma = 0.0;
        }
        return suma;
    }
    
   
}
