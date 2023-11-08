# Java y java.lang

Java.lang hace referencia a Java Language y es el paquete base en el cual vamos a traer todos los objetos que necesitemos par poder contruir una aplicación, como la base de todo, paquete base de java que nos da Java a los desarrolladores.

Java disponibiliza todos los archivos de la clase lang en cualquier paquete que lo quieras usar sin necesidad de importarlo explicitamente o llamarlo del nombre completo

## Paquetes

Para hacer que una clase sea más fácil de localizar y utilizar así como evitar conflictos de nombres y controlar el acceso a los miembros de una clase, las clases se agrupan en paquetes.

Paquete

Un paquete es un conjunto de archivos o de otros paquetes también, asi como clases e interfaces relacionadas Por el cual vamos a tener cierta jerarquia
La forma general de la declaración package es la siguiente: ``package nombrePaquete;``
Donde ``nombrePaquete`` puede constar de una sola palabra o de una lista de nombres de paquetes separados por puntos.

La declaracion de un `package`, la direccion de este mismo va en la linea 1, asi que toda clase pertenece a un paquete.

```Java
        package miPaquete;  //paquete y nombre del paquete
        
        class MiClase{
          ...
        }
      //////  
        package nombre1.nombre2.miPaquete; //lista de nombre de paquetes separados por puntos
        
        class TuClase{
      
        }
```

Los nombres de los paquetes se corresponden con nombre de directorios en el sistema de archivos.

</br>

Todo archivo que este dentro de un paquete y quiera acceder a otro archivo que esta dentro de otro paquete, este tiene que conocer el nombre especifico/direccion de ese archivo.

En java si una clase esta dentro de un paquete su nombre de direccion esta compuesto por:
`nombrePaquete.nombreDeLaClase`, `package.className`

```java
//Ejemplo  package + classname
    package modelo.Funcionario
```

¿Qué es FQN?

- FQN (Full Qualified Name) es el nombre completo de la clase, que consta del nombre del paquete y el nombre de la clase.
FQN = ``Nombre del paquete. Nombre de clase simple``

</br>

Cuando se requiera hacer uso de estas clases se tendrán que importar de la siguiente manera.

```java
        import miPaquete.MiClase;
        import nombre1.nombre2.miPaquete.TuClase;
        
        class OtraClase
        {
           /* Aqui se hace uso de la clase 'Miclase' y de la
           clase 'TuClase' */
        }
```

Para importar todas las clases que están en un paquete, se utiliza el asterisco  *

Ejemplo

```Java
import miPaquete.*;  //importamos todas las clases del paquete
```

</br>

Si no se utiliza la sentencia package para indicar a que paquete pertenece una clase, ésta terminará en el package por default, el cual es un paquete que no tiene nombre.

### Consejos

- Crear un package `modelos` o `models`
Para clases que representan a cada entidad de nuestro negocio (un cliente, contador, funcionario...)

- Como buena practica, Si se esta haciendo un proyecto para un pais en especifico hay una convencion que dice que para nombrar packages necesitamos empezar por el pais (la empresa tiene un sitio web con la dirección de brazil por ejemplo), despues dependiendo si es organizacion o compañia hay un diminutivo a agregar  `com` o  `org`, ya siguiente el nombre de la empresa como tal.
Por organización y por la convención adoptada, debemos seguir el dominio de la empresa. Es decir, si la empresa tiene el dominio bytebank.com.br, los paquetes deben ser subpaquetes de `br.com.bytebank`

Ejemplo:
`br.com.bytebank.nombrePackage`
`mx.org.bytebank.nombrePackage`
`arg.com.bytebank.nombrePackaee`

**Ejemplo de problematica**
Pedro trabaja en la empresa Uberdisth en el proyecto con el sobrenombre de udnotas. Pedro también mencionó que la empresa tiene un sitio web con la dirección "uberdisth.com.br."

Pedro tiene dudas sobre la nomenclatura del package. ¿Qué nombre tendría más sentido y se considera una buena práctica?

R = ``br.com.uberdisth.udnotas`` o `br.com.uberdisth.namePackage` siguiendo la regla ``NOMBRE_DEL_SITE_AL_REVES.NOMBRE_DEL_PROYECTO``

</br>

## Documentación Javadoc

Normalmente la documentación se añade a todos los atributos públicos o en su defecto protected, porque la documentación siempre la añadimos a nivel de los métodos que son accesibles y utilizables por los demás elementos del proyecto, del aplicativo

