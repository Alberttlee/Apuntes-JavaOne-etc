# Seguridad en Spring Boot

Primero que nada debemos entender y llevar a la practica que debemos proteger nuestra aplicacion de cualquier filtrado de datos sensibles al cliente, aplicando practicas que nos proporcionan las herramientas con las que trabajamos.

En el caso de Spring Boot nos proporciona distintas configuraciones y en la cual nos podemos informar de el desde su propia página [spring.io](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

## Aspectos de seguridad

### Errores

- Server properties

    Son configuraciones de propiedades de la aplicacion que deben ser configuradas en `application.properties`

Todo lo referente a `server.error` para tratar el error que esta retornando a mi Cliente

- `server.error.include-stacktrace = never`
  Para no compartir mi stackTrace de mi cliente

#### Tratar errores

Tratar los errores a nivel de controller, a nivel de proyecto, asi evitamos tratar a cada método en especifico

- Organizamos nuestro proyecto, creando un paquete `domain` donde iran las clases a nivel dominio, `infra` en cual incluiremos la clase para tratar los errores

- Crear una clase para aislar el manejo de excepciones de API, utilizando la anotación `@RestControllerAdvice`
    `@RestControllerAdvice` : Esta etiqueta actua como una especie de Proxy para todos nuestros controllers "*RestController*", para interceptar las llamadas en caso suceda una exception
Con  esta etiqueta es suficiente para interceptar nuestros métodos de controller y atrapar alguna exepction que sea lanzada
Para devolver un codigo de error en especifico, `ResposeEntity` es la entidad que tiene los tipos de error mapeadas dentro, hace un wrapper de esta respuesta y la retorna al cliente

</br>

##### - Error 404 not found

No encontrado

Porque tratar este error? Retornamos este error 404 en lugar de un 500 (Internal Server error) ya que es un error de cliente que da cuando insertas cosas invalidas o elementos vacios

- Para Regresar un **404** (not Found) decir que un algo no fue encontrado
  La anotación `@ExceptionHandler`, de Spring, para indicar qué excepción debe capturar un determinado método de la clase de manejo de errores
  
    ```java
        @RestControllerAdvice
        public class TratadorDeErrores {

            @ExceptionHandler(EntityNotFoundException.class)
            public ResponseEntity tratarError404(){
            return ResponseEntity.notFound().build();
            }
    }
    ```

> Para avisarle a Spring que el metodo que trata el error va a ser llamado en caso de una exception de algún tipo, Usamos ``@ExceptionHandler`` en el que le vamos a decir el tipo de exception que quiero tratar

##### Error 400 bad request

Mala Solicitud

En el caso de registrar(valaciones) esto puede ser diferente, nos regresa el error requerido pero el arreglo de errores que mostramos en el body es el problema
Siguiendo el método anterior resolveriamos el problema pero.. no se esta recibiendo sobre cual fue el parámetro que no se ingreso o se ingreso mal

Retornamos un body

```java
@ExceptionHandler(MethodArgumentNotValidException .class)
public ResponseEntity tratarError400(MethodArgumentNotValidException e){
    // obetenemos los campos de error de la exception pero mapeamos solo los que necesitamos
    List<DatosErrorValidacion> errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
    return ResponseEntity.badRequest().body(errores); // <--- mandamos los errores al body
}

private record DatosErrorValidacion(String campo, String error){ //DTO intnerno para usar para solo este metodo
    public DatosErrorValidacion(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
```

> La exception es el que contiene la lista de errores, los mensajes de error del bean Validator de esa exception

</br>

### Spring Security

Instalamos la dependencia referente a Spring Security

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```

Autenticación y Autorización

</br>

#### Autenticación

Credenciales que definen quien dices ser, el proceso mediante el cual tu aseguras que eres la persona que dices ser mediante un request

1. Envias un Request, un post hacia un API Rest con tus datos de login que tienes guardada en la base de datos
2. La Api busca si estan tus credenciales guardadas
3. Si tus credenciales son verdaderas y estan en la BD el te va a generar un **Json Web Token** JWT
4. Envia el JWT al cliente

De esta forma la aplicacion cliente tiene que enviar ese token como llave para el siguiente paso, la [autorizacion](#autorización)

> [!Note]
> A nivel API este tipo de autenticación no tiene estado (``State less``), no conoce que usuarios estan logeados o no, solo valida el proceso de autenticación y genera el JWT
> `State full` estado por defecto de aplicaciónes web, una sesion dentro de la aplicacion

</br>

#### Autorización

1. En JWT viene un header *Authorization* que es enviado en cada request que mi aplicacion-cliente va a hacer a mi backend
2. Dependiendo de si el token es válido o no, puede *liberar* el espacio o me puede *restringir* el acceso a este recurso

Hacer que todo el Request no sean publicos y requiran un tipo de autenticación

#### Hash de contraseña

Al implementar una funcionalidad de autenticación en una aplicación, independientemente del lenguaje de programación utilizado, deberá tratar con los datos de inicio de sesión y contraseña de los usuarios, y deberán almacenarse en algún lugar, como, por ejemplo, una base de datos.

Las contraseñas son información confidencial y no deben almacenarse en texto sin formato, ya que si una persona malintencionada logra acceder a la base de datos, podrá acceder a las contraseñas de todos los usuarios. Para evitar este problema, siempre se debe usar algún algoritmo hash en las contraseñas antes de almacenarlas en la base de datos.

**Hashing** no es más que una función matemática que convierte un texto en otro texto totalmente diferente y difícil de deducir.

Hay varios algoritmos hashing que se pueden usar para transformar las contraseñas de los usuarios, algunos de los cuales son más antiguos y ya no se consideran seguros en la actualidad, como MD5 y SHA1. Los principales algoritmos actualmente recomendados son:

- Bcrypt
- Scrypt
- Argon2
- PBKDF2

Algoritmo de Hashing

- Protección contra ataques (**CSRF** (Cross-site request forgery),**Clickjacking**)
    Protección contra ciber ataques
    CSRF (Cross-site request forgery) mas conocido como suplantacion de edenidad con cookies
    Clickjacking

---

##### Login

Cundo inciamos la aplicacion con Spring Security por primera vez e intentar entrar a la url por defecto de la app se creara por defecto un login de tipo **State full** que al logearse se crea un seccion dentro de la aplicación, Lo que hace Spring Boot es proteger todos nuestro datos creando un seccion *State Full*.

> El caso de API requiere otro tipo de trabajo, cambiar a *``State less``*

- Repository Service

Ya crada nuestras tabla en la base de datos, la entidad y el repository extendiendo de `JpaRespository` pora los usuarios, necesito mi capa intermedia llamada `Service`

- Capa ``Service``

El encargado de ejecutar la lógica de autenticacion de la aplicacion par que Spring tome ese servicio y busque en el repositorio por los usuarios

1. En el paquete `infra` necesitamos crear un servicio de autenticación de usuarios por lo que dentro craremos un nuevo paquete `security` con la clase `AutenticationService`
   Clase que  inplementara la interfaz `UserDetailsService` propia de spring que utiliza internamente para efectuar la autenticacion del user junto con sus métodos

2. `@Service` - El estereotipo *Service*, Esta anotación le dice a Spring que escanne esta clase porque es un servicio de esta aplicación

> ``@Autowired`` - Inyectar con autowired a nivel de campo para metodos de testing no es recomendado, puede ser [por un setter o un constructor](https://gustavopeiretti.com/spring-boot-anotacion-autowired/)

   ```java
    @Service
    public class AutenticacionService implements UserDetailsService {
    
        private UsuarioRepository usuarioRepository;
    
        @Autowired //inyectar la dependencia por constructor
        public AutenticacionService(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }
        //de que forma yo voy a cargar ese usuario y de donde
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return usuarioRepository.findByLogin(username);
        }
    }
    
    ///////REPOSITORY de usuario en el paquete domain.usuarios ///////
    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        UserDetails findByLogin(String username); // metodo personalizado para hacer consultas SQL personalizado en spring data
    }
   ```

> [!Important]
> Spring Data usa su propio patrón de nomenclatura de métodos que debemos seguir para que pueda generar consultas SQL correctamente.
> Hay algunas palabras reservadas que debemos usar en los nombres de los métodos, como findBy y existBy, para indicarle a Spring Data cómo debe ensamblar la consulta que queremos. Esta característica es bastante flexible y puede ser un poco compleja debido a las diversas posibilidades existentes.
> Para conocer más detalles y comprender mejor cómo ensamblar consultas dinámicas con Spring Data, acceda a su [documentación oficial](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/).

</br>

##### Configuración de Autenticacion

1. En el paquete `infra.security` creamos la clase para configuracion **`SecurityConfigurations`**

[**``@Configuration``**](https://gustavopeiretti.com/spring-boot-anotacion-bean/) - se utiliza en Spring Boot para indicar que una clase contiene una o más definiciones bean.
Cuando una clase está anotada con *@Configuration*, se trata como una fuente de definiciones de beans, y cualquier método dentro de la clase que esté anotado con *@Bean* se utilizará para crear y configurar beans.

[**`@Bean`**]((https://gustavopeiretti.com/spring-boot-anotacion-bean/)) - Es una anotación a nivel de método que se utiliza para declarar un bean y registrarlo en el contenedor de Spring.
Un bean es un objeto gestionado por el framework Spring y que puede utilizarse para proporcionar servicios o funcionalidades a otras partes de la aplicación.

Cuando una clase está anotada con @Configuration, se trata como una fuente de definiciones de beans, y cualquier método dentro de la clase que esté anotado con *@Bean* se utilizará para crear y configurar beans.

**`@EnableWebSecurity`** - Para decir que los métodos sobreescriban el comportamiento de actenticacion que queremos

> **CSRF** (Cross-site request forgery) mas conocido como suplantacion de edenidad con cookies, si estas implementado de una aplicacion web llamar al método csrf qe proporcionara una proteccion

```java
    @Configuration //Archivo de configuracion
    @EnableWebSecurity//Habilita web security, Para decir que los métodos sobreescriban el comportamiento de actenticacion que queremos
    public class SecurityConfigurations {
    
        //AL RETORNAR IMPLEMENTA UN TIPO DE AUTENTICACION STATE LESS
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    /*        return httpSecurity.csrf().disable().sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   .and().build();    */
    //como va a ser configuracion  via token no tengo que tener proteccion CSRF
            return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(
                            httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    
        }
    }
