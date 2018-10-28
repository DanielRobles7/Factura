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
        <title>Modificar Factura</title>
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
                    <h1 class="text-center" style="padding: 10px; font-weight: bold;">Modificar Factura</h1>
                    <html:form>
                        <div class="card-header" style="background-color:#DDDDDD;">
                            <div class="row">
                                <div class="col-9">
                                    <html:hidden property="idFactura"></html:hidden>
                                        <div class="row">
                                            <div class="col-9">
                                                <h2 class="text-center">Empresa ABC, S.A. de C.V.</h2>
                                                <h6 class="text-center">Calle xyz, Av. mnñ, #123, San Salvador</h6>
                                            </div>
                                            <div class="col-3">
                                                <div class="text-left"><label >Modo de Pago</label></div>
                                            <html:select property="idPago" styleClass="form-control">
                                                <html:option value="Seleccionar"></html:option>
                                                <logic:notEmpty name="FacturaActionForm" property="listaModoPago">
                                                    <logic:iterate id="ver" name="FacturaActionForm" property="listaModoPago">
                                                        <html:option value="${ver.idPago}" >${ver.nombreModoPago}</html:option>
                                                    </logic:iterate>
                                                </logic:notEmpty>
                                            </html:select>
                                        </div>
                                    </div>
                                    <div class="text-left"><label >Cliente</label></div>
                                    <html:select property="idCliente" styleClass="form-control">
                                        <html:option value="Seleccionar"></html:option>
                                        <logic:notEmpty name="FacturaActionForm" property="listaCliente">
                                            <logic:iterate id="ver" name="FacturaActionForm" property="listaCliente">
                                                <html:option value="${ver.idCliente}" >${ver.nombreCliente}</html:option>
                                            </logic:iterate>
                                        </logic:notEmpty>
                                    </html:select>
                                </div>
                                <div class="col-3">
                                    <div class="text-left"><label>Numero de Factura : </label></div>
                                    <h2 class="text-center" style="color: red">00000${num}</h2>
                                    <div class="text-left"><label >Fecha : </label></div>
                                    <html:text property="fechaFactura" styleClass="form-control"></html:text>
                                    </div>
                                </div>    
                            </div>
                            <br>
                            <div class="card-header" style="background-color:#DDDDDD;">
                                <div class="row">
                                    <div class="col-10">
                                        <table>
                                            <thead>
                                                <tr style="width:100%">
                                                    <td style="width:700px">Producto</td>
                                                    <td>    </td>
                                                    <td style="width:400px">Cantidad</td>
                                                </tr>
                                            </thead>
                                            <tbody class="align-content-center">
                                                <tr>
                                                    <td>
                                                    <html:select property="idProducto" styleClass="form-control" >
                                                        <html:option value="Seleccionar"></html:option>
                                                        <logic:notEmpty name="FacturaActionForm" property="listaProducto">
                                                            <logic:iterate id="ver" name="FacturaActionForm" property="listaProducto">
                                                                <html:option value="${ver.idProducto}" >${ver.nombre}</html:option>
                                                            </logic:iterate>
                                                        </logic:notEmpty>
                                                    </html:select> 
                                                </td>
                                                <td>  </td>
                                                <td><html:text property="cantidad" styleClass="form-control"></html:text></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                    <div class="col-2">
                                        <br>
                                    <html:submit  property="action" value="Agregar" styleClass="btn  font-weight-bold " style="background-color:#000; color: white"></html:submit>
                                    </div>
                                </div>
                            </div>
                    </html:form>
                    <div class="card-header" style="background-color:#DDDDDD;">
                        <div class="row">
                            <div class="col-12">
                                <table class="table table-hover table-dark">
                                    <thead class="text-center">
                                        <tr class="table-secondary" style="color: black"> 
                                            <td scope="col" style="vertical-align: middle">Producto : </td>
                                            <td scope="col" style="vertical-align: middle">Cantidad : </td>
                                            <td scope="col" style="vertical-align: middle">Precio : </td>
                                            <td scope="col" style="vertical-align: middle">Total :</td>
                                            <td scope="col" style="vertical-align: middle"></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <logic:notEmpty name="FacturaActionForm" property="listaDetalle">
                                            <logic:iterate id="ver" name="FacturaActionForm" property="listaDetalle">
                                                <tr class="table-secondary text-center" style="color: black">
                                                    <html:form>
                                                        <td><bean:write name="ver" property="producto.nombre"></bean:write>
                                                            <html:hidden name="ver" property="idDetalle"></html:hidden></td>
                                                        <td style="vertical-align: middle"><bean:write name="ver" property="cantidad"></bean:write></td>
                                                        <td style="vertical-align: middle"><bean:write name="ver" property="producto.precio"></bean:write></td>
                                                        <td style="vertical-align: middle"><bean:write name="ver" property="total"></bean:write></td>
                                                        <td style="vertical-align: middle"><html:submit property="action" value="x "/></td>
                                                    </html:form>
                                                </tr>
                                            </logic:iterate>
                                        </logic:notEmpty>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="table-secondary text-center" style="padding: 10px;">
                        <div class="row">
                            <div class="col-5">
                            </div>
                            <div class="col-4 text-right">
                                <label>Total : </label>
                            </div>
                            <div class="col-3 " >
                                <label style="border-top: 1px black">${totalFactura}</label>
                            </div>
                        </div>
                    </div>
                    <div class="card-header text-center" style="background-color:#DDDDDD;">
                        <html:form>
                            <html:hidden property="idFactura"></html:hidden>
                            <html:submit property="action" value="Actualizar" styleClass="btn btn-outline-dark" style="width: 100px"></html:submit>
                        </html:form>   
                    </div>
                </div>
            </div>
        </div>
        <div id="mensaje" hidden="hidden">${mensaje}</div>
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
