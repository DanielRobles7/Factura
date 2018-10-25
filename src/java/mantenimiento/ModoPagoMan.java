/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimiento;

import com.myapp.struts.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.ModoPago;

/**
 *
 * @author DanyDanny
 */
public class ModoPagoMan {
    
    public int guardar(
            String nombreModoPago,
            String otrosDetalles
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        ModoPago mod = new ModoPago();

        mod.setNombreModoPago(nombreModoPago);
        mod.setOtrosDetalles(otrosDetalles);
        try {
            session.beginTransaction();
            session.save(mod);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto ModoPago man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar ModoPago man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int modificar(
            Integer idModoPago,
            String nombreModoPago,
            String otrosDetalles
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        ModoPago mod = new ModoPago();

        mod.setIdPago(flag);
        mod.setNombreModoPago(nombreModoPago);
        mod.setOtrosDetalles(otrosDetalles);
        try {
            session.beginTransaction();
            session.update(mod);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto ModoPago man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Modificar ModoPago man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public int eliminar(Integer idModoPago) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        ModoPago mod;
        
        try {
            session.beginTransaction();
            mod = (ModoPago) session.get(ModoPago.class, idModoPago);
            session.delete(mod);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto ModoPago man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar ModoPago man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public List consultarTodos() {
        List<ModoPago> listaModoPago = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from ModoPago");
            listaModoPago = (List<ModoPago>) q.list();
            System.out.println("consultarTodo Correcto ModoPago man");
        } catch (Exception e) {
            System.out.println("Error en consultarTodo ModoPago man " + e);
        } finally {
             //session.close();           
        }
        return listaModoPago;
    }
    public ModoPago consultarId(Integer idModoPago) {
        ModoPago cat = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            cat = (ModoPago) session.get(ModoPago.class, idModoPago);
            System.out.println("Consultar por Id Correcto ModoPago man");
                        session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id ModoPago. " + e);
                cat = null;
            }
        } finally {
            session.close();
        }
        return cat;
    }
}