```

- Controller de Autenticación

1. Creamos una clase controller llamada `AutenticacionController`

    ```java
        @RestController
        @RequestMapping("/login")
        public class AutenticacionController {
        
            @Autowired
            private AuthenticationManager authenticationManager; //interfaz que necesita un bean ya que existe uno
        
            @PostMapping
            public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
                //disparar el proceso de autenticacion en spring
        
                Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
                authenticationManager.authenticate(token);
                return ResponseEntity.ok().build();//fines didacticos
            }
        }
    ```

2. Agregamos `AutenticacionManager` en la clase de configuracion `SecurityConfigurations` junto con su **@Bean** necesario

```java
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
```

##### Guardar usuarios en base de datos

Disparar la request y hacer la validades de login en la base de datos

- Especificar que atributo va a usar como username, cual es el que va a comparar como clave y cual es el algoritmo de Hashing que hemos usado
    1. En la Clase de configuraciones de spring `SecurityConfigurations` de *infra.security* agregamos el método que especifica el tipo de hashing que usamos en este caso **Bcrypt**

```java
    @Bean //para que este diponible en el contexto de Spring
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
```

2. Y en la entidad Usuario implementamos `UserDetails` junto con sus clases las cuales tendremos que modificar unas cosas, valores a true y especificar el usuario y password que utilizara para comparar en la base de datos

```java
    
    ///...... ETIQUETAS  //
    public class Usuario implements UserDetails { //Interfaz
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String login;
        private String clave;
    
        @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Authorities: el rol que va a tener este usuario dentro de la app
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); //Role_user va a ser por defecto los que entren aqui
    }
    
        @Override
        public String getPassword() { //Asignamos el password
            return clave;
        }
    
        @Override
        public String getUsername() { //Asignamos el user
            return login;
        }
    
        @Override
        public boolean isAccountNonExpired() { //a true
            return true;
        }
    
        @Override
        public boolean isAccountNonLocked() { //a true
            return true;
        }
    
        @Override
        public boolean isCredentialsNonExpired() { //a true
            return true;
        }
    
        @Override
        public boolean isEnabled() { //a true
            return true;
        }
}
```

#### JsonWebToken

JSON Web Token (JWT) es un estándar para transmitir información de forma segura en internet, por medio de archivos en formato JSON, que es un tipo de archivo de texto plano con el cual se pueden crear parámetros y asignarles un valor.

- Agregamos la libreria `Auth0` para generar JsonWebToken
  
```xml
    <!-- auth0 para generar JsonWebToken -->
    <dependency>
      <groupId>com.auth0</groupId>
      <artifactId>java-jwt</artifactId>
      <version>4.4.0</version>
    </dependency>
