<%-- 
    Document   : guardar
    Created on : 26-oct-2018, 22:32:09
    Author     : DanyDanny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardar Nuevo Cliente</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="http://codeseven.github.com/toastr/toastr.js"></script>
        <link href="http://codeseven.github.com/toastr/toastr.css" rel="stylesheet"/>
        <link href="http://codeseven.github.com/toastr/toastr-responsive.css" rel="stylesheet"/>
    </head>
    <body style="background: #ABABAB; min-height: 700px">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div> 
                        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #000; padding: 15px">
                            <a class="navbar-brand" href="inicio.jsp" style = 'color: white'>INICIO</a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <div class="collapse navbar-collapse" id="navbarSupportedContent" >
                                <ul class="navbar-nav mr-auto" >

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
                                           aria-haspopup="true" aria-expanded="false" style = 'color: white'> 
                                            Clientes
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown" >
                                            <a class="dropdown-item" href="cliente.do?action=nuevo">Agregar Cliente</a>
                                            <a class="dropdown-item" href="cliente.do?action=Consultar">Consultar Clientes</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
                                           aria-haspopup="true" aria-expanded="false" style = 'color: white'> 
                                            Facturas
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown" >
                                            <a class="dropdown-item" href="factura.do?action=nuevo">Agregar Factura</a>
                                            <a class="dropdown-item" href="factura.do?action=Consultar">Consultar Facturas</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
                                           aria-haspopup="true" aria-expanded="false" style = 'color: white'> 
                                            Productos
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown" >
                                            <a class="dropdown-item" href="producto.do?action=nuevo">Agregar Producto</a>
                                            <a class="dropdown-item" href="producto.do?action=Consultar">Consultar Productos</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
                                           aria-haspopup="true" aria-expanded="false" style = 'color: white'> 
                                            Consultas
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown" >
                                            <a class="dropdown-item" href="cliente.do?action=Consultar">Clientes</a>
                                            <a class="dropdown-item" href="factura.do?action=Consultar">Facturas</a>
                                            <a class="dropdown-item" href="producto.do?action=Consultar">Productos</a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center" style="padding: 10px; font-weight: bold;">Guardar Nuevo Cliente</h1>
                    <html:form>
                        <div class="card-header" style="background-color: #DDDDDD; border-color: black">
                            <div class="row">
                                <div class="form-group col-6">
                                    <div>
                                        <label style="font-weight: bold;">Nombre : </label>
                                        <html:text property="nombreCliente" styleClass="form-control" style=" border-color: gray"  ></html:text>
                                            <br>
                                        </div>
                                        <div>
                                            <label style="font-weight: bold; align-items: center">Apellido : </label>
                                        <html:text property="apellidoCliente" styleClass="form-control" style=" border-color: gray"  ></html:text>
                                            <br>
                                        </div>
                                        <div>
                                            <label style="font-weight: bold; align-items: center">Fecha de Nacimiento : (dd/MM/aaaa)</label>
                                        <html:text property="fechaNacimiento" styleClass="form-control" style="border-color: gray "></html:text>
                                            <br>
                                        </div>
                                    </div>
                                    <div class="form-group col-6">
                                        <div>
                                            <label style="font-weight: bold; align-items: center">Dirección : </label>
                                        <html:text property="direccionCliente" styleClass="form-control" style="border-color: gray "></html:text>
                                            <br>
                                        </div>
                                        <div>
                                            <label style="font-weight: bold; align-items: center" >Teléfono : (0000-0000)</label>
                                        <html:text property="telefono" styleClass="form-control" style="border-color: gray "></html:text>
                                            <br>
                                        </div>
                                        <div>
                                            <label style="font-weight: bold; align-items: center">E-Mail : </label>
                                        <html:text property="email" styleClass="form-control" style="border-color: gray "></html:text>
                                            <br>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="form-group col-12">
                                        <div class="text-center">
                                        <html:submit property="action" value="Guardar" styleClass="btn btn-outline-dark" style="width: 100px"></html:submit>
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </html:form>
                </div>
            </div>
        </div>
        <div id="mensaje" hidden="hidden">${mensaje}</div>
        <div id="error" hidden="hidden">${error}</div>
        <script type="text/javascript">
            window.onload = function () {
                if ($("#error").text() !== "") {
                    error();
                }
                if ($("#mensaje").text() !== "") {
                    mensaje();
                }
            };
            toastr.options = {
                "debug": false,
                "onclick": null,
                "fadeIn": 300,
                "fadeOut": 100,
                "timeOut": 5000,
                "extendedTimeOut": 1000};
            var showToastrs = false;
            function error() {
                if (!showToastrs) {
                    toastr.error($("#error").text(), 'Error');
                }
            }
            function mensaje() {
                if (!showToastrs) {
                    toastr.success($("#mensaje").text(), 'Confirmacion');
                }
            }
        </script>
    </body>
</html>
