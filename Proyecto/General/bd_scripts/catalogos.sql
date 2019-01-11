/********************
*	tau03_rol
********************/
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Usuario','');
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Jefe', '');
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Responsable de Local', '');
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Cajero','');
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Cocinero', '');
insert into tau03_rol(id_rol, nb_rol, ds_rol) values (nextval('tau03_rol_seq'), 'Cliente', '');

/********************
*	tau02_estado_usuario
********************/
insert into tau02_estado_cuenta(id_estado, nb_estado) values(1, 'Activo');
insert into tau02_estado_cuenta(id_estado, nb_estado) values(2, 'Bloqueado');

/********************
*	tau05_metodo
********************/
insert into tau05_metodo(id_metodo, nb_metodo) values (1, 'GET');
insert into tau05_metodo(id_metodo, nb_metodo) values (2, 'POST');
insert into tau05_metodo(id_metodo, nb_metodo) values (3, 'UPDATE');
insert into tau05_metodo(id_metodo, nb_metodo) values (4, 'DELETE');

/********************
*	tau08_recurso
********************/
insert into tau08_recurso(id_recurso, nb_recurso, tx_uri) 
	values(nextval('tau08_recurso_seq'), 'A','backend/login');
insert into tau08_recurso(id_recurso, nb_recurso, tx_uri) 
	values(nextval('tau08_recurso_seq'), 'B','backend/addUser');
insert into tau08_recurso(id_recurso, nb_recurso, tx_uri) 
	values(nextval('tau08_recurso_seq'), 'C','backend/recoverUser');


/********************
*	tau06_metodo_recurso
********************/
insert into tau06_metodo_recurso(id_metodo, id_recurso) values (2 , 1);
insert into tau06_metodo_recurso(id_metodo, id_recurso) values (2 , 2);
insert into tau06_metodo_recurso(id_metodo, id_recurso) values (2 , 3);


/********************
*	tau07_rol_recurso
********************/
insert into tau07_rol_recurso(id_rol, id_metodo, id_recurso) values(1 , 2, 1);
insert into tau07_rol_recurso(id_rol, id_metodo, id_recurso) values(1 , 2, 2);
insert into tau07_rol_recurso(id_rol, id_metodo, id_recurso) values(1 , 2, 3);


/********************
*	tps06_tipo_producto
********************/
insert into tps06_tipo_producto(id_tipo, nb_tipo) values(1, 'Abarrotes');
insert into tps06_tipo_producto(id_tipo, nb_tipo) values(2, 'Cocina');
insert into tps06_tipo_producto(id_tipo, nb_tipo) values(3, 'Mixto');	


/********************
*	tps09_estado_local
********************/
insert into tps09_estado_local(id_estado, nb_estado) values(1, 'Registrado');
insert into tps09_estado_local(id_estado, nb_estado) values(2, 'Publicado');
insert into tps09_estado_local(id_estado, nb_estado) values(3, 'Fuera de Operación');
insert into tps09_estado_local(id_estado, nb_estado) values(4, 'Cerrado');
insert into tps09_estado_local(id_estado, nb_estado) values(5, 'Eliminado');

/********************
*	tps10_estado_producto
********************/
insert into tps10_estado_producto(id_estado, nb_estado) values(1, 'Registrado');
insert into tps10_estado_producto(id_estado, nb_estado) values(2, 'Publicado');
insert into tps10_estado_producto(id_estado, nb_estado) values(3, 'Eliminado');

/********************
*	tps11_estado_producto_local
********************/
insert into tps12_estado_producto_local(id_estado, nb_estado) values(1, 'Disponible');
insert into tps12_estado_producto_local(id_estado, nb_estado) values(2, 'Agotado');


/********************
*	tpd03_estado_orden
********************/
insert into tpd03_estado_orden(id_estado, nb_estado) values(1, 'Sin Confirmar');
insert into tpd03_estado_orden(id_estado, nb_estado) values(2, 'Confirmado');
insert into tpd03_estado_orden(id_estado, nb_estado) values(3, 'Cancelado');


/********************
*	tpd08_tipo_orden
********************/
insert into tpd08_tipo_orden(id_tipo, nb_tipo) values(1, 'No Programada');
insert into tpd08_tipo_orden(id_tipo, nb_tipo) values(2, 'Programada');


/********************
*	tpg02_tipo_forma
********************/
insert into tpg02_tipo_forma(id_tipo, nb_tipo) values(1, 'Efectivo');
insert into tpg02_tipo_forma(id_tipo, nb_tipo) values(2, 'Tarjeta');
insert into tpg02_tipo_forma(id_tipo, nb_tipo) values(3, 'Paypal');

/********************
*	tpg02_estado_forma_pago
********************/
insert into tpg07_estado_forma_pago(id_estado, nb_estado) values (1, 'Registrado');
insert into tpg07_estado_forma_pago(id_estado, nb_estado) values (2, 'Eliminado');


