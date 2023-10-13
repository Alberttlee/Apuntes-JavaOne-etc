# Spring Boot 3: documentar, probar y preparar una API para su implementación

- Documentacion
- Test automatizados
  - Junit y mokito
- Build del proyecto

[Model figma](https://www.figma.com/file/vgn35i1ErivIN8LJYEqxGZ/Untitled?type=design&node-id=0-1&mode=design)
[Trello de Api Voll Med](https://trello.com/b/yGQuuyVV/api-voll-med)

git pull https://github.com/Alberttlee/Voll-Med-API.git --allow-unrelated-histories


---

## Proyecto

- Códigos para nuevas funcionalidades

A la hora de hacer nuevas funcionalidades vamos a tener 

**Controller** - Es el lugar en el que enviamos y recibimos peticiones, la que conecta con Postman, Insomnia o cualquier API externa, para mapear la solicitud de la nueva funcionalidad
**DTO** - que representan los datos que llegan y salen de la API
**Entidades JPA** - La que representan las tablas en la base de datos
**Repository** -  Elemento en Spring que se encarga de realizar todas las conexiones de la base de datos e incluso hace algunas consunsultas por nosotros, para aislar el acceso a la base de datos;
**Migraciones** - Para agregar tablas o nuevos registros de datos, hacer las alteraciones en la base de datos.

**S. Segurity**
**Reglas de Nº**

---

### Consultas

Cremos la entidad, Los Dto (Uno que recibe los datos para la base de datos y otro que devuelve), el repository y la creacion de tabla mediante `migration`

```sql
    create table consultas(

    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key (id),

    constraint fk_consultas_medico_id foreign key (medico_id) references medicos(id), /*creamos restricciones consultas_medico y llave estranjera medico id*/
    constraint fk_consultas_paciente_id foreign key (paciente_id) references pacientes(id),
);
```

```json
    {
    "idPaciente": "1",
    "idMedico": "1",
    "fecha": "2023-12-12T"  //las horas,minutos y segundos, se encuentran separados por la T
}
```

> [!Important]
> Los nombres de los campos enviados en JSON a la API deben ser idénticos a los nombres de los atributos de las clases DTO, ya que de esta manera Spring puede completar correctamente la información recibida
> La anotación @`JsonAlias` sirve para mapear "alias" alternativos para los campos que se recibirán del JSON, y es posible asignar múltiples alias:
>
>```java
> @JsonAlias({"producto_id", "id_producto"}) Long idProducto,
> @JsonAlias({"fecha_compra", "fecha"}) LocalDate fechaCompra
>```

### Servicio

- *Clase que tiene las **reglas de negocio***, validaciones, por lo que hace uso de Los *repository* para buscar los elemntos que llegan al ``controller`` por medio de *DTOs*
- Esta clase se hace llamar ``@Service`` y se tiene que encontrar disponible por lo que tenemos que **inyectarlo** dentro de nuestro ``controller``

Las validaciones:

Clases ``Throwable`` - responde ante errores(en caso de querer errores poddddemos usar throw) y excepciones, cuando extendemos de el tenemos que agregar un `throw` al método
Clases ``RuntimeException`` - responde ante excepcione

</br>

> Existen dos formas más utilizadas para *crear Services*. Puede crear Services más genéricos, *responsables de todas las asignaciones de un `Controller`*; o ser aún más específico, aplicando así la S del **SOLID**: Single Responsibility Principle (Principio de Responsabilidad Única). Este principio nos dice que una clase/función/archivo debe tener sólo una única responsabilidad.
> Piense en un sistema de ventas, en el que probablemente tendríamos algunas funciones como: Registrar usuario, Iniciar sesión, Buscar productos, Buscar producto por nombre, etc. Entonces, podríamos crear los siguientes Services: RegistroDeUsuarioService, IniciarSesionService, BusquedaDeProductosService, etc.
>
> Pero es importante estar atentos, ya que muchas veces no es necesario crear un Service y, por lo tanto, agregar otra capa y complejidad innecesarias a nuestra aplicación. Una regla que podemos utilizar es la siguiente: si no hay reglas de negocio, simplemente podemos realizar la comunicación directa entre los controllers y los repositories de la aplicación.

</br>

### Clases de validación

Para tener mas odrdenado y ms elegante y que esta clase siga creciendo, que a la hora de darle mantenimiento se mas compleja

Se crea un paquete `Validaciones` que incluiran las validaciones de regla de negocio, como si algo esta activo, el horario de algun lugar que si no esta en el rango mande ``ValidationException`` con un mensaje

## Principios SOLID

**SOLID** es un acrónimo que representa cinco principios de programación:

- Principio de Responsabilidad Única (Single Responsibility Principle)
- Principio Abierto-Cerrado (Open-Closed Principle)
- Principio de Sustitución de Liskov (Liskov Substitution Principle)
- Principio de Segregación de Interfaces (Interface Segregation Principle)
- Principio de Inversión de Dependencia (Dependency Inversion Principle)

Cada principio representa una buena práctica de programación que, cuando se aplica en una aplicación, facilita mucho su mantenimiento y extensión. Estos principios fueron creados por Robert Martin, conocido como Uncle Bob, en su artículo Design Principles and Design Patterns.

### Pilar de polimorfismo (P)

Cuando tenemos **multiples clases que implementan el mismo método con la misma firma**, misma funcionalidad nosotros podemos crear una **``interface``** para indicar a los desarrolladores que ese va a ser un patron que se va a seguir.

Muchas clases que implementan una interfaz con el mimos metodo utilizanddo una interfaz

```java
///ejemplo
@Component
public interface ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datos);
}

/// toddas las clases que tengan el mismo metodo la implementan 
public class PacienteActivo implements ValidadorDeConsultas{
        public void validar(DatosAgendarConsulta datos){
            ///propio metodo
        }
}

//inyectarlo y no tener que hacer muchos @Autowired
@Autowired
List<ValidadorDeConsultas> validadores; //Todos los que utilicen la interfaz validadorDeConsultas van a ser inyectados en la lista y serán disponibles
```

Siempre que tengamos que indicar donde se va a inyectar una clase que esta autoinstanciada por Spring nosotros utilizamos `@Autowired`
Cuando quisieramos autoinstanciar una clase necesitamos ver cual es la finalidad de esa clase pa usar, estan las etiquetas @``Service``, @Repository, @``Component``

### Single responsability (S)

Unica responsabilidad/función para cada una de las clases

### Principio de Inversion  de dependencias

Las clases de alto nivel tienen que depender de abstracciones y no de clases de alto nivel

- Clases de alto nivel

Aquellas que estan reelacionadas con las reglas de negocio, como consultas , guarddar una consulta o de asignar unas consultas por id

- Clases de bajo nivel

Clases que se encargarn de realizar conexiones

</br>

## Documentando con SpringDoc

Api que se integra a nuestro proyecto, [OpenAPI SpringDoc](https://springdoc.org/), documentacion interactiva, el cliente puede interactuar con los endpoints y consigue una mejor ilustracion de ls parámetros que se estan aplicando

[sitio web oficial de la OpenAPI Initiative] (https://www.openapis.org/)

Dependecia:

```xml
    <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.2.0</version>
   </dependency>
```



- Documentation will be available in HTML format, using the official swagger-ui jars
- The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http://server:port/context-path/v3/api-docs

**server**: The server name or IP
**port**: The server port
**context-path**: The context path of the application

### Configurar

Para configurar y poder usar la herramienta nos dirigimos a `SecurityConfigurations` y modificamos la parte de `SecurityFilterChain`

```java
//  de
.requestMatchers(HttpMethod.POST,"/login")
//  a    
.requestMatchers("swagger-ui.html","/v3/api-docs/**","/swagger-ui/**") //indicanto el patron regex indicando que quiero la direccion de la pagina inicias y las subdirecciones que pasrtan de ellas
```

Para agregar una autorizacion en el encabezado de las peticiones, tenemos que agregar un método del tipo **@Bean** para ejecutar ese metodo de forma automatica, asi como la  la anotacion dentro dde todos los controladores que vayan a tener acceso a ese token

paquete `infra.springdoc` creamos una clase llamada **SpringDocConfiguration**

```java
    @Configuration //instanciada en el contexto de spring
    public class SpringDocConfiguration {
    
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .components(new Components()
                            .addSecuritySchemes("bearer-key",
                                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
        }
    
    
        @Bean
        public void message(){
            System.out.println("bearer is workin");
        }
    }
```

Y en cada controller que necesitamos usar un JWT vamos a usar una etiqueta `@SecurityRequirement(name = "bearer-key")` [ver ddocumentacion](https://springdoc.org/#how-do-i-add-authorization-header-in-requests)

en la clase que implementa login no ees necesario ya que lo tenemos configurado

```java
        //ejemplo
    @SecurityRequirement(name = "bearer-key")
    public class MedicoController {
```

> Además del token, podemos incluir otras informaciones en la documentación que forman parte de la especificación OpenAPI, como la descripción de la API, información de contacto y su licencia de uso.
> Estas configuraciones se deben hacer en el objeto OpenAPI, que se configuró en la clase **SpringDocConfigurations**
> 
> ```java
> @Bean
    > public OpenAPI customOpenAPI() {
    > return new OpenAPI()
    > .components(new Components()
    > .addSecuritySchemes("bearer-key",
    > new SecurityScheme()
    > .type(SecurityScheme.Type.HTTP)
    > .scheme("bearer")
    > .bearerFormat("JWT")))
    > .info(new Info()
    > .title("API Voll.med")
    > .description("API Rest de la aplicación Voll.med, que contiene las funcionalidades de CRUD de médicos y pacientes, así como programación y cancelación de consultas.")
    > .contact(new Contact()
    > .name("Equipo Backend")
    > .email("backend@voll.med"))
    > .license(new License()
    > .name("Apache 2.0")
    > .url("http://voll.med/api/licencia")));  
    > }
> ```

## Test automatizados

- test de caja negra

- test de caja blanca
  mas control

---

librerias

Mokito
JUnit

### Configurando danco de dddatos test

Cuando usamos bases de datos de prueba tenemos que separar la base de produccion a la de ddebug o prueba

Configurar una base ddde datos de test:

creamos un nuevo properties llamado `application-test.properties` en el cual configuraremos nuestra base de datos de prueba

```java
    @DataJpaTest //testear capas de persistencia, entiende que trabajamos con base de datos
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //configuro que la base de datos que uso es externa y que no voy a remplazr la base de datos que uso
    @ActiveProfiles("test")
    class MedicoRepositoryTest {
    
        @Test
        void seleccionarMedicoConEspecialidadEnFecha() {
    
        }
    }
```

- ``?createDatabaseIfNotExist=true&ServerTimezone=UTC`` agregar al final de el link de una direccion de base de datos para que cree esa si es que no existe

- Agregar una base ddde datos en memoria:

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
También debe eliminar las anotaciones @AutoConfigureTestDatabase y @ActiveProfiles en la clase de prueba, dejándola solo con la anotación @DataJpaTest:
@DataJpaTest
class MedicoRepositoryTest {
//resto del código permanece igual
}
```

Podemos combinar pruebas unitarias con pruebas de integración.

### Build con maven

``${DATASOURSE_URL}`` -- FORMATO PARA PASARR DATOS en variables dde entorno a spring y proteger nuestos datos como url¡, username y contraseña
``${DATASOURSE_URL}``
``${DATASOURSE_URL}``

ls target

deplay con terminal 

- servicios de nube, sistema de clound y sevidor

### Para saber más: GraalVM Native Image

Una de las características más destacadas de la versión 3 de Spring Boot es el soporte para imágenes nativas, algo que reduce significativamente el consumo de memoria y el tiempo de inicio de una aplicación. Algunos otros frameworks competidores de Spring Boot, como Micronaut y Quarkus, ya ofrecían soporte para esta función.

De hecho, era posible generar imágenes nativas en aplicaciones con Spring Boot antes de la versión 3, pero esto requería el uso de un proyecto llamado Spring Native que agregaba soporte para ello. Con la llegada de la versión 3 de Spring Boot, ya no es necesario utilizar este proyecto.

Native Image
Una imagen nativa es una tecnología utilizada para compilar una aplicación Java, incluyendo todas sus dependencias, generando un archivo binario ejecutable que puede ser ejecutado directamente en el sistema operativo sin necesidad de utilizar la JVM. Aunque no se ejecute en una JVM, la aplicación también contará con sus recursos, como la gestión de memoria, el recolector de basura y el control de la ejecución de hilos.

Para obtener más detalles sobre la tecnología de imágenes nativas, consulte la documentación en el sitio web: https://www.graalvm.org/native-image

- Native Imagem com Spring Boot 3
Una forma muy sencilla de generar una imagen nativa de la aplicación es mediante un plugin de Maven, que debe incluirse en el archivo pom.xml:

```xml
<plugin>
  <groupId>org.graalvm.buildtools</groupId>
  <artifactId>native-maven-plugin</artifactId>
</plugin>
```

¡Listo! Esta es la única modificación necesaria en el proyecto. Después de esto, la generación de la imagen debe hacerse a través de la terminal, con el siguiente comando de Maven que se ejecuta en el directorio raíz del proyecto:

```txt
./mvnw -Pnative native:compile
```

Este comando puede tardar varios minutos en completarse, lo cual es completamente normal.

¡Atención! Para ejecutar el comando anterior y generar la imagen nativa del proyecto, es necesario que tenga instalado en su computadora GraalVM (una máquina virtual Java con soporte para la función Native Image) en una versión igual o superior a 22.3.

Después de que el comando anterior termine, se generará en la terminal un registro como el siguiente:

```java
Top 10 packages in code area:           Top 10 object types in image heap:
   3,32MB jdk.proxy4                      19,44MB byte[] for embedded resources
   1,70MB sun.security.ssl                16,01MB byte[] for code metadata
   1,18MB java.util                        8,91MB java.lang.Class
 936,28KB java.lang.invoke                 6,74MB java.lang.String
 794,65KB com.mysql.cj.jdbc                6,51MB byte[] for java.lang.String
 724,02KB com.sun.crypto.provider          4,89MB byte[] for general heap data
 650,46KB org.hibernate.dialect            3,07MB c.o.s.c.h.DynamicHubCompanion
 566,00KB org.hibernate.dialect.function   2,40MB byte[] for reflection metadata
 563,59KB com.oracle.svm.core.code         1,30MB java.lang.String[]
 544,48KB org.apache.catalina.core         1,25MB c.o.s.c.h.DynamicHu~onMetadata
  61,46MB for 1482 more packages           9,74MB for 6281 more object types
--------------------------------------------------------------------------------
    9,7s (5,7% of total time) in 77 GCs | Peak RSS: 8,03GB | CPU load: 7,27
--------------------------------------------------------------------------------
Produced artifacts:
 /home/rodrigo/Desktop/api/target/api (executable)
 /home/rodrigo/Desktop/api/target/api.build_artifacts.txt (txt)
================================================================================
Finished generating 'api' in 2m 50s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:03 min
[INFO] Finished at: 2023-01-17T12:13:04-03:00
[INFO] ------------------------------------------------------------------------
```

La imagen nativa se genera en el directorio target, junto con el archivo .jar de la aplicación, como un archivo ejecutable de nombre api,

texto alternativo: Lista de archivos y directorios ubicados dentro del directorio target del proyecto, incluido el archivo de la imagen nativa, cuyo nombre es api.

La diferencia del archivo .jar, que se ejecuta en la JVM mediante el comando java -jar, la imagen nativa es un archivo binario y debe ejecutarse directamente desde la terminal:

```txt
target/api
```

Al ejecutar el comando anterior se generará el registro de inicio de la aplicación, que al final muestra el tiempo que tardó la aplicación en iniciarse:

```java
INFO 127815 --- [restartedMain] med.voll.api.ApiApplication : Started ApiApplication in 0.3 seconds (process running for 0.304)
```

Observe que la aplicación tardó menos de medio segundo en iniciarse, algo realmente impresionante, ya que cuando se ejecuta en la JVM, a través del archivo .jar, este tiempo aumenta a alrededor de 5 segundos.

Para obtener más detalles sobre la generación de una imagen nativa con Spring Boot 3, consulte la documentación en el sitio: [Soporte de imagen nativa GraalVM.](https://www.graalvm.org/22.0/reference-manual/native-image/)