```

- Generar tokens JWT

En el paquete de `infra.security` creamos una clase *TokenService*

En el token es muy comun que tenga fecha de validez (pueden ser 2 horas, un minuto, un dia entero)

```java
    @Service //Se scanne como servicio
    public class TokenService {
    
        public String generarToken(Usuario usuario){
            try {
                Algorithm algorithm = Algorithm.HMAC256("123456"); // Algoritmo HMAC256 pide un string o arreglo de bytes, secret para validar la firma 
                return JWT.create()
                        .withIssuer("voll med") //issuer el que emite el jwt
                        .withSubject(usuario.getLogin()) //usuario a quien va dirigido
                        .withClaim("id", usuario.getId()) //si la app cliente necesita conocer el id del usuario
                        .withExpiresAt(generarFechaExpiracion()) //para hacer que el token expire en 2 horas
                        .sign(algorithm);
            } catch (JWTCreationException exception){
                throw new RuntimeException();
            }
        }
    
        private Instant generarFechaExpiracion(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
        }
    }
```

Llamados a nuesto JWT en nuestro controller

```java
    public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager; //interfaz que necesita un bean ya que existe uno

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        //disparar el proceso de autenticacion en spring
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.clave());
        authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken();
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));//fines didacticos
    }
}

    /////DTO record en infra.security//
    
    public record DatosJWTToken(String jwTtoken) {
    
    }
