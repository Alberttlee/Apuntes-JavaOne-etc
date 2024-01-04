# Spring Boot3

- [Spring Boot3](#spring-boot3)
  - [Spring](#spring)
    - [Spring boot](#spring-boot)
      - [Cors](#cors)
        - [Same-origin policy](#same-origin-policy)
        - [Habilitación de diferentes orígenes en Spring Boot](#habilitación-de-diferentes-orígenes-en-spring-boot)
  - [Creacion de un proyecto](#creacion-de-un-proyecto)
    - [Configuración](#configuración)
  - [Anotaciones](#anotaciones)
  - [Códigos de respuesta](#códigos-de-respuesta)
  - [REQUEST](#request)
    - [Java Record](#java-record)
    - [DTO (Data Transfer Object)](#dto-data-transfer-object)
  - [Spring Data JPA](#spring-data-jpa)
    - [Entidades JPA](#entidades-jpa)
    - [Lombok](#lombok)
    - [Interfaces Reposity (Patrón Repository)](#interfaces-reposity-patrón-repository)
      - [Agregando intefaz Repository al Proyecto](#agregando-intefaz-repository-al-proyecto)
    - [Flyway gestor de migraciones](#flyway-gestor-de-migraciones)
      - [Nuevas migraciones](#nuevas-migraciones)
    - [Validaciones](#validaciones)
  - [Request GET](#request-get)
    - [Estrategias de paginación](#estrategias-de-paginación)
      - [Query Params](#query-params)
        - [Modificar los parámetros por defecto](#modificar-los-parámetros-por-defecto)
      - [Ordenación](#ordenación)
  - [Resquest PUT (update)](#resquest-put-update)
  - [Request DELETE](#request-delete)
    - [DELETE verdadero](#delete-verdadero)
    - [DELETE Lógico](#delete-lógico)
    - [Retornos API](#retornos-api)

## Spring

Spring es un framework para desarrollar aplicaciones en Java, creado a mediados de 2002 por Rod Johnson, que se ha vuelto muy popular y adoptado en todo el mundo debido a su simplicidad y facilidad de integración con otras tecnologías.

### Spring boot

Es una herrmanienta o módulo de Spring con el objetivo de agilizar la cración de un proyecto que utilice Spring como Framework

---

- **Spring Boot 3**
  - Compatibilidad con Jav 17
  - Migracion de especificaciones Java EE a Jakarta EE
  - Compatibilidad con imágenes nativas

- **Lombok**
    Para minimizar codigo con el uso de etiquetas. Documentacion [Lombok](https://projectlombok.org/)
- **Mysql/Flyway** (Flyway es una herramienta usada para migraciones de bases de datos, ligero de intercambio de datos).
    Gestor de base de datos a nivel de estructura y las tablas, declaras las tablas como scrip de SQL y el motor de Flyway lo ejecuta y va a crear su estructura de datos en Mysql de tal forma que es mantenible en el futuro, versionable y habilitar colaboracion con muchos colaboradores

- JPA/HIBERNETE
- Maven
- simplifica la interacción y el diseño de API basadas en HTTP
- Insomia (herramienta, simplifica la interacción y el diseño de API basadas en HTTP)

**EndPoint** un endpoint es una *URL* de una *API* o un backend que se encarga de contestar a una petición

**JSON**(*JavaScript Object Notation*) - Es un fromato utilizado para representar información,
    Envia y recibe información, es un formato que recibe y envia información, habiéndose popolarizado por su ligereza, sencillez, facilidad de lectura po personas u máquinas.

---

#### Cors

*CORS* es un mecanismo utilizado para agregar encabezados *HTTP* que le indican a los navegadores que permitan que una aplicación web se ejecute en un origen y acceda a los recursos desde un origen diferente. Este tipo de acción se denomina **cross-origin HTTP request**. En la práctica, les dice a los navegadores si se puede acceder o no a un recurso en particular.

##### Same-origin policy

Por defecto, una aplicación Front-end, escrita en JavaScript, solo puede acceder a los recursos ubicados en el mismo origen de la solicitud. Esto sucede debido a la política del mismo origen (same-origin policy), que es un mecanismo de seguridad de los navegadores que restringe la forma en que un documento o script de un origen interactúa con los recursos de otro. Esta política tiene como objetivo detener los ataques maliciosos.

¿Qué hacer cuando necesitamos consumir una API con una URL diferente sin tener problemas con CORS?

- Al enviar una solicitud a una API de origen diferente, la API debe devolver un header llamado **Access-Control-Allow-Origin**. Dentro de ella es necesario informar los diferentes orígenes que serán permitidas de consumir la API, en nuestro caso: ``Access-Control-Allow-Origin: http://localhost:3000``
- Puede permitir el acceso desde cualquier origen utilizando el símbolo * (asterisco): ``Access-Control-Allow-Origin: *``. Pero esta no es una medida recomendada, ya que permite que fuentes desconocidas accedan al servidor, a menos que sea intencional, como en el caso de una API pública. Ahora veamos cómo hacer esto en Spring Boot correctamente.

##### Habilitación de diferentes orígenes en Spring Boot

Para configurar el CORS y permitir que un origen específico consuma la API, simplemente cree una clase de configuración como la siguiente:

```java
    @Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
```

> [!NOTE]
> ``http://localhost:3000`` sería la dirección de la aplicación Front-end y ``.allowedMethods`` los métodos que se permitirán ejecutar. Con esto, podrás consumir tu API sin problemas desde una aplicación front-end.

</br>

## Creacion de un proyecto

1. Crear proyecto Sprint boot
2. Agregamos deoendencias necesarias
3. Configuramos la base de datos en el archivo `application.properties`
4. Creamos las entidades para persistir
5. Creamos los [controllers](#request) usa de parámetro un `Record` para mapper la información
6. [creamos las DTO (Clases record) para recibir la informacion del Json](#dto-data-transfer-object)
7. Creacion del patron [repository](#agregando-intefaz-repository-al-proyecto) para hacer todo el proceso de gestión en la base de datos auttomaticamente
8. Para hacer uso del gestor de migraciones Flyway no dirigiremos a ``resourse`` > ``bd`` > ``migration`` (crear los directorios si no aparecen)
    - Crear un archivo en esa localizacion con el nombre [`V1__create-table-medicos.sql`](#flyway-gestor-de-migraciones)
9. Agregmos valiaciones con la ependencia [Validation](#validaciones)

Creamos aplicación Con Spring Boot 3 desde la pagina que nos proporciona la propia Spring Boot: [spring.io](https://start.spring.io/)

Dependencias:

- **Spring Boot DevTools**:
    Da la facilidad de modificar  nuestro código y no tener que reiniciar nuestro código para var nuestros cambios, Reinicio de servidor automaticamente.
- **Lombok**:
    Ayuda a reducir codigo
- **Spring Web**:
    nos da las librerias de API REST atrrvés de Post, Get, Put, Delete, etc.
- **Spring Data JPA**
- **Mysql driver**
- **Flyway Migration**
    No es propia de Spring. Es un gestor de migraciones dde base de datos, es basicamente para mantener tu base de datos como código versionado
- **Validation**
    Bean validation with Hibernate validator

Servidor de aplicaciones: En informática, se denomina servidor de aplicaciones a un servidor en una red de computadores que ejecuta ciertas aplicaciones. Usualmente se trata de un dispositivo de software que proporciona servicios de aplicación a las computadoras cliente

### Configuración

- **Idea** ACtivando propiedades al IDE Intellij para que se actualice el servidor automaticamente junto con la dependencia
  - ``Settings > Buil, Execution, Depployment > Compiler`` Y activamos la casilla "Build proyect automatically"
  - `Advanced Settings` - Compiler - Activamos Allow auto-make  to star even if developed application is currently running

- **Spring Data**
  - Dirigirse al archivo `application.properties` en la carpeta *resources* y configurar nuestra base de datos

    ```properties
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost:3306/clinica
        spring.datasource.username=root
        spring.datasource.password=root

    ```

    o en `application.yaml`

    ```yaml
        spring:
            datasource:
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://localhost:3306/clinica
                username: root
                password: root

    ```

</br>

## Anotaciones

- **``@RestController``**
    Creacion de un Controller en Rest (no retornamos ninguna página html) Solo retornamos JSON, XML, TXT o culquier tipo de dato crudo (@ResponseBody)
- **``@RequestMapping``**
    Para decir que ruta de HTTP esta siguiendo el método que se va a crear, cada vez que se entre a esa ruta, debe hace hacer algo

    ```java
        @RequestMapping("/hello")
    ````

- **``@GetMapping``**
    Mapeamos el método get en la ruta especificada.
- **``@PostMapping``**
- **``@RequestBody``**
    Señalar que un parámetro es el body, Este parámetro es el body
- **``@Transactional``** - Abre una transaccion de la base de datos y cierra la transaccion.
- [Anotaciones JPA](#entidades-jpa)
- [Anotaciones Lombok](#lombok)

</br>

## Códigos de respuesta

Los códigos HTTP (o HTTPS) tiene tres dígitos, y el primer dígito representa la clasificación dentro de las cinco categorias posibles.

1. 1XX: Informativo: la solicitud fue aceptada o el proceso aún está en curso;
2. 2XX: Confirmación: codigos de exito, la acción se completó o se comprendió
3. 3XX: Redirección: indica que se debe hacer o se debió hacer algo más para completar la solicitud;
4. 4XX: Error del cliente: nivel usuario/cliente, indica que la solicitud no se puede completar o contiene una sintaxis incorrecta;
5. 5XX: Error del servidor: el servidor falló al concluir la solicitud.

A cada respuesta que devuelve la aplicación REST se le envía un código que define el estado de la solicitud. Por ejemplo:

- **200** (OK) solicitud cumplida con éxito. (PUT)
- **201** (CREADO) objeto o recurso creado con éxito. (POST)
- **204** (SIN CONTENIDO) objeto o recurso eliminado con éxito, no retorna nada. (DELETE)
- **400** (MALA SOLICITUD) ocurrió un error en la solicitud (puede haber numerosas causas), error nivel cliente?
- **403** (PROHIBIDO) Significa que el servidor entendió la solicitud del cliente, pero se niega a procesarla, ya que el cliente no está autorizado para hacerlo.
- **404** (NO ENCONTRADO) ruta o colección no encontrada. Puede ser que la aplicación ya no exista, que la URL haya cambiado o que haya ingresado una URL incorrecta.
- **500** (ERROR INTERNO DEL SERVIDOR), se ha producido algún error del servidor, puede ser cualquier cosa.
- **503** El servicio al que accede no esta disponible temporalmente.
  Las causas comunes son un servidor que está fuera de servicio por mantenimiento o sobrecargado. Los **ataques maliciosos como DDoS** causan mucho este problema.

[Lista](https://developer.mozilla.org/es/docs/Web/HTTP/Status) completa del código de cada solicitud.

</br>

## REQUEST

EndPoint : un endpoint es una *URL* de una *API* o un backend que se encarga de contestar a una petición

Cuando hablas de Rest Services te refieres a recursos

Body : cuerpo de nuestros Request

- **POST** -- enviando datos al servidor
    Los post request requieren una parte del cuerpo, puede ser formato Json, XML, TXT, etc. Son llamados ``Payload``
    Incluimos dentro del request y podemos enviar los datos
- **PUT** -- actualizar
- **GET** -- traer /si obtienes / solicitar a un servidor que envíe un recurso
- **DELETE** -- elimina un determinado dato o colección del servidor

</br>

Para mappear un endpoint

`@RequestBody` --- Señalar que un parameto es el Body

```java
    @RestController
    @RequestMapping("/medicos")
    public class MedicoController{
        //handling para atrapar esta request
        //Mapear request de tipo POST
        @PostMapping
        public void registrarMedico(@RequestBody String xxxx){
        }
    }
```

### Java Record

- Record (Disponibles desde la version 14(Experimental) de java y en las últimas versiones estables 17 - )

Es un recurso que permite representar una clase inmutable que permite crear dinamicamente o rapidamente campos para llenarlos y en tiempo de compilación va a crear usolo atributos, constructor y métodos de lectura, de una manera muy simple y ágil, metodos *tostring*, *hash*, y lo que fuera necesario.

### DTO (Data Transfer Object)

Es un padrón que se usa a nivel de controller, un objeto como intermediario para que mapee la información que nos llega desde nuestro cliente a nuestro API, como un controller usa de parámetro un `Record` para mapper la información. (*Record*)

Es usado para recibir lo que esta viniendo de la API.

---

## Spring Data JPA

### Entidades JPA

- ``@Entity(name = "Medico")`` - Para indicar que la clase es una entidad con ese nombre
- `@Table(name ="medicos")` - Indicar que sera una tabla y su nombre, se usa despues de identity, inicio de la clase
- @``Id`` - Para decir que es una llave primaria o el identificador
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` - Indicar que el valor es  de *Auto-Increment*
- ``@Enumerated(EnumType.STRING)`` - Señalar que el atributo es un Enum y sera convertido a String en la base de datos
- `@Embedded` - Que el atributo de tipo clase va a ser incorporado en esta entidad, va a ser Inyectada con los atributos
- ``@Embeddable`` - Para decir que no es una tabla, que solo es para ser incluida en otra que tenga la anotacion ``embedded``

### Lombok

- ``@Getter`` - Genera todos los getter de todos los atributoa, se utiliza al inicio de la clase
- ``@NoArgsConstructor`` - Genera un contructor sin argumentos, constructor por default, al inicio de la clase
- ``@AllArgsConstructor`` - Gnera un constructor con todos los argumentos, al inicio de la clase
- `@EqualsAndHashCode(of = "id")` - Implementa los metodos *Equals y hashCode* con el parametro "id" para comparar si son iguales

### Interfaces Reposity (Patrón Repository)

Capa extra, en la que vamos a tomar los datos que llegan, transformarlos a entidades y estas entidades van a persistir en la base de datos
Con esta iterfaz, vamos a poder hacer todo el proceso de gestión en la base de datos a nivel de CRUD pero automaticamente

Un repositorio se encuentra entre las reglas de negocio y la capa de persistencia

- interface ``JpaRepository<T, ID>`` - propia de SpringData
    Necesita 2 parámetros:
        1. El tipo de objeto que se va aguardar, con el que se va a trabajar en el repositorio
        2. Tipo e objeto del ID

> [!Importante]
> Los repositorios no deben tener la responsabilidad de tomar decisiones, aplicar algoritmos de transformación de datos o brindar servicios directamente a otras capas o módulos de la aplicación. Mapear entidades de dominio y proporcionar funcionalidades de aplicación son responsabilidades muy diferentes.

#### Agregando intefaz Repository al Proyecto

- Creando una interfaz que extiende de `JpaRepository<T, ID>` declarando los 2 parametros

```java
    public interface MedicoRepository extends JpaRepository< Medico, Long > {
        
     }
```

- Declaramos como parametro ese repository en el controller del mismo y le asignamos una anotación
`@Autowired` esa anotacion le dice a Spring que de su contexto busque el XRespository y lo inyectes para no tener que crear uno nuevo (usar new XRepository y sus implementaciones)

```java
    @Autowired
    private MedicoRepository medicoRepository;
```

**Autowired**: Traducido del inglés, la palabra Autowired sería ''un cable automático''. En el contexto del framework Spring, que utiliza como una de sus bases el patrón de diseño “Inyección de Dependencias”, la idea sirve para definir una inyección automática en un determinado componente del proyecto Spring, ese componente puede ser atributos, métodos e incluso constructores.
Al marcar un componente con la anotación @Autowired le estamos diciendo a Spring que el componente es un punto donde se debe inyectar una dependencia, en otras palabras, el componente se inyecta en la clase que lo posee, estableciendo una colaboración entre componentes.

> [❗Important]
>
> No es recomendable con fines de testing usar ``@Autowired`` a nivel de la declaracion del atributo porque si se usa pruebas unitarias va a tener problemas.
> ``@Autowired`` solo es una forma para fines didacticos
> Otra forma e hacer la inyeccion de dependencias sería con setter

</br>

- Creamos UN método en el controller que recibira los datos tomados por el DTO(records), ese valor sera utilizado por el método `save` que nos proporciona el *repository* anteriormente definido

```java
    @PostMapping
    public void registrarMedico(@RequestBody DatosRegistroMedico datosRegistroMedico){
        medicoRepository.save(new Medico(datosRegistroMedico)); //creamos el constructor de medico que recibe los datos del DTO
    }
```

### Flyway gestor de migraciones

Gestor de base de datos a nivel de estructura y tablas, declaras las tablas como scrip de SQL y el motor de Flyway lo ejecuta y va a crear su estructura de datos
Con las migration puedes gestionar toda la base de datos a traves de código SQL, si haces otra migracion a otra base de datos.. no hay de que preocuparse porque te la llevas contigo

Flyway utiliza una nomenclatura, un tipo de padrón para nombrar sus archivos con el que identifica que archivo ya fue migrado y cual no

- `V1__create-table-medicos.sql` por verisones
    El v1 y los 2 guiones bajos son el patrón de Flyway para identificar que es una migracion y el internamente decide si se ejecuta o no
    Lo siguiente es autoesplicativo sobre esta migration esta haciendo (en este caso crear tabla medicos)
    la tercera parte es la extención de la base de datos `.sql`

  - Dentro del archivo se escribe el código de la base de datos

> [!note]
> Si trabajamos con migrations y dev tool activado es mejor detener el servidor, ya que si hay algún error antes de terminar la tabla se guardará asi la tabla y podria causar errores.
> Las versiones de flyway sirven para crear nuevas migraciones.
>
> - Si nos olvidamos detener el sevidor usando migration y dev tool nos saldra un error de que no se puede iniciar la app por fallos de migraciones
>   Para solucionar este problema será necesario acceder a la base de datos de la aplicación y ejecutar el siguiente comando sql:
>
>    ```sql
>   delete from flyway_schema_history where success = 0
>     ```
>
> El comando anterior se usa para eliminar de la tabla Flyway todas las migraciones cuya ejecución falló. Después de eso, simplemente corrija el código de migración y ejecute el proyecto nuevamente.

</br>

#### Nuevas migraciones

Agregar un campo a una tabla

- Detener la aplicacion
- Crear un archivo sql con la padrón para nombrar un archivo
- `V2__alter-table-medicos-add-telefono.sql` - La version y el nombre esplicativo del archivo
- Agregar el codigo sql a ejecutar
- Alterar los DTO y a nivel entidad

</br>

### Validaciones

Usando la dependencia de Spring Boot, **Bean validation**, validar a nivel de API
Validaciones en DTO(records) y controller parametros en metodos  

Etiquetas:

- **@Valid** - Validara la entidad (Puedde usarse en controller, como parametro o atributo en una aentidad)

    ```java
    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        ///method
    }

    @NotNull
    @Valid
    DatosDireccion direccion
    ```

- **@NotBlank** - Indica que un atributo String no puede ser nulo o vacio
- **@NotNull** - Para evitaar parametros nulos, OBJETOS, enums, numeros
- **@Email** - Para decir que un camppo de cadena debe ser una dirección de corre electrónico válida.
- **@Pattern** - para decir que un campo String solo es válido cuando coincide con una determinada expresión regular.
  - Ejemplo: ``@Pattern(regexp = "\\d{4,6}")`` Solo digitos de 4 a 5 digitos
- **@NotEmpty** - Para decir que un campo de lista no debe estar vacío.
- **@Miny** and **@Max** - decir que un campo numérico solo es válido cuando su valor está por encima o por debajo de cierto valor.

Existen decenas de anotaciones que podemos utilizar en nuestro proyecto. Lista de proncipales anotaciones de [Bean Validation](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints)

</br>

## Request GET

No debes exponer mas información de la que se debe, Eventualmente podemos tener atributos que no queremos que sean devueltos en el JSON, ya sea por razones de seguridad, en el caso de datos sensibles, o incluso porque no son utilizados por clientes API. Puedes crer un DTO dedicado que solo devuelva lo necesario.

Ejemplo de una request Get

```java
    @GetMapping
    public List<DatosListadoMedico> listadoMedicos(){ //devuleve listado del DTO medico
        //usamos stream   y creamos un map que usara los datos del DTO y creara uno muevo devolviendo una lista
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList(); 
    }
```

### Estrategias de paginación

- Interfaz `Page` del paquete *org.springframework.data.domain*
Cuando nosotros trabajamos con páginas necesitamos trabajar con parámetro que llega del frontEnd llamado `Pageable` de  *data.domain*

#### Query Params

Especificar manualmente los valores a nivel de mi URL

- `?` Iniciando con con signo ^`?` en el url para iniciar los parámetros
- `&` - Para incluir un parametro mas

Ejemplo de una query params: `http://localhost:8080/medicos?size=1&page=2`

Parametros por defecto:

```json
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 3,
    "totalPages": 3,
    "size": 1,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
```

##### Modificar los parámetros por defecto

Si quiero sobreescribir los valores en la *interfaz* **Pageable** usaremos la etiqueta **PageableDefault** en el controller
Con *PageableDefault* yo le especifico los nuevos valores que va a tener

- `PageableDefault`
  Me dice los valores por defecto y sobreescribe los que spring tiene por dentro
  En la anotacion podemos agregarle un aparametro que queremos configurar por defecto
  `PageableDefault(size = 2)`

  ```java
    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){ //pageable llega desde el cliente va dentro de findAll
        //Como paginacin retorna page no necesita la api stream ni mapearlo a una lista
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new); //tenemos paginacion y mapeando lo que necesitamos de DatosListadoMedico
    }
  ```

</br>

#### Ordenación

Oredenar las listas segun los requerimientos del negocio

`&` - Utilizando otro amper mas despues de las query params

- **sort** - orden con al atributo registrado en la entidad de la base de datos, **debe ser el mismo**
    `http://localhost:8080/medicos?size=10&page=0&sort=nombre`

> cambiar el nombre
>
> ```java
> spring.data.web.pageable.page-parameter=pagina
> spring.data.web.pageable.size-parameter=tamano
> spring.data.web.sort.sort-parameter=orden
>```

</br>

---
>
> Si en algún momento necesitamos ver la querys que JPA esta ejecutando sobre la base de datos, yo puedo mostrar lo que se esta haciendo
> ``spring.jpa.show-sql=true`` agregar a *application.properties* del proyecto
> ``spring.jpa.properties.hibernate.format_sql=true`` para formatear las querys de sql, pero solo para desarrollo, **no produccion** ya que **consume recursos y performance**

---

## Resquest PUT (update)

Para actualizar necesitas saber que elemento es al que le vas a hacer ese cambio, Usando un identidicador se sabra que elemento/s actualizar

- Usamos el Id para cambiar un valor, Usanddo las etiquetas `PutMapping`, `@Transactional` de jakarta, `@ResquestBody` y `@Valid` en el controllerm nosotros vamos a poder actualizar datos requeridos.
- Creacion de metodos

  ```java
        //controller
    @PutMapping
    @Transactional //cuando termine este metodo, la transaccion se va a validar
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id()); //El cliente envia el id
        medico.actualizarMedico(datosActualizarMedico);
    }
    //Entidad
    public void actualizarMedico(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
    }
        // metodo Entidad direccion, no es una tabla, 
    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        return this;
    }
  ```

- **PUT**
El método **PUT** reemplaza todos los datos actuales de un recurso con los datos enviados en la solicitud, es decir, estamos hablando de una actualización completa. Entonces, con él, hacemos la actualización completa de un recurso en una sola solicitud.

- **PATCH**
El método **PATCH**, a su vez, aplica modificaciones parciales a un recurso. Por lo tanto, es posible modificar solo una parte de un recurso. Con PATCH, entonces, realizamos actualizaciones parciales, lo que flexibiliza las opciones de actualización.

¿Cuál elegir?
>En la práctica, es difícil saber qué método usar, porque no siempre sabremos si un recurso se actualizará parcial o completamente en una solicitud, a menos que lo verifiquemos, algo que no se recomienda.
>
>Entonces, lo más común en las aplicaciones es usar el método **PUT** para las solicitudes de actualización de recursos en una API.

## Request DELETE

Delete lógico o exclusión lógico, es como decir que un registro quede desactivado pero sin borrar de la base de datos para mantener un historico de la base.

Para eliminar también se necesita de un identificador para saber que tipo de archivo se va a eliminar

### DELETE verdadero

Que eliminara por completo un registro de la base de datos

- Path Variables
  Es una variable que va en el path/URL

```java
    @DeleteMapping("/{id}") //dinamico
    @Transactional
    public void eliminarMedico(@PathVariable Long id){ //el valor que llege al endpoint /{id} de la pathvariable 
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
```

### DELETE Lógico

Que solo excluira el registro en la base de datos (desactivar por decir así), no lo eliminara de la base da datos

Este método requiere de otro campo más en la entidad / tabla: `Private Boolean activo;` el cual sera inicializado en el constructor como ``true``;

- Trabajarr con migations
  crear el archivo sql, el cual creara la columna y actualizara la iniciando todas las columnas a 1 de la toda la base de datos

  ```sql
    alter table medicos add COLUMN activo tinyint;
    update medicos set activo = 1;
  ```

`findByActivoTrue` nomenclatura que usa Spring Data para crear las querys y hacer el WHERE en el SELECT, nos permite hacer querys personalizadas

```java
    /* ACTUALIZA EL METODO LISATAR (GET) PARA HACER USO DEL PARAMETO "ACTIVE",
    Y UTILIZARLO PARA LISTAR SOLO LOS QUE TEENGA ESA DEFINICION Y LOGRAR UN DELEETE LOGICO */
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion){ //pageable llega desde el cliente va dentro de findAll
        //Como paginacion retorna page no necesita la api stream ni mapearlo a una lista
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)); //tenemos paginacion y mapeando lo que necesitamos de DatosListadoMedico
    }

    //DELETE LOGICO
    @DeleteMapping("/{id}") //dinamico
    @Transactional
    public void eliminarMedico(@PathVariable Long id){ //el valor que llege en el endpoint /{id}
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }


    //METODO DEL REPOSITORY
    Page<Medico> findByActivoTrue(Pageable paginacion);
```

---

### Retornos API

Hay diferentes tipos de retorno que tenemos que aprender a hacer con Rest

Spring nos ayuda a retornar codigo mas personalizados para que nuestras llamadas sean mas personalistas

- Retornar un [*código HTTP*](#códigos-de-respuesta) **204** (no content) DELETE
  En el método tenemos que retornar un tipo de dato llamado `ResponseEntity`

  ```java
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){ 
        //DELETE LOGICO
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();// <---- retornando un 204
    }
  ```

- Retornar un [*código HTTP*](#códigos-de-respuesta) **200** (ok) PUT
  No es bueno retornar directamente la entidad de la base de datos, para ello se debe tener un DTO (record) dedicado a retornar la informacion del body deseada
  `DatosActualizarMedico` y `DatosRespuestaMedico` son DTO, el primero es para guardar los elementos y el segundo lo que va a retornar de la base de datos

  ```JAVA
    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id()); 
        medico.actualizarMedico(datosActualizarMedico); 
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDocumento(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
    }
  ```

- Devolviendo un [*código HTTP*](#códigos-de-respuesta) **201** (Creado) POST
  Post debe retornar algo por la especificación que tiene REST, es un estándar.
  Debe retornar en un header la URL donde tu puedes retornar x que guardaste
  Con un método GET se puede acceder al xx que acabamos de guardar

  ```java
    @PostMapping //save tiene el update por eso, no se agrega @Transactional
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDocumento(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
     }
  ```

- Devolviendo un [*código HTTP*](#códigos-de-respuesta) **200** GET
  
  ```java

    //METODO GET PARA TRAER ELEMENTOS POR URL AL HEADER
    @GetMapping("/{id}") //dinamico
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){ //el valor que llege en el endpoint /{id}
        //DELETE LOGICO
        Medico medico = medicoRepository.getReferenceById(id);
        var datosmedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getDocumento(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosmedico);
    }
  ```

```java
    //METODO GET PARA LISTAR ELEMENTOS
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion){ //pageable llega desde el cliente va dentro de findAll
        //Como paginacion retorna page no necesita la api stream ni mapearlo a una lista
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }
```







[Subir](#spring-boot3)