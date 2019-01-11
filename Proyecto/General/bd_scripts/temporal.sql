/*************
* tau09_persona
**************/
insert into tau09_persona(id_persona, nb_persona, tx_primer_ap, tx_segundo_ap, by_foto) 
	values(nextval('tau09_persona_seq'), 'Francisco Isidoro', 'Mera', 'Torres', NULL);
insert into tau09_persona(id_persona, nb_persona, tx_primer_ap, tx_segundo_ap, by_foto) 
	values(nextval('tau09_persona_seq'), 'Diana Laura', 'Mejía', 'Mendoza', NULL);
insert into tau09_persona(id_persona, nb_persona, tx_primer_ap, tx_segundo_ap, by_foto) 
	values(nextval('tau09_persona_seq'), 'Eliot Uriel', 'Cedillo', 'Vázquez', NULL);
insert into tau09_persona(id_persona, nb_persona, tx_primer_ap, tx_segundo_ap, by_foto) 
	values(nextval('tau09_persona_seq'), 'Fernando Alonso', 'Martínez', 'Calderon', NULL);
insert into tau09_persona(id_persona, nb_persona, tx_primer_ap, tx_segundo_ap, by_foto) 
	values(nextval('tau09_persona_seq'), 'Eric Alejandro', 'López', '', NULL);

/*************
* tau01_cuenta
**************/
insert into tau01_cuenta(id_cuenta, id_estado, nb_usuario, tx_correo, tx_contrasena, fh_creacion)
	values(1, 1, 'fcoescom', 'fcomera.ipn.mx@gmail.com', 'prueba123', CURRENT_DATE);
insert into tau01_cuenta(id_cuenta, id_estado, nb_usuario, tx_correo, tx_contrasena, fh_creacion)
	values(2, 1, 'dianam', 'fcomera.ipn.mx@gmail.com', 'prueba123', CURRENT_DATE);
insert into tau01_cuenta(id_cuenta, id_estado, nb_usuario, tx_correo, tx_contrasena, fh_creacion)
	values(3, 1, 'eliote', 'fcomera.ipn.mx@gmail.com', 'prueba123', CURRENT_DATE);
insert into tau01_cuenta(id_cuenta, id_estado, nb_usuario, tx_correo, tx_contrasena, fh_creacion)
	values(4, 1, 'fernandoa', 'fcomera.ipn.mx@gmail.com', 'prueba123', CURRENT_DATE);
insert into tau01_cuenta(id_cuenta, id_estado, nb_usuario, tx_correo, tx_contrasena, fh_creacion)
	values(5, 1, 'erica', 'fcomera.ipn.mx@gmail.com', 'prueba123', CURRENT_DATE);

/*************
* tau10_acceso
**************/
insert into tau10_acceso(id_acceso, fh_ultimo_acceso, nu_intentos, st_sesion_activa)
	values(1, null, 0, 0);
insert into tau10_acceso(id_acceso, fh_ultimo_acceso, nu_intentos, st_sesion_activa)
	values(2, null, 0, 0);
insert into tau10_acceso(id_acceso, fh_ultimo_acceso, nu_intentos, st_sesion_activa)
	values(3, null, 0, 0);
insert into tau10_acceso(id_acceso, fh_ultimo_acceso, nu_intentos, st_sesion_activa)
	values(4, null, 0, 0);
insert into tau10_acceso(id_acceso, fh_ultimo_acceso, nu_intentos, st_sesion_activa)
	values(5, null, 0, 0);

/*************
* tau04_rol_usuario
**************/
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(1, 1);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(1, 2);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(1, 3);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(1, 4);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(1, 5);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(2, 1);
insert into tau04_rol_cuenta(id_rol, id_cuenta) values(3, 2);



/*************
* tps04_dueno
**************/
insert into tps04_dueno(id_dueno) values(1);


/*************
* tps12_cafeteria
**************/
insert into tps12_cafeteria(id_cafeteria, id_dueno, nb_cafeteria)
	values(nextval('tps12_cafeteria_seq'),1, 'El Kioskito');
insert into tps12_cafeteria(id_cafeteria, id_dueno, nb_cafeteria)
	values(nextval('tps12_cafeteria_seq'),1, 'El Sinoalense');
/*************
* tps01_local
**************/
insert into tps01_local(id_local, id_estado, id_cafeteria, nb_local, nu_rating, nu_longitud, nu_latitud, tm_inicio, tm_fin, by_foto, tx_direccion)
	values(nextval('tps01_local_seq'), 2, 1, 'El Kioskito ESCOM', 3.1, 19.5040269, -99.1479615, '2018-11-05 07:00:00', '2018-11-09 20:00:00', null,'Unidad Profesional Adolfo Lòpez Mateos ZACATENCO, María Luisa Stampa Ortigoza, Unidad Profesional Zacatenco Lindavista, 07738 Gustavo A. Madero, Ciudad de México, México');
insert into tps01_local(id_local, id_estado, id_cafeteria, nb_local, nu_rating, nu_longitud, nu_latitud, tm_inicio, tm_fin, by_foto, tx_direccion)
	values(nextval('tps01_local_seq'), 2, 1, 'El Kioskito UPIICSA', 2.1, 19.396696, -99.094691, '2018-11-05 10:00:00', '2018-11-09 22:30:00', null,'UPIICSA, Te 950, Ex Ejido Magdalena Mixhuca, 08600 Iztacalco, Ciudad de México, México');
