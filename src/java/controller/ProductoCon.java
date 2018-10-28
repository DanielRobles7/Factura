/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.CategoriaMan;

/**
 *
 * @author DanyDanny
 */
public class ProductoCon extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "nuevo":
                    this.nuevo(request, response);
                    break;
                case "guardar":
                    this.guardar(request, response);
                    break;
                case "modificar":
                    this.modificar(request, response);
                    break;
                case "eliminar":
                    this.eliminar(request, response);
                    break;
                case "detalle":
                    this.detalle(request, response);
                    break;
                case "consultar":
                    this.consultar(request, response);
                    break;

            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en switch " + e);
        }
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void modificar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        CategoriaMan cman = new CategoriaMan();
        String nombre = cman.consultarId(idCategoria).getNombre();
        int v = cman.eliminar(idCategoria);
        String msg = "";
        if (v>0) {
            msg = "Registro "+nombre+" eliminado ";
        }else {
            msg = "Hubo un error, el registro "+nombre+" no se eliminó";
        }
        RequestDispatcher rd;
        request.setAttribute("mensaje", msg);
        request.setAttribute("listaCategoria", cman.consultarTodos());
        rd = request.getRequestDispatcher("/jsp/categoria/lista.jsp");
        
        
    }

    protected void detalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaMan cman = new CategoriaMan();
        RequestDispatcher rd;
        request.setAttribute("listaCategoria", cman.consultarTodos());
        rd = request.getRequestDispatcher("/jsp/categoria/lista.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
