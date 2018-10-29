/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import com.myapp.struts.HibernateUtil;
import java.util.List;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.Cliente;
import persistencia.Factura;
import persistencia.ModoPago;

/**
 *
 * @author DanyDanny
 */
public class FacturaMan {

    public int guardar(
            Integer idCliente,
            String fechaFactura,
            Integer idPago,
            Double totalFactura
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Factura fac = new Factura();

        //----------------------------
        Cliente cli = new Cliente();
        cli.setIdCliente(idCliente);
        fac.setCliente(cli);
        //----------------------------
        fac.setFechaFactura(fechaFactura);
        fac.setTotalFactura(totalFactura);
        //----------------------------
        ModoPago mod = new ModoPago();
        mod.setIdPago(idPago);
        fac.setModoPago(mod);

        try {
            session.beginTransaction();
            session.save(fac);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto Factura man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Factura man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int modificar(
            Integer idFactura,
            Integer idCliente,
            String fechaFactura,
            Integer idPago,
            Double totalFactura
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Factura fac = new Factura();

        fac.setIdFactura(idFactura);
        //----------------------------
        Cliente cli = new Cliente();
        cli.setIdCliente(idCliente);
        fac.setCliente(cli);
        //----------------------------
        fac.setFechaFactura(fechaFactura);
        fac.setTotalFactura(totalFactura);
        //----------------------------
        ModoPago mod = new ModoPago();
        mod.setIdPago(idPago);
        fac.setModoPago(mod);

        try {
            session.beginTransaction();
            session.update(fac);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto Factura man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Modificado Factura man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int eliminar(Integer idFactura) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Factura fac;

        try {
            session.beginTransaction();
            fac = (Factura) session.get(Factura.class, idFactura);
            session.delete(fac);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto Factura man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar Factura man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public List consultarTodos() {
        List<Factura> listaFactura = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Factura");
            listaFactura = (List<Factura>) q.list();
            System.out.println("consultarTodo Correcto Factura man");
        } catch (Exception e) {
            System.out.println("Error en consultarTodo Factura man " + e);
        } finally {
            //session.close();           
        }
        return listaFactura;
    }

    public Factura consultarId(Integer idFactura) {
        Factura fac = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            fac = (Factura) session.get(Factura.class, idFactura);
            System.out.println("Consultar por Id Correcto Factura man");
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id Factura. " + e);
                fac = null;
            }
        } finally {
            session.close();
        }
        return fac;
    }

    public List maxFactura() {
        List<Factura> listaI = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("select max(idFactura) from Factura");
            listaI = (List<Factura>) q.list();
            System.out.println(listaI.get(0));
            System.out.println("Consultar max Factura Correcto");
        } catch (Exception e) {
            System.out.println("Error consultar max id Factura. " + e);
        } finally {
            session.close();
        }

        return listaI;
    }

    public int maxIdFactura() {
        FacturaMan fmam = new FacturaMan();
        int idFactura = 0;
        List<Factura> fe = fmam.consultarTodos();
        if (fe.size() > 0) {
            List<Integer> lista = fmam.maxFactura();
            idFactura = lista.get(0);
        }
        return idFactura;
    }
public int call() {
    StoredProcedureQuery query = 
    
                .createStoredProcedureQuery("count_comments")
                .registerStoredProcedureParameter(
                        "postId", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "commentCount", Long.class, ParameterMode.OUT)
                .setParameter("postId", 1L);

    query.execute ();

    Long commentCount = (Long) query
            .getOutputParameterValue("commentCount");
}
}
f where f.factura.idFactura=:idFactura