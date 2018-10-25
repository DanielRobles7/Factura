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
import persistencia.Producto;

/**
 *
 * @author DanyDanny
 */
public class ProductoMan {
    
    public int guardar(
            String nombre,
            Double precio,
            int stock,
            Integer idCategoria
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Producto pro = new Producto();

        pro.setNombre(nombre);
        pro.setPrecio(precio);
        pro.setStock(stock);
        //--------------------
        Categoria cat = new Categoria();
        cat.setIdCategoria(idCategoria);
        pro.setCategoria(cat);
        
        try {
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Guardado Correcto Producto man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en guardar Producto man " + e);
        } finally {
            session.close();
        }
        return flag;
    }

    public int modificar(
            Integer Producto,
            String nombre,
            Double precio,
            int stock,
            Integer idCategoria
    ) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Producto pro = new Producto();

        pro.setIdProducto(Producto);
        pro.setNombre(nombre);
        pro.setPrecio(precio);
        pro.setStock(stock);
        //--------------------
        Categoria cat = new Categoria();
        cat.setIdCategoria(idCategoria);
        pro.setCategoria(cat);
        
        try {
            session.beginTransaction();
            session.update(pro);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Modificado Correcto Producto man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Modificado Producto man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public int eliminar(Integer idProducto) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        int flag = 0;
        Producto pro;
        
        try {
            session.beginTransaction();
            pro = (Producto) session.get(Producto.class, idProducto);
            session.delete(pro);
            session.getTransaction().commit();
            flag = 1;
            System.out.println("Eliminado Correcto Producto man");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                flag = 0;
            }
            System.out.println("Error en Eliminar Producto man " + e);
        } finally {
            session.close();
        }
        return flag;
    }
    public List consultarTodos() {
        List<Producto> listaProducto = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        try {
            Query q = session.createQuery("from Producto");
            listaProducto = (List<Producto>) q.list();
            System.out.println("consultarTodo Correcto Producto man");
        } catch (Exception e) {
            System.out.println("Error en consultarTodo Producto man " + e);
        } finally {
             //session.close();           
        }
        return listaProducto;
    }
    public Producto consultarId(Integer idProducto) {
        Producto cat = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();
            cat = (Producto) session.get(Producto.class, idProducto);
            System.out.println("Consultar por Id Correcto Producto man");
                        session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println("error en consulta por id Producto. " + e);
                cat = null;
            }
        } finally {
            session.close();
        }
        return cat;
    }
}