```

> [!Importan]
> Si en todo tu estandar de API el estandar es consumir DTO y devolver DTO eso debe aplicar para todos los métodos que estas usando, para codigo mas limpio y mas entendible para la aplicacion cliente

- En ``application.properties`` decimos cual es el seccret por uso de *Variables de ambiente*
  1. En la terminal creamos la variables `export JWT_SECRET=123456`
  2. `echo $JWT_SECRET` dara el valor asignado a esa variable
  
```properties
    api.security.secret=${JWT_SECRET}
```

- Consumimos el valor de esa properties

```java
@Service //Se scanne como servicio
public class TokenService {

    @Value("${api.security.secret}") //value de bean spring //obtener el valor de mi propiedad que quiero extraer el valor,ruta
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // <----------- secret para validar la firma
            return JWT.create()
                    .withIssuer("voll med") //issuer el que emite el jwt
                    .withSubject(usuario.getLogin()) //usuario a quien va dirigido
                    .withClaim("id", usuario.getId()) //si la app cliente necesita conocer el id del usuario
                    .withExpiresAt(generarFechaExpiracion()) //para hacer que el token expire en 2 horas
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
```

- el ide no tiene acceso a las variables del sistema
  > Si no escuentra la variable, por defecto que use 123456 pero El standar es que en produccion tenga un secret

  ```properties
        api.security.secret=${JWT_SECRET:123456}
    ```

#### Control de acceso

Todo request tiene que ir acompañado de un token valido, mientras no expire tiene que ser aceptado nuestro request

**Filter** es una de las características que componen la especificación Servlets, que estandariza el manejo de solicitudes y respuestas en aplicaciones web en Java. Es decir, dicha función no es específica de Spring y, por lo tanto, puede usarse en cualquier aplicación Java.

Es una característica muy útil para aislar códigos de infraestructura de la aplicación, como por ejemplo, seguridad, logs y auditoría, para que dichos códigos no se dupliquen y se mezclen con códigos relacionados con las reglas comerciales de la aplicación.

Para crear un Filter, simplemente cree una clase e implemente la interfaz `Filter` en ella (paquete jakarta.servlet). Por ejemplo:

```java
@WebFilter(urlPatterns = "/api/**")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Requisição recebida em: " + LocalDateTime.now());
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
```

El método doFilter es llamado por el servidor automáticamente, cada vez que este filter tiene que ser ejecutado, y la llamada al método filterChain.doFilter indica que los siguientes filters, si hay otros, pueden ser ejecutados. La anotación @WebFilter, agregada a la clase, indica al servidor en qué solicitudes se debe llamar a este filter, según la URL de la solicitud.

##### Filter utilizando recursos de Spring

- Security filtrer

En el paquete `infra.security` creamos la clase *SecurityFilter*

- obteniendo el token

por estandar el token llega en un header especifico llamado Authorization
Por estandar el token simpre va tener el prefijo *Bearer*

> [!Note]
> El nombre del header es Authorization

```java
@Component // Estereotipo mas generico de Spring para definir un component
public class SecurityFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header que por estandar se llama Authorization
        var token = request.getHeader("Authorization").replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
        System.out.println(token);
        //UNICA FORMA DE HACER EL doFilter si el filtro no llama especificamente al siguiente no va a suceder
        filterChain.doFilter(request, response); //filtro ejecuta esto y mandale el request y response que esta llegando del html
    }
}
```

si el token es incorreco o viene un string vacio

```java
@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header que por estandar se llama Authorization
        var token = request.getHeader("Authorization");
        if (token  == null || token == ""){
            throw new RuntimeException("El token enviado no es valido");
        }
        token = token.replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
        //////////
    }
