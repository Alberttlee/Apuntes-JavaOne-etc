# Orientacion a  objetos

Es un paragdima de programación en el cual intentamos ___encapsular y abstraer diferentes funcionalidades de nuestro código___ de forma que podamos ___reutilizarlo___ y sea mas entedible para cualquier programador.

</br>

__Corner case__:

- Los corner case son la ruina de la programación informática. Suelen ser donde encuentras errores. Muy a menudo, en el procesamiento de un archivo, el primer y el último registro deben procesarse especialmente en algún mismo. Estos no se llamarían casos de esquina, ya que el código se ejercita cada vez que ejecuta los programas. Los casos de esquina incluirían:

  - ¿Funciona este código para la cadena vacía , nula , 0 o un conjunto de 0 elementos?
  - ¿Funciona este código para Integer . MIN_VALUE y Entero . MAX_VALOR ?
  - ¿Funciona este código si hay valores duplicados?
  - ¿Funciona este código para byte s con el bit alto activado?
  - Si el código exige puntos, ¿qué sucede si tres de los puntos son colineales?

__`Incluso los programadores famosos pueden volverse locos por los corner case.`__

__¿Qué son los Getters y setters?__

- Los métodos que permiten acceder al valor de un atributo se denominan `getters` (del verbo inglés "get", obtener) y los que fijan el valor de un atributo se denominan `setters` (del verbo inglés "set", fijar).

__¿Que es un objeto?__

Representacion de un conjunto de atributos que definen alguna entidad o un tipo.

- Ejemplo: Un auto.
  - Tiene disntos tipos de atributos como marca, color, año, etc.

## Programación orientada a objetos

Se basa en el concepto de agrupar código y datos juntos dentro de una misma unidad llamada __clase.__

Sus principales caracteristicas son:

- Abstracción
- Encapsulamiento
- Polimorfismo
- Herencia
- UML

### Clase

- Palabra reservada de java que sirve para representar un tipo, una entidad, un objeto, representa una cosa, una abstraccion, un conjunto que va a representar una cosa.
- Una clase es el modelo a partir del cual se crean los objetos individuales
- una clase Java no es más que un molde o receta con el vas a crear un esquema del objeto que quieres instanciar.
- Un objeto es una instancia de una clase donde podemos definir valores para sus `atributos.`
- Es una plantilla,  o molde que permite construir objetos.
  - Por ejemplo, si tuvieramos un Auto, ésta sería el plano para construir el Objeto (en este caso uno o varios autos).
- Poseen atributos y métodos.
- No pueden se utilizadas directamente (sino mediante instancias a objetos).

Dentro de una clase se definen los datos y el código que actúa sobre esos datos. El código está contenido en métodos. Debido a que las clases, los objetos y los métodos son fundamentales para Java.

</br>

__Atributo:__ En orientacion a objeto llamamos atributo a las caracteristicas de una clase.  

</br>

## 1. Abstracción

La abstracción describe el proceso mediante el cual se da forma a una entidad de la realidad tangible para ponerla en un código que maneje pautas basadas en estas características junto con funciones que representen este tipo de clases, la abstracción en concreto es representada dentro del lenguaje de programación Java por medio de atributos, métodos y clases, a través de los cuales se va a dar forma a un código determinado.

</br>

### Instancias

Las instancias son la materialización de una clase, es decir, son objetos concretos que tienen su propio estado (los valores de sus atributos) y su propio comportamiento (los métodos que pueden ejecutar)

</br>

#### Referencias

Las referencias en Java son identificadores de instancias de las clases Java. Una referencia dirige la atención a un objeto de un tipo específico.

Cuando creamos un nuevo objeto en la memoria se crea un carácter alfanumerico por así decir, y para dirigirnos hacia el usamos un identificador que en este caso es una referencia.

```java
        /*Para llamar un campo con eso decimos que separe en la memoria espacio para el 
        nuevo objeto con cosas y memoria difente. le decimos a java que es otra instancia*/
        new NombreClase();  

        //EJEMPLO

        new Cuenta();
```

Si queremos llamar un elemento

```java
        public static void main(String[] args) {
            Cuenta primeraCuenta = new Cuenta();  
            primeraCuenta.saldo = 1000;   //llamos a saldo e ingresamos un valor 

            System.out.println(primeraCuenta.saldo);  //1000.0
        }
```

Al crear un objeto o instancia con `new` automaticamente los campos se inicializan
Ejemplo:

```java
        double saldo;  // 0.0
        int agencia;  // 0
        int numero; // 0
        String titular; // null
```

</BR>

#### Métodos

Son acciones contenidas en una clase, y definen su __comportamiento.__
Son toda secuencia de pasos para realizar una cosa o alguna acción

En java hay 2 tipos de métodos

- Que retornan valor (`return`)
  - La palabra return se usa para devolver un valor desde un método al programa.
  
</br>

- Los que no retornan valor (`void`).
  - La palabra reservada `void` de Java, se utiliza para indicar que un método no tiene return (valor de retorno).

< /br>

