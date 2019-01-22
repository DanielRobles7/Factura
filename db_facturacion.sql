
/* procedimiento almacenado */
delimiter $ 
create procedure sp_insercion_factura (in cli int , fec varchar(20), pago int)
begin
insert into factura(id_cliente, fecha_factura, id_pago) values (cli, fec, pago);
end $

call sp_insercion_factura (1, "24/1/2018", 1);
/* --------------------------------------------------- */

/* trigger*/
delimiter \\
create trigger tr_disminuye_stoc after insert on detalle
for each row
begin
set@ncantidad=
(select cantidad from detalle where id_detalle=(select max(id_detalle) as id_detalle from detalle));
set@idproducto=
(select id_producto from detalle where id_detalle=(select max(id_detalle) as id_detalle from detalle));
set@nstock=
(select stock from producto where id_producto=@idproducto);
set@resultado=(@nstock-@ncantidad);
update facturacion.producto 
set nombre=nombre, precio=precio, stock=@resultado, id_categoria=id_categoria 
where id_producto=@idproducto;
end \\
delimiter ;

/* ---------------------------------- */
insert into detalle values(0,  1, 1, 3, 0.75);
/* ---------------------------------- */
DROP TRIGGER tr_disminuye_stoc;
/* ---------------------------------- */