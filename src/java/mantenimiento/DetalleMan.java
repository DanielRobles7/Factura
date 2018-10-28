/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import com.myapp.struts.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.Detalle;
import persistencia.Factura;
import persistencia.Producto;

/**
 *
 * @author DanyDanny
 */
public class DetalleMan {
    
    public int guardar(
            Integer idFactura,
            Integer idProducto,
            int cantidad,
            Double precio,
            Double total
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Detalle det = new Detalle();

        //----------------------------
        Factura fac = new Factura();
        fac.setIdFactura(idFactura);
        det.setFactura(fac);
        //----------------------------
        Producto pro = new Producto();
        pro.setIdProducto(idProducto);
        det.setProducto(pro);
        //----------------------------
        det.setCantidad(cantidad);
        det.setPrecio(precio);
        det.setTotal(total);
        
        try {
            session.beginTransaction();
            session.save(det);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto Detalle man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Detalle man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

     public int modificar(
             Integer idDetalle,
            Integer idFactura,
            Integer idProducto,
            int cantidad,
            Double precio,
            Double total
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Detalle det = new Detalle();

        det.setIdDetalle(idDetalle);
        //----------------------------
        Factura fac = new Factura();
        fac.setIdFactura(idFactura);
        det.setFactura(fac);
        //----------------------------
        Producto pro = new Producto();
        pro.setIdProducto(idProducto);
        det.setProducto(pro);
        //----------------------------
        det.setCantidad(cantidad);
        det.setPrecio(precio);
        det.setTotal(total);
        
        try {
            session.beginTransaction();
            session.update(det);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto Detalle man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Detalle man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
     
    public int eliminar(Integer idDetalle) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Detalle det;
        
        try {
            session.beginTransaction();
            det = (Detalle) session.get(Detalle.class, idDetalle);
            session.delete(det);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto Detalle man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar Detalle man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public List consultarTodos() {
        List<Detalle> listaDetalle = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Detalle");
            listaDetalle = (List<Detalle>) q.list();
            System.out.println("consultarTodo Correcto Detalle man");
        } catch (Exception e) {
            System.out.println("Error en consultarTodo Detalle man " + e);
        } finally {
             //session.close();           
        }
        return listaDetalle;
    }
    public Detalle consultarId(Integer idDetalle) {
        Detalle det = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            det = (Detalle) session.get(Detalle.class, idDetalle);
            System.out.println("Consultar por Id Correcto Detalle man");
                        session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id Detalle. " + e);
                det = null;
            }
        } finally {
            session.close();
        }
        return det;
    }
    public List consultaDetalleEspecifico (Integer idFactura){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Detalle f where f.factura.idFactura=:idFactura");
            q.setParameter("idFactura", idFactura);
            List<Detalle> list = q.list();
            if (list.size()>0) {
                return list;
            }
            session.close();
            return null;
        } catch (HibernateException e) {
            session.close();
            System.out.println("Error en consultar Detalle "+e);
            return null;
        }
    }
    public static void main(String[] args) {
        DetalleMan dman = new DetalleMan();
        List<Detalle> ld = dman.consultaDetalleEspecifico(8);
        System.out.println("ver "+ld.toString());
    }
}