Para hacer este tipo de documentacion se debe utilizar `/**   */`

A todo esto se le llama **Javadoc**, es el nombre que Java le dio al tipo de documentacion dentro del codigo.

- Hay que tener en cuenta que no es una documentacion para usuario final, es una documentacion tecnica, de programador a programdor
- Se debe tener instalado el JDK para poder generar la documentación de javadoc.

</br>

Lista de tag (o anotacionaes) de javaddoc

- @deprecated  =  que ya no esta en uso, no esta retirado pero en el futuro sera removido
- @author (usado en la clase o interfaz)
- @version
- @param (usado en el método y constructor)
- @return (usado solo en el método)
- @exception o @throws (en el método o constructor)
- @see
- @since
- @serial

Es importante que las etiquetas `javadoc` existan solo para estandarizar algunos datos fundamentales de su código fuente, como el autor y la versión.

## Archivos Jar

Un archivo .jar puede ser un archivo de aplicación de Java, es decir, un programa que, como tal, puede ser ejecutado.

Es un formato de archivo de paquete que generalmente se usa para agregar muchos archivos de clase Java y metadatos y recursos asociados (texto, imágenes, etc.) en un archivo para distribuir software de aplicación o bibliotecas en la plataforma Java. En palabras simples, un archivo JAR es un archivo que contiene una versión comprimida de archivos .class, archivos de audio, archivos de imagen o directorios, muy parecido a los archvos .zip

### Libreria

Un conjunto de clases exportadas en un Jar

- Por convencion se crea una carpeta llamada `lib` para las libreria a la altura de `src` como hermanas

#### Dependencias

Llamamos a toda libreria externa a nuestro proyecto, toda libreria que no sea escrita por nosotros es una dependencia y existen herramientas para administrar dependencias (Maven por ejemplo).

</br>

## String

String no es un tipo de dato, es un objeto.
Se puede crear tanto utilizando `new` o comillas dobles `""`, pero hay una diferencia entre crear con la palabra reservada new y crear con comillas dobles. El uso de comillas dobles es una buena práctica ya que la JVM puede realizar varias optimizaciones bajo el capó.

### Clase string

#### Inmutabilidad

Todo string es inmutable

El string nace y nunca es más modificado hasta el momento en el que es recolectado por el garbage collector, osea que nace y muere de la misma forma, nunca mas es modificado o alterado.

```Java
        .replace() //rewmplaza Caracteres o secuencia de caracteres, recordando en otra variable
        .substring(1);  //elimina los caracteres antes del indice
        .toUpperCase() //convierte a Mayusculas
        .toLowerCase() //conviente a Minuscula

```

### Interfaz charSequence

El tipo CharSequence es una interfaz que la propia clase String implementa (¡ya que String es una secuencia de caracteres!):

```java
            public class String implements CharSequence {

            }
```

Cuando usamos la clase String, incluso podríamos declarar la variable con el tipo de interfaz, pero eso es raro de ver:

```Java
        CharSequence seq = "es una secuencia de caracteres";
```

### Clase StringBuilder

La clase StringBuilder es similar a la clase String en el sentido de que sirve para almacenar cadenas de caracteres. No obstante, presenta algunas diferencias relevantes.

- Su tamaño y contenido pueden modificarse. Los objetos de éste tipo son **mutables**. Esto es una diferencia con los String.

- Debe crearse con alguno de sus costructores asociados. No se permite instanciar directamente a una cadena como sí permiten los String.

- Un StringBuilder está indexado. Cada uno de sus caracteres tiene un índice: 0 para el primero,1 para el segundo, etc.

- Los métodos de StringBuilder no están sincronizados. Esto implica que es más eficiente que StringBuffer siempre que no se requiera trabajar con múltiples hilos (threads), que es lo más habitual.

El constructor por defaul construye un StringBuilder vacío y con una capacidad por defecto de 16 carácteres.

```Java
        StringBuilder() //16 caracteres

        StringBuilder(int capacidad)  //Se le pasa la capacidad (número de caracteres) como argumento

        StringBuilder(String str) //Construye un StringBuilder en base al String que se le pasa como argumento. 
        
        ///////////////
        ////EJEMPLO////
        StringBuilder builder = new StringBuilder("Ayuda");
        builder.append("-"); //adjunta un caracter o strings, conjunto de caracteres
        builder.append("me ");
        builder.append("subi ");
        builder.append("en el ");
        builder.append("omnibus ");
        builder.append("equivocado ");
        String texto = builder.toString();
        System.out.println(texto);
```

