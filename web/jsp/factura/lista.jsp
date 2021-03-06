<%-- 
    Document   : lista
    Created on : 26-oct-2018, 22:32:32
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
        <title>Lista de Facturas</title>
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
            <div class="row" >
                <div class="col-12">
                    <div class="row">
                        <div class="col-4" style="padding: 20px;">
                            <html:form>
                                <div class="row">
                                    <div class="col-8">
                                        <html:select property="idCliente" styleClass="form-control" style="width: 240px">
                                        <html:option value="Todas las Facturas"></html:option>
                                        <logic:notEmpty name="FacturaActionForm" property="listaCliente">
                                            <logic:iterate id="ver" name="FacturaActionForm" property="listaCliente">
                                                <html:option value="${ver.idCliente}" >${ver.nombreCliente}</html:option>
                                            </logic:iterate>
                                        </logic:notEmpty>
                                    </html:select>
                                    </div>
                                    <div class="col-4"><html:submit property="action" value="Consultar " styleClass="btn btn-outline-dark" style="width: 100px"></html:submit></div>
                                </div>
                            </html:form>
                        </div>
                        <div class="col-5"><h1 class="text-center" style="padding: 10px; font-weight: bold; ">Lista de Facturas</h1></div>
                        <div class="col-3">
                            <div class="text-right" style="padding: 20px"><a class="btn btn-outline-dark" href="modoPago.do?action=Consultar" style="width: 150px">Modos de Pago</a></div>
                        </div>
                    </div>
                    <table class="table table-hover table-dark">
                        <thead >
                            <tr class="text-center" >
                                <th scope="col" style="vertical-align: middle">ID</th>
                                <th scope="col" style="vertical-align: middle">Cliente</th>
                                <th scope="col" style="vertical-align: middle">Fecha</th>
                                <th scope="col" style="vertical-align: middle">Modo de Pago</th>
                                <th scope="col" style="vertical-align: middle">Total</th>
                                <th scope="col" style="vertical-align: middle"></th>
                                <th scope="col" style="vertical-align: middle">
                                    <html:form>
                                        <html:submit property="action" value="Nuevo" styleClass="btn font-weight-bold" style="background-color: secondary  ; color: black; width: 100px" />
                                    </html:form>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <logic:notEmpty name="FacturaActionForm" property="listaFactura">
                                <logic:iterate id="ver" name="FacturaActionForm" property="listaFactura">
                                    <tr class="table-secondary text-center" style="color: black">
                                        <html:form>
                                            <td class="text-center" scope="row" style="vertical-align: middle">
                                                <bean:write name="ver" property="idFactura" />
                                                <html:hidden name="ver" property="idFactura"/>
                                            </td>
                                            <td style="vertical-align: middle"><bean:write name="ver" property="cliente.nombreCliente" /></td>
                                            <td style="vertical-align: middle"><bean:write name="ver" property="fechaFactura" /></td>
                                            <td style="vertical-align: middle"><bean:write name="ver" property="modoPago.nombreModoPago" /></td>
                                            <td style="vertical-align: middle"><bean:write name="ver" property="totalFactura" /></td>
                                            <td class="text-center" style="vertical-align: middle">
                                                <html:submit property="action" value="Detalle" styleClass="btn font-weight-bold" style="background-color: #5D7EC9; color: white; width: 100px" />
                                            </td>
                                            <td class="text-center" style="vertical-align: middle">
                                                <html:submit property="action" value="Eliminar" styleClass="btn font-weight-bold" style="background-color: #C95D5D; color: white; width: 100px" />
                                            </td>
                                        </html:form>
                                    </tr>
                                </logic:iterate>
                            </logic:notEmpty>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="mensaje" hidden="hidden">${mensaje}</div>
        <div id="info" hidden="hidden" style="color:white">${info}</div>
        <div id="error" hidden="hidden" style="color:white">${error}</div>
        <script type="text/javascript">
            window.onload = function () {
                if ($("#error").text() !== "") {
                    error();
                }
                if ($("#mensaje").text() !== "") {
                    mensaje();
                }
                if ($("#info").text() !== "") {
                    info();
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
            function info() {
                if (!showToastrs) {
                    toastr.info($("#info").text(), 'Informacion');
                }
            }
        </script>
    </body>
</html>
