use tallerJava;
insert into gestion_cuenta values  (999, '2024-06-05 16:10:17.169169', 1222);
insert into gestion_cuentaPrePaga values (100, 999);
insert into gestion_nacional values ( 99, 'usu1@gmail.com',0, 'Juan', null, null);
insert into gestion_clienteTelepeaje values (9999, null,  999, 99);
update gestion_nacional SET clienteTelepeaje_idClienteTelepeaje = 9999 WHERE id=99;

insert into gestion_vehiculo values (99, 'IBM11', 111, 1, '2023-06-05 16:10:17.169169', 9999);
insert into gestion_usuario_gestion_vehiculo values (99,99);  


insert into gestion_cuenta values  (888, '2222-06-05 16:10:17.169169', 1333);
insert into gestion_cuentaPrePaga values (100, 888);
insert into gestion_extranjero values ( 88, 'usuExtranjero@gmail.com',1, 'JuanExtranejro', null);
insert into gestion_clienteTelepeaje values (8888, null,  888, 88);
update gestion_extranjero SET clienteTelepeaje_idClienteTelepeaje = 8888 WHERE id=88;

insert into gestion_vehiculo values (88, 'AAX111', 333, 1, '2222-06-05 16:10:17.169169', 8888);
insert into gestion_usuario_gestion_vehiculo values (88,88);

insert into peaje_tarifa (DTYPE,  valor) values ('comun', 180);
insert into peaje_tarifa (DTYPE,  valor) values ('preferencial', 100);


