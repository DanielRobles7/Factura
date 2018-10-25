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
import persistencia.Cliente;

/**
 *
 * @author DanyDanny
 */
public class ClienteMan {
    
    public int guardar(
            String nombreCliente,
            String apellidoCliente,
            String direccionCliente,
            String fechaNacimiento,
            String telefono,
            String email
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Cliente cli = new Cliente();
        
        cli.setNombreCliente(nombreCliente);
        cli.setApellidoCliente(apellidoCliente);
        cli.setDireccionCliente(direccionCliente);
        cli.setFechaNacimiento(fechaNacimiento);
        cli.setTelefono(telefono);
        cli.setEmail(email);
        
        try {
            session.beginTransaction();
            session.save(cli);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto Cliente man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Cliente man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int modificar(
            Integer idCliente,
            String nombreCliente,
            String apellidoCliente,
            String direccionCliente,
            String fechaNacimiento,
            String telefono,
            String email
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Cliente cli = new Cliente();
        
        cli.setIdCliente(idCliente);
        cli.setNombreCliente(nombreCliente);
        cli.setApellidoCliente(apellidoCliente);
        cli.setDireccionCliente(direccionCliente);
        cli.setFechaNacimiento(fechaNacimiento);
        cli.setTelefono(telefono);
        cli.setEmail(email);
        
        try {
            session.beginTransaction();
            session.update(cli);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto Cliente man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Modificar Cliente man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    
    public int eliminar(Integer idCliente) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Cliente cli;
        
        try {
            session.beginTransaction();
            cli = (Cliente) session.get(Cliente.class, idCliente);
            session.delete(cli);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto Cliente man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar Cliente man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public List consultarTodos() {
        List<Cliente> listaCliente = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Cliente");
            listaCliente = (List<Cliente>) q.list();
            System.out.println("consultarTodo Correcto Cliente man");
        } catch (Exception e) {
            System.out.println("Error en consultarTodo Cliente man " + e);
        } finally {
             //session.close();           
        }
        return listaCliente;
    }
    public Cliente consultarId(Integer idCliente) {
        Cliente cli = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            cli = (Cliente) session.get(Cliente.class, idCliente);
            System.out.println("Consultar por Id Correcto Cliente man");
                        session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id Cliente. " + e);
                cli = null;
            }
        } finally {
            session.close();
        }
        return cli;
    }

}
