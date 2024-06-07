use tallerJava;
insert into gestion_cuenta values  (1, '2024-06-05 16:10:17.169169', 1222);
insert into gestion_cuentaPrePaga values (100, 1);
insert into gestion_nacional values ( 1, 'usu1@gmail.com', 'Juan', null, null);
insert into gestion_clienteTelepeaje values (1, null,  1, 1);
update gestion_nacional SET clienteTelepeaje_idClienteTelepeaje = 1 WHERE id=1;

insert into gestion_vehiculo values (1, 'IBM11', 111, 1, '2023-06-05 16:10:17.169169', 1);
insert into gestion_usuario_gestion_vehiculo values (1,1);  

insert into peaje_tarifa (DTYPE,  valor) values ('comun', 180);
insert into peaje_tarifa (DTYPE,  valor) values ('preferencial', 100);


