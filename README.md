# Iteración 1

Módulos Implementado: Gestion - MediosDePago - Monitoreo - Peaje

Cada módulo posee sus respectivas capas: Aplicación, Dominio, Infraestructura e Interfase

El módulo de monitoreo solo posee los Observers de los eventos

Para realizar los test, se ha provisionado de mocks que nos hará posible realizar los test sin la necesidad de la invocación de las dependencias, estos mocks proveen datos pre cargados.

# Iteración 2

Se han generado 2 API´s, ClienteAPI para el módulo de Gestión y PeajeAPI para el módulo de Peaje.

Se han generado 2 API´s, SuciveAPI para el módulo de Sucive y MediosPagoAPI para el módulo de Medios Pago para visualizar los pagos tanto por Sucive como con Tarjeta en formato json.

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


# Iteración 3 
Docker, Grafana, InfluxDB y Micrometer

Se instaló Docker Engine para levantar el contenedor de Grafana + InfluxDB según el siguiente link:
https://hub.docker.com/r/philhawthorne/docker-influxdb-grafana/

Para iniciar el conetenedor:
sudo docker start docker-influxdb-grafana

Para entrar a Grafana:
http://localhost:3003
user/pass: root/root

Para entrar a InfluxDB (Chronograf): 
http://localhost:3004
user/pass: root/root

Para entrar a InfluxDB Shell (CLI) y trabajar con comandos tipo SQL:
Entrar por SSH al contenedor
Ejecutar comando influx

Modulo de Monitoreo:

Se creo una clase RegistradorDeMetricas que tiene los parámetros de configuración para Micrometer y la BD de Influx. Influx gestiona una BD de tipo Time Series.  
Se crearon Observer para los distintos módulos, con el fin de recibir e incrementar los contadores de los eventos que se graficaran en Grafana.
En Grafana se crearon varios Dashboard que gráfica los eventos en tiempo que muestra:
	OK prepagos realizados; 
	OK post pagos realizados;
	OK pagos sucive;
	OK cantidad de vehículos nacionales; 
	OK cantidad de vehículos extranjeros;
	OK pago con tarjeta;
	Error Saldo Insuficiente;
	Error Tarjeta Rechazada;
	Error Cliente no encontrado por TAG;

La configuración del Dashboard con las gráficas es DashboardGrafana.json.

# Iteración 4 
Implementación de Queue de pagos y JMeter para análisis de tiempo de respuesta del servidor.

Se implemento una Queue de pagos ya que el caso de pasada por peaje de vehiculo nacional siempre se va a poder cobrar: sea si tiene saldo en la cuenta pre-paga; tiene tarjeta asociada en la cuenta post-paga; o por la matricula en Sucive.

En en módulo Peaje se implemento tres clases: EnviarMensajeQueue; NuevoPagoConsumer; y PagoRealizadoMessage. 

NuevoPagoConsumer: realiza el procesamiento de los mensjaes que se encuentran encolados.
EnviarMensajeQueue: defino la direccion de jndi y es donde se encolan los mensajes para luego tratarse. Los mensjaes están encapsulados como json. 
PagoRealizadoMessage: convierte DTs a json. 

Para visualizar la útilidad de la Queue se utilizo JMeter para crear carga en el servidor. 

La configuración se encuentra en: Plan de Pruebas JMS.jmx

Response sin Queue:








