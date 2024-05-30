# Iteración 1
Módulos Implementado: Gestion - MediosDePago - Monitoreo - Peaje

Cada módulo posee sus respectivas capas: Aplicación, Dominio, Infraestructura e Interfase

El módulo de monitoreo solo posee los Observers de los eventos

Para realizar los test, se ha provisionado de mocks que nos hará posible realizar los test sin la necesidad de la invocación de las dependencias, estos mocks proveen datos pre cargados.

# Iteración 2
Se han generado 2 API´s, ClienteAPI para el módulo de Gestión y PeajeAPI para el módulo de Peaje.

Se ha generado la necesidad de crear datatypes los cuales nos van a servir para los datos que entran en las API´s

Se ha implementado la API REST "ServicioMockMedioDePago", está se va a encargar de exponer una API Rest con funcionalidad que simule la autorización o no de un pago.
Esta posee la función "autorizarPago", la cual va a recibir un número de tarjeta, la cual va a simular la aprobación o rechazo de las tarjetas.

La aplicación principal "TallerJava2024" que  tiene los módulos pertenecientes al sistema de tráfico y la aplicación web externa del módulo de medios de pagos llamada con la API REST "ServicioMockMedioDePago" van a correr en el mismo servido Wildfly.

Para esto se tuvo que crear los archivos "jboss-web.xml" en cada aplicación web para separar los contextos en el servidor.
Luego, mediante la consola cli de wildfly se deployo las dos aplicaciones agregando el WAR de "TallerJava2024" y "ServicioMockMedioDePago", http://localhost:9990 -> Deployments -> add -> ServicioMockMedioPago.war.

Ejemplos URL´s:
	-http://localhost:8080/ServicioMockMedioPago/api/*
	-http://localhost:8080/TallerJava2024/api/*

Se han agregado anotaciones de persistencia a la gran mayoría de clases de cada módulo, con distintas estrategias para la creación de las tablas dependiendo de cada módulo.
Usamos la siguiente estructura snakeCase para las tablas: nombreModulo_Clase.