```

- validar el token

Si esta expirado y si esta asignado al usuario que a iniciado secion

el **Subject** es para quien este subject a sido generado

Obtenemos el nombre de ususario y obtener si en efecto a iniciado o no a iniciado secion en mi sistema

Insertamos un metodo en `TokenService`

```java
public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //validando la firma
            verifier = JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verify invalido");
        }
        return verifier.getSubject();
    }
```

en `SecurityFilter` lo llamamos, con lo cual lo declaramos

```java
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header que por estandar se llama Authorization
        var token = request.getHeader("Authorization");//.replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
        if (token  == null || token == ""){
            throw new RuntimeException("El token enviado no es valido");
        }
        token = token.replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
        System.out.println(token);
        System.out.println(tokenService.getSubject(token));//este usuario tiene secion
        //UNICA FORMA DE HACER EL doFilter si el filtro no llama especificamente al siguiente no va a suceder
        filterChain.doFilter(request, response); //filtro ejecuta esto y mandale el request y response que esta llegando del html
    }
```

</br>

- Decirle a Spring que agrege mi filtro antes que el filtro dde Spring por defecto, lo hacemos en ``TokenService``

```java
////anterior codigo
.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //agregar un filtro antes que el de spring valida al usuario si existe y esta autenticado
                .build();