## Clase Object

Es la clase padre de todo, es la clase maxima en el lenguaje de Java
Todos son objetos y de la clase que sea, todos extienden de `Object`

Si crear queremos que un método que reciba cualquier n tipo de parametro, tengo a una super clase, que es el padre de todas las clases que es la la clase `Obejct`

```Java
        public static void printLine(Object valor){
        System.out.println(valor);
    }
```

### Método `toString`

El método toString debería devolver información sobre el estado del objeto. Es útil para depurar en desarrollo.

- Es una buena práctica sobrescribir el método para darle un significado mayor que el resultado estándar de ese método.

```Java
        @Override
        public String toString() {
            return  "saldo= " + this.saldo +
                    ", agencia= " + this.agencia +
                    ", numero= " + this.numero +
                    ", titular= " + this.titular +
                    '}';


        ///Main///
        Object cc = new CuentaCorriente(22,11);
        Object cp = new CuentaCorriente(33,22);

        System.out.println(cc);
        System.out.println(cp);
```

```txt
        ///Salida///
        CuentaCorriente, saldo= 0.0, agencia= 22, numero= 11, titular= br.com.banco.bytebank.modelo.Cliente@4e50df2e
        CuentaCorriente, saldo= 0.0, agencia= 33, numero= 22, titular= br.com.banco.bytebank.modelo.Cliente@7291c18f
```

___

## Enumeraciones (ENUM)

Son los tipos de datos especiales donde una variable puede contener el valor de un grupo de constantes predefinidas.

Basicamente **es un conjunto de constantes que se las asocia a un tipo `enum`.** En lugar de `class`
utilizamos la palabra clave `enum` y entre llaves definimos las constantes, por regla es escrita en mayúsculas.

¿Cuando usar un tipo enums?

Cundo quieras representar un conjunto fijo de constantes, incluye enumeraciones de tipo natural como planetas del sistema solar y conjunto de datos en donde se conoce el valor de cada constante en tiempo de compilación como las opciones de un menú, los colores o los dias de la semana.

```java

//Sintaxis y como usar
    public enum Continentes {

        AFRICA(53),
        EUROPA(46),
        ASIA(59),
        AMERICA(34),
        OCEANIA(44);
    
        private final int paises;
    
        Continentes(int paises) {
            this.paises = paises;
        }
    
        public int getPaises() {
            return this.paises;
        }
}
```

> Para acceder a los datos de la enumeracion, basta con solo escriber el nombre y el valor que deseamos, sin instanciar.

</br>

## Bloques de Codigo en java

El bloque de inicialización es un fragmento de código de ejecución fijo que no acepta ningún parámetro. Por lo tanto, si un fragmento de código de inicialización es el mismo para todos los objetos y no necesita recibir ningún parámetro, puede colocar este fragmento de código de inicialización en el bloque de inicialización.

Se ejecutan antes del constroctor, antes que los bloques de inicialización ordinarios y se mandan a llamar antes de nuestro constructor de nuestra clase.

```java
///  Tenemos una clase  ///
public class Persona {

    private final int idPersona;
    private static int contadorPersonas;

}
```

- **Bloques de inicialización estáticos**

El bloque de inicialización definido con el modificador estático se denomina **bloque de inicialización estático**, también denominado **bloque de inicialización de clase** (No se puede usar el operador `this`).

El bloque de inicialización normal es inicializar el objeto, y el bloque de inicialización de clase es **inicializar la clase**.

Se va a ejecutar condo se carga por primera vez en memoria lal clase.

```java
static{
        ////   Se va a ejecutar la primera vez cuando se carga la clase   ////
        System.out.println("Ejecición bloque estatico");
        ++Persona.contadorPersonas;
        //idPersona = 10; No se puede agregar contextos estaticos a uno que es estatico
    }

```

</br>

- **Bloques de inicialización no estáticos**

Se le conoce como contexto no dinámico, se crea caundo inicializamos un nuevo objeto e que inicializa el objeto.

Se puede usar el operador `this`.

```java
    {  ///   Se va a ejecutar cada vez que creemos un objeto de nuestra clase   ///
        System.out.println("Ejecucion bloque no estatico");
         this.idPersona = Persona.contadorPersonas++;
    }
```

> [!Note]
> Cuando Java crea un objeto, primero asigna memoria y luego instala el orden de inicialización para especificar el valor inicial, los bloques de inicialización estática siempre se ejecutan antes que los bloques de inicialización ordinarios