insert into tps01_local(id_local, id_estado, id_cafeteria, nb_local, nu_rating, nu_longitud, nu_latitud, tm_inicio, tm_fin, by_foto,tx_direccion)
	values(nextval('tps01_local_seq'), 2, 2, 'El Sinoalense EDO MEX', 1.5,  19.492331, -99.042378, '2018-11-05 10:00:00', '2018-11-09 23:11:00', null,'Valle de Toluca, Arboledas de Aragón, 57120 Ecatepec, Estado de México, México');
insert into tps01_local(id_local, id_estado, id_cafeteria, nb_local, nu_rating, nu_longitud, nu_latitud, tm_inicio, tm_fin, by_foto, tx_direccion)
	values(nextval('tps01_local_seq'), 2, 2, 'El Sinoalense GAM', 4.2, 19.459667, -99.091846, '2018-11-05 08:00:00', '2018-11-09 17:30:00', null,'Mercado San Juán de Aragón I, 2da. Cda. 517, 06979 Gustavo A. Madero, Ciudad de México, México');

/*************
* tps02_empleado
**************/
insert into tps02_empleado(id_empleado, id_local, st_responsable) 
	values(2, 1, 1);
insert into tps02_empleado(id_empleado, id_local, st_responsable) 
	values(3, 1, 0);
insert into tps02_empleado(id_empleado, id_local, st_responsable) 
	values(4, 1, 0);
insert into tps02_empleado(id_empleado, id_local, st_responsable) 
	values(5, 1, 0);


/*************
* tps05_producto
**************/
insert into tps05_producto(id_producto , id_tipo , id_estado , id_cafeteria , nb_producto, tm_estimado_prep, nu_rating , tx_descripcion, by_foto)
	values(nextval('tps05_producto_seq'), 2, 2, 1, 'Hamburguesa de Pollo', 5.0, 4, 'Hamburguesa con queso amarrillo y oaxaca, sasonado con la salsa especial de la casa y pechuga de pollo.' ,NULL);
insert into tps05_producto(id_producto , id_tipo , id_estado , id_cafeteria , nb_producto, tm_estimado_prep, nu_rating , tx_descripcion, by_foto)
	values(nextval('tps05_producto_seq'), 2, 2, 1, 'Chapata de Milanesa', 10.0, 3, 'Chapata con mayonesa, ensalada y jitomate.',NULL);
insert into tps05_producto(id_producto , id_tipo , id_estado , id_cafeteria , nb_producto, tm_estimado_prep, nu_rating , tx_descripcion, by_foto)
	values(nextval('tps05_producto_seq'), 1, 2, 1 , 'Coca Cola de 600 ml.',null, 1, 'Botella de Coca Cola de 600ml.',NULL);
insert into tps05_producto(id_producto , id_tipo , id_estado , id_cafeteria , nb_producto, tm_estimado_prep, nu_rating , tx_descripcion, by_foto)
	values(nextval('tps05_producto_seq'), 3, 2, 1, 'Paquete 1', 15.0, 4, 'Hamburguesa de Pollo con Coca Cola de 600ml',NULL);

--Productos en promocion

/*************
* tps07_paquete
**************/
insert into tps07_paquete(id_paquete)
	values(4);



/*************
* tps09_producto_local
**************/
insert into tps09_producto_local(id_local, id_producto, id_estado, nu_precio_venta)
	values(1, 1, 1, 20.00);
insert into tps09_producto_local(id_local, id_producto, id_estado, nu_precio_venta)
	values(1, 2, 1, 30.50);
insert into tps09_producto_local(id_local, id_producto, id_estado, nu_precio_venta)
	values(1, 3, 1, 10.00);
insert into tps09_producto_local(id_local, id_producto, id_estado, nu_precio_venta)
	values(1, 4, 1, 30.00);

/*************
* tps10_paquete_producto
**************/
insert into tps10_paquete_producto(id_paquete, id_local, id_producto ,st_aplica_prom)
	values(4, 1, 1, 0);
insert into tps10_paquete_producto(id_paquete, id_local, id_producto, st_aplica_prom)
	values(4, 1, 2, 0);


/*************
* tps11_disponibilidad
**************/
insert into tps11_disponibilidad(id_disponibilidad, id_local, id_producto, tm_fecha_ini, nu_existencia_ini, nu_existencia_ac, tm_fecha_ag)
	values(nextval('tps11_disponibilidad_seq'),1, 1, CURRENT_DATE, 30, 30, NULL);
insert into tps11_disponibilidad(id_disponibilidad, id_local, id_producto, tm_fecha_ini, nu_existencia_ini, nu_existencia_ac, tm_fecha_ag)
	values(nextval('tps11_disponibilidad_seq'),1, 2, CURRENT_DATE, 30, 30, NULL);
insert into tps11_disponibilidad(id_disponibilidad, id_local, id_producto, tm_fecha_ini, nu_existencia_ini, nu_existencia_ac, tm_fecha_ag)
	values(nextval('tps11_disponibilidad_seq'),1, 3, CURRENT_DATE, 30, 30, NULL);
insert into tps11_disponibilidad(id_disponibilidad, id_local, id_producto, tm_fecha_ini, nu_existencia_ini, nu_existencia_ac, tm_fecha_ag)
	values(nextval('tps11_disponibilidad_seq'),1, 4, CURRENT_DATE, 30, 30, NULL);	