```

en **``SecurityFilter``** quedaria asi

```java
@Component // Estereotipo mas generico de Spring para definir un component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header que por estandar se llama Authorization
        System.out.println("este es el inicio del filter");
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
        if (authHeader != null){
            var token = authHeader.replace("Bearer ", ""); //retorna header de authorization y el bearer por vacio, solo retorna token
            var nombreUsuario= tokenService.getSubject(token);
            if (nombreUsuario != null){
                //toquen valido
                var usuario = usuarioRepository.findByLogin(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());//forzamos inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        //UNICA FORMA DE HACER EL doFilter si el filtro no llama especificamente al siguiente no va a suceder
        filterChain.doFilter(request, response); //filtro ejecuta esto y mandale el request y response que esta llegando del html
    }
}
```

y nuestro SecurityConfigarations asi:

```java
@Configuration //Archivo de configuracion
@EnableWebSecurity//Habilita web security, Para decir que los métodos sobreescriban el comportamiento de actenticacion que queremos
public class SecurityConfigurations { //clase con la configuración de seguridad de la API: sobreesciribir la configuracionpor defecto de Spring


    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers(
                                                HttpMethod.POST,"/login").permitAll().anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //agregar un filtro antes que el de spring valida al usuario si existe y esta autenticado
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean //para que este diponible en el contexto de Spring
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
```

La clase TokenService quedaría asi:

```java
@Service //Se scanne como servicio
public class TokenService {  //CLASE ENCARGADA DE GENERAR LOS TOKENS

    @Value("${api.security.token.secret}") //value de bean spring //obtener el valor de mi propiedad que quiero extraer el valor
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Algoritmo HMAC256 pide un string o arreglo de bytes, secret para validar la firma
            return JWT.create()
                    .withIssuer("API Voll.med") //issuer el que emite el jwt
                    .withSubject(usuario.getLogin()) //usuario a quien va dirigido
                    .withClaim("id", usuario.getId()) //si la app cliente necesita conocer el id del usuario
                    .withExpiresAt(generarFechaExpiracion()) //para hacer que el token expire en 2 horas
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el  token jwt", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //validando la firma
            verifier = JWT.require(algorithm)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verify invalido");
        }
        return verifier.getSubject();
    }


    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
```

</br>

##### Control de acceso por url

Perfiles de acceso para los usuarios, esta característica se usa en algunas aplicaciones y podemos indicarle a Spring Security que solo los usuarios que tienen un perfil específico pueden acceder a ciertas URL.

Por ejemplo, supongamos que en nuestra aplicación tenemos un perfil de acceso llamado ADMIN, y solo los usuarios con ese perfil pueden eliminar médicos y pacientes. Podemos indicar dicha configuración a Spring Security cambiando el método securityFilterChain, en la clase SecurityConfigurations, de la siguiente manera:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

indicando a Spring Security que las solicitudes de tipo DELETE de las URL /médicos y /pacientes solo pueden ser ejecutadas por usuarios autenticados y cuyo perfil de acceso es ADMIN.

##### Control de acceso por anotaciones

Otra forma de restringir el acceso a ciertas funciones, según el perfil del usuario, es usar una función de Spring Security conocida como Method Security, que funciona con el uso de anotaciones en los métodos:

```java
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detallar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalladoMedico(medico));
    }
```

El método se anotó con @Secured("ROLE_ADMIN"), de modo que sólo los usuarios con el rol ADMIN pueden activar solicitudes para detallar a un médico. La anotación @Secured se puede agregar en métodos individuales o incluso en la clase, lo que sería el equivalente a agregarla en todos los métodos.

> [!Note]
>¡Atención! Por defecto esta característica está deshabilitada en Spring Security, y para usarla debemos agregar la siguiente anotación en la clase Securityconfigurations del proyecto:
>
> ```java
> @EnableMethodSecurity(securedEnabled = true)    
>```

Mas detalles sobre la funcion de seguridad del método en la comuntacion de Spring Security, [Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html)