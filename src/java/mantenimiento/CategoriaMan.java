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
import persistencia.Categoria;

/**
 *
 * @author DanyDanny
 */
public class CategoriaMan {

    public static void main(String[] args) {
        CategoriaMan cman = new CategoriaMan();

        Integer idCategoria = 3;
        String nombre = "higiene personal";
        String descripcion = "productos de higiene personal";
        
        //cman.guardar(nombre, descripcion);
        //cman.eliminar(idCategoria);
        //cman.modificar(idCategoria, nombre, descripcion);
        //cman.consultarId(idCategoria);
        //cman.consultarTodos();
        
        System.exit(0);

    }

    public int guardar(
            String nombre,
            String descripcion
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Categoria cat = new Categoria();

        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        try {
            session.beginTransaction();
            session.save(cat);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto Categoria man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Categoria man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int modificar(
            Integer idCategoria,
            String nombre,
            String descripcion
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Categoria cat = new Categoria();

        cat.setIdCategoria(idCategoria);
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        try {
            session.beginTransaction();
            session.update(cat);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto Categoria man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Modificar Categoria man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int eliminar(Integer idCategoria) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Categoria cat;

        try {
            session.beginTransaction();
            cat = (Categoria) session.get(Categoria.class, idCategoria);
            session.delete(cat);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto Categoria man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar Categoria man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public List consultarTodos() {
        List<Categoria> listaCategoria = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Categoria");
            listaCategoria = (List<Categoria>) q.list();
            System.out.println("consultarTodo Correcto Categoria man "+listaCategoria);
        } catch (Exception e) {
            System.out.println("Error en consultarTodo Categoria man " + e);
        } finally {
            //session.close();           
        }
        return listaCategoria;
    }

    public Categoria consultarId(Integer idCategoria) {
        Categoria cat = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            cat = (Categoria) session.get(Categoria.class, idCategoria);
            System.out.println("Consultar por Id Correcto Categoria man "+cat);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id Categoria. " + e);
                cat = null;
            }
        } finally {
            session.close();
        }
        return cat;
    }
}
