# Patrones y Padrones de diseño

Solución a una problematica que se presenta frecuentemente. Nos ayudara con una descripción de como resolver dicha problematica aplicando las mejores practicas de programación. Fueron creados por programadores de mucha esperiencia, que al pasar de los años notaron dichas problematicas eran constantes en el desarrollo.

Patrones de diseño escritos en el [libro ....](https://wiki.c2.com/?DesignPatternsBook)

## Patrones

### Patrones creacionales

- **Abstract Factory** Provee una interfaz para la creación de familias de objetos sin especificar una clase en concreta.

- **Factory Method** Define una interfaz para la creación de un objeto, pero deja que la subclase decida que clase instanciar.

- **Builder** Separa la construcción de objetos complejos.

- **Prototype** Especifica que tipo de objetos crear usando una instancia prototipo y crea nuevos objetos copiando este prototipo.

- **Singleton** Nos asegura que una clase solo puede ser instanciada una vez, además de proveer un punto de acceso a esta.

</br>

### Patrones estructurales

- **Adapter** Convierte la interfaz de una clase en otro interfaz que el cliente espera.

- **Bridge** Nos permite desacoplar una abstracción de su implementación, de manera que ambas puedan ser modificadas independientemente sin necesidad de alterar por ello la otra.

- **Composite** sirve para construir objetos complejos a partir de otros más simples y similares entre sí, gracias a la composición recursiva y a una estructura en forma de árbol.

- **Decorator** Agrega responsabilidades adicionales a un objeto de forma dinámica.

- **Facade** Nos permite utilizar módulos complejos de una forma sencilla y con bajos costos para el cliente.

- **Flyeight** Nos permite eliminar o reducir redundancia cuando trabajamos con una gran cantidad de objetos.

- **Proxy** Permite controlar el acceso a diferentes áreas de módulos.

</br>

### Patrones de comportamiento

- **Chain of Responsibility** Evita acoplar el emisor de una petición a su receptor dando a más de un objeto la posibilidad de responder a una petición. Para ello, se encadenan los receptores y pasa la petición a través de la cadena hasta que es procesada por algún objeto.

- **Command** Permite solicitar una operación a un objeto sin conocer realmente el contenido de esta operación, ni el receptor real de la misma. Para ello se encapsula la petición como un objeto, con lo que además facilita la parametrización de los métodos.

- **Interpreter** Dado un idioma, define una representación para su gramática junto con un intérprete que use la representación para interpretar oraciones en el lenguaje.

- **Iterator** Define una interfaz que declara los métodos necesarios para acceder secuencialmente a un grupo de objetos de una colección.

- **Mediator** Define un objeto que encapsula cómo un conjunto de objetos interactúan.

- **Memento** Permite almacenar el estado de un objeto (o del sistema completo) en un momento dado de manera que se pueda restaurar en ese punto de manera sencilla. Para ello se mantiene almacenado el estado del objeto para un instante de tiempo en una clase independiente de aquella a la que pertenece el objeto (pero sin romper la encapsulación), de forma que ese recuerdo permita que el objeto sea modificado y pueda volver a su estado anterior.

- **Observer** Define una dependencia del tipo uno a muchos entre objetos, de manera que cuando uno de los objetos cambia su estado, notifica este cambio a todos los dependientes.

- **State** Se utiliza cuando el comportamiento de un objeto cambia dependiendo del estado del mismo.

- **Template** Method Define el esqueleto de programa de un algoritmo en un método, llamado método de plantilla, el cual difiere algunos pasos a las subclases

- **Visitor** Representa una operación que se realiza sobre los elementos que conforman la estructura de un objeto.

---

#### Patron DAO

El patrón de diseño DAO, también conocido como Data Access Object, se utiliza para la persistencia de datos, donde su objetivo principal es separar las reglas de negocio de las reglas de acceso a la base de datos. En las clases que siguen este patrón, aislamos todos los códigos que se ocupan de conexiones, comandos SQL y funciones directas a la base de datos, para que dichos códigos no se esparzan a otras partes de la aplicación, algo que puede dificultar el mantenimiento del código y también el intercambio de tecnologías y del mecanismo de persistencia.

##### Implementación

Supongamos que tenemos una tabla de productos en nuestra base de datos. La implementación del patrón DAO sería la siguiente:

Primero, será necesario crear una clase básica de dominio ``Producto``:

```JAVA
    public class Producto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;

    // constructores, getters y setters
}
```

A continuación, necesitaríamos crear la clase ProductoDao, que proporciona operaciones de persistencia para la clase de dominio Producto:

```JAVA
    public class ProductoDao {

    private final EntityManager entityManager;

    public ProductoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Producto producto) {
        entityManager.persist(producto);
    }

    public Producto read(Long id) {
        return entityManager.find(Producto.class, id);
    }

    public void update(Producto producto) {
        entityManger.merge(producto);
    }

    public void remove(Producto producto) {
        entityManger.remove(producto);
   }

}
```

#### Patrón DTO

Uso de records Java

El patrón DTO (Data Transfer Object) es un patrón arquitectónico que se usó ampliamente en aplicaciones Java distribuidas (arquitectura cliente/servidor) para representar los datos que eran enviados y recibidos entre aplicaciones cliente y servidor.

El patrón DTO puede (y debe) usarse cuando no queremos exponer todos los atributos de alguna entidad en nuestro proyecto, una situación similar a los salarios de los empleados. Además, con la flexibilidad y la opción de filtrar qué datos se transmiten, podemos ahorrar tiempo de procesamiento.

---

## Padrón

### Padrón Repository (spring)

Según el famoso libro Domain-Driven Design de Eric Evans:

``
    El repositorio es un mecanismo para encapsular el almacenamiento, recuperación y comportamiento de búsqueda, que emula una colección de objetos.
``

En pocas palabras, un repositorio también maneja datos y oculta consultas similares a DAO. Sin embargo, se encuentra en un nivel más alto, más cerca de la lógica de negocio de una aplicación. Un repositorio está vinculado a la regla de negocio de la aplicación y está asociado con el agregado de sus objetos de negocio, devolviéndolos cuando es necesario.

Pero debemos estar atentos, porque al igual que en el patrón DAO, las reglas de negocio que están involucradas con el procesamiento de información no deben estar presentes en los repositorios. Los repositorios no deben tener la responsabilidad de tomar decisiones, aplicar algoritmos de transformación de datos o brindar servicios directamente a otras capas o módulos de la aplicación. Mapear entidades de dominio y proporcionar funcionalidades de aplicación son responsabilidades muy diferentes.

Un repositorio se encuentra entre las reglas de negocio y la capa de persistencia:

1. Proporciona una interfaz para las reglas comerciales donde se accede a los objetos como una colección;
2. Utiliza la capa de persistencia para escribir y recuperar datos necesarios para persistir y recuperar objetos de negocio.

El patrón de repositorio fomenta un diseño orientado al dominio, lo que proporciona una comprensión más sencilla del dominio y la estructura de datos. Además, al usar el repositorio de Spring, no tenemos que preocuparnos por usar la API de JPA directamente, simplemente creando los métodos, que Spring crea la implementación en tiempo de ejecución, lo que hace que el código sea mucho más simple, pequeño y legible.