La sintaxis y el orden correcto para llamar a um método con Java es:

```java
        nombreDeReferencia.nombreDelMetodo();

        //ejemplo
        miCuenta.depositar();
```

- El `this` esta es una referencia, es decir, "apunta" a un objeto. Es una palabra clave igual al `void`, `class`, `new`, `int` y varias otras

```java
        this.saldo = this.saldo + valor;
```

</br>

##### Parametros

Acción necesaria que ese método necesaria usar

- Los parámetros son variables que se utilizan para pasar información a funciones o métodos cuando son llamados.

---

## 2.Encapsulamiento

El término encapsulamiento en Java, consiste en ocultar atributos de un objeto de manera que solo se pueda cambiar mediante operaciones definidas en ese objeto.

Modifiacdores de acceso `public`, `private` ...

</br>

### Ocultación de datos en Java

Con frecuencia, la encapsulación de Java se conoce como ocultación de datos . Pero más que el ocultamiento de datos, el concepto de encapsulación está destinado a una mejor gestión o agrupación de datos relacionados.

Para lograr un menor grado de encapsulación en Java, puede usar modificadores como “protegido” o “público”. Con la encapsulación, los desarrolladores pueden cambiar una parte del código fácilmente sin afectar a los demás.

</br>

### Métodos Getter y Setter en Java

Si un miembro de datos se declara “privado”, solo se puede acceder a él dentro de la misma clase. Ninguna clase externa puede acceder al miembro de datos de esa clase. Si necesita acceder a estas variables, debe usar los métodos públicos `getter` y `setter`.

Los métodos de `Getter` y `Setter` se utilizan para crear, modificar, eliminar y ver los valores de las variables.

El siguiente código es un ejemplo de métodos getter y setter:

```java
      Cuenta de clase { 
          private int account_number;
          private int account_balance;     
          // método getter         
          public int getBalance () {        
            devuelve this.account_balance;    
          }    
          // método setter         
          public void setNumber (int num) {        
            this.account_number = num;    
          }
      }
```

En el ejemplo anterior, el método `getBalance ()` es el método `getter` que lee el valor de variable `account_balance` y el método `setNumber ()` es el método `setter` que establece o actualiza el valor para la variable `account_number`.

#### Abstracción vs. Encapsulación

A menudo, la encapsulación se entiende mal con Abstracción. Estudiemos-

- La encapsulación se trata más de “cómo” lograr una funcionalidad
- La abstracción se trata más de “Qué” puede hacer una clase.
Un simple ejemplo para entender esta diferencia es un teléfono móvil. Donde la lógica compleja en la placa de circuito está encapsulada en una pantalla táctil, y se proporciona la interfaz para abstraerla.

#### Ventajas de la encapsulación en Java

- La encapsulación vincula los datos con sus funcionalidades relacionadas. Aquí las funcionalidades significan “métodos” y los datos significan “variables”
- Entonces mantenemos variables y métodos en un solo lugar. Ese lugar es “clase”. La clase es la base para la encapsulación.
- Con Java Encapsulation, puede ocultar (restringir el acceso) a los miembros de datos críticos en su código, lo que mejora la seguridad
- Como mencionamos anteriormente, si un miembro de datos se declara “privado”, solo se puede acceder a él dentro de la misma clase. Ninguna clase externa puede acceder al miembro de datos (variable) de otra clase.
- Sin embargo, si necesita acceder a estas variables, debe utilizar los métodos públicos “getter” y “setter”.
  
|Con los métodos `get` y `set` nuestros atributos privados no pueden ser accesibles directamente, esto quiere decir que cuando nuestro objeto es creado no es manipulable a no ser que sea a traves de nuestros métodos ya definidos, a esto se le llama el estado consistente de un objeto|
|---|

</br>

### Contructores

- Un constructor es un método que se ejecuta de manera automática al instanciar un objeto de una clase. El constructor tiene como finalidad la incialización de las variables de la clase y posiblemente ejecutar algunos de los métodos de la clase.

Un constructor sirve para inicializar el objeto y establecer sus propiedades y valores predeterminados.
Tiene el mismo nombre que la clase y no cuenta con ningún valor de retorno, ya que su función principal es inicializar el objeto y no devolver ningún valor.

Ventaja

- El constructor te obliga a informar parámetros al momento del nacimiento del objeto
- Te fuerza a dar información para que este objeto sea creado.

#### Static

- En Java el uso de `static` indica que en lugar de pertenecer a una instancia del tipo que se acaba de declarar pertenece a un tipo en si mismo. Es decir, solo se creará una instancia de ese miembro estático el cual será compartida por todas las instancias de la clase.
- Indica que es accesible a nivel de Clase y no necesita una instancia de ella para ser usada.
- El modificador static permite acceder a las variables y métodos aunque no tengamos una instancia del objeto que los contiene.
- **Al contrario que con las variables no static, no habrá una por cada instancia de una clase, sino una para todas las instancias de la clase.**
- La palabra `static` nos dice que la variable que la usa, no es alterada por la instancia, solo con la clase.
