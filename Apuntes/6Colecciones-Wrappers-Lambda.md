# Java y java.util: Colecciones, Wrappers y Lambda expressions

Estructuras de datos, arreglos de datos, agrupacion

- Wrappers

Objetos que engloban otros objetos para aplicar encapsulamiento y esconder los atributos propias de cada objeto

- Lambda expression

Es un tipo de funcion de funcion que se ejecutan en un contexto separado

## java.lang

Donde estan las principales clases de java como la clase `Object`, la clase `String`

## java-io

Clases para leer archivos, string de datos (nivel intermedio)

## java.util

Utilitarios, estructuras de datos, son objetos que nos van a simplificar la vida para realizar diferentes operaciones

---

## Arreglos (Array)

Es una estructura de datos utilizada para almacenar datos del mismo tipo (Ejem: Valores primitivos o referencias). Los arreglos almacenan sus elementos en ubicaciones de memoria contiguas
Los array usan corchetes `[]` sintaticamente, tienen un tamaño fijo.

En Java los arreglos son objetos. Todos los métodos pueden ser invocados

```Java
        String[] nombres = new String[5]; //llamarlo asi indicando el tamaño del string

```

Los arrays en Java se enumeran desde elemento 0, que sería el primer elemento, hasta el tamaño -1 que sería el último elemento, Es decir, si tenemos un array de diez elementos, el primer elemento sería el cero y el último elemento sería el nueve.

```Java
        int[] intArray = {2,5,46,12,34}; //Forma para inicializar un array definiendo sus valores
```

### Indice

El índice es la posición que tiene cada sección de mi "caja", los índices cominzan con 0

```Java
        int[] numeros = new int[5]; //especificamos el tamaño del array
        numeros[2] = 30;  //Indicamos con el indice en que posicion colocar el numero 30 
```

- Las matrices tienen un atributo  `.lenght` para conocer la cantidad de espacios que tenemos en el array

```Java
        .lenght // con este metodo conocemos la cantidad de espacios en nuestro array

        ///Ejemplo///

        int longitud = numeros.lenght;
        System.out.println(longitud); ///salida: 5
```

</br>

### Type cast

El cast explícito solo funciona si es posible, pero hay casos en los que el compilador sabe que un cast es imposible y luego ni compila ni con type cast.

```Java
        Cliente cliente = new Cliente();
        Cuenta cuenta = (Cuenta) cliente; // imposible, no compila ya que Cliente no estiende ni implemete Cuenta
```

ejemplo: `Designer` y `Gerente` extienden de Funcionario

```Java
        Funcionario[] referencias = new Funcionario[5]; //Array de funcionario llamado referencias

        referencias[0] = new Designer(); //en la posicion 0 guardamos la referencia de Designer 
        referencias[1] = new Gerente();  //En la posicion 1 guardamos la referencia de Gerente
        
        Designer designer = (Designer) referencias[0]; //Casteamos porque esta guardado en un array Funcionario y es un designer
        Gerente gerente = (Gerente) referencias[1]; //Casteamos porque esta guardado en un array Funcionario y es un Gerente
```

</br>

#### `ClassCastException`

Cuando falla el `type cast`, podemos recibir un ClassCastException.

- Lanzado para indicar que el código ha intentado convertir un objeto en una subclase de la que no es una instancia.

</br>

> [Consejo]
> Puedes crear métodos para agregar u obtener valores de un array (referencias si hablamos de objetos), auque hay otro tipo de arrays que ya los tienen, los `ArrayList`. Puedes hacerlo mediante una clase

```Java

        //////Ejemplo//////
        private Cuenta[] referencias;
        private int posicionLibre = 0;
            
    
        public GuardadorCuentas(){  //tambien podria ser GuardarObjetos
            this.referencias = new Cuenta[10];
        }
    
        public void adicionar(Cuenta ref) {  //de argumento //Object ref
            referencias[this.posicionLibre] = ref;
            posicionLibre++;
    
        }
    
        public int getCantidadElementos() {
            return this.posicionLibre;
        }

        //con el tipo de retorno obtenemos lo que queremos retornar
        public Cuenta getRerencia(int pos) { //que retorne Object en de que qeuramos otro tipo
        return this.referencias[pos];
        }
```

## Java.util.List (Interface List)

### ArrayList

Es una clase de array de tamaño ampliable, que se puede agregar y eliminar elementos a diferencia de un array tradicional, se puede decir que es un conjunto de objetos agrupados en un objeto mayor. Es realmente un guardador de referencias y usa un array internamente pero sin preocuparse por los detalles y limitaciones, el único límite es la memoria de la JVM.

`java.util.ArrayList` encapsula el uso de array y ofrece varios métodos de alto nivel.

</br>

Si la tratamos como objeto, se inicializa asi:

```Java
            ArrayList array list = new ArrayList();//Creamos el objeto de un ArrayList
            
            ArrayList array list = new ArrayList(capacidad); //metodo sobrecargado que sirve para señalar el tamaño del array
            
```

</br>

**Lista a partir de otra**.

Otra forma de inicializar una lista es basada en otra que es muy común en el día a día. Para esto, ArrayList tiene un constructor más que recibe la lista base:

```Java
        ArrayList lista = new ArrayList(26); //capacidad inicial
        lista.add("RJ");
        lista.add("SP");
        //otros estados
        ArrayList nueva = new ArrayList(lista); //creando basada en la primera lista
```

</br>

Forzamos a ser de un tipo (usando generics con el operador diamond `<>`):

```Java
        // <> Forzando a que solo acepte un tipo de objeto
        ArrayList<String> string = new ArrayList<String>(); //ArrayList de tipo String
        ArrayList<Integer> string = new ArrayList<Integer>(); //ArrayList de tipo Integer

```

</br>

- Métodos que podemos utilizar:

```java
        .add(Elemento) //Para agregar un elemento a nuestro ArraList
        .add(Indice, Elemento) //Para agregar un elemento a nuestro ArraList

        .get (indice) //Se utiliza para obtener un elemento del ArrayList

        .set(indice,valor)  //Cambiar el valor de un elemento
        
        .size() //Retorna el tamaño de elementos de la Lista (diferente de .lenght que es cantidad de espacios del array aunque sea null y no se cuantos estan llenos)

        .remove(indice)  // elimina la referencia del array deacuerdo al indice dado
        .remove(elemento)  //Elimina la referencia del array deacuerdo al elemento dado
        .isEmpty() //saber si la lista esta vacia, devuelve un boolean
        
        .contains(objeto) /*saber si una lista contiene un elemento, retorna un boolean 
        (si es un objetos compara la referencia pero no la informacion que esta dentro de la referencia,
        si queremos comparar la informacion tenemos que sobreescribir .equals )*/

        @Override
        .equals(Object obj) /*Metodo que internamente usa el metodo contains `return obj == object` devuelve un boolean 
        La implementación estándar compara las referencias y es usado por listas.
        Sobreescribir para permitir comparar la informacion de la referencia con nuestro propio return*/

        //EJEMPLO//
        @Override
        public boolean equals(Object obj) {
        Cuenta cuenta = (Cuenta) obj;
        return this.agencia == cuenta.getAgencia() && this.numero == cuenta.getNumero();
        }

         
```

</br>

### LinkedList (Lista anexada)

Es una estructura de datos de listas anexadas el cual cada nodo tiene referencias a su proximo elemento, **no es un array** pero si es una lista. Un conjunto de objetos en el que cada uno tiene referencias al siguiente nodo (conjunto de objetos) y al nodo anterior

El beneficio principal es que puedo utilizar operaciones de remover elementos o insertar elementos en un especifico orden sin necesidad de recorrer toda la lista

Si tienes muchas operaciones de recorrer toda la lista completa, de imprimir todos los elementos de la lista en todo momento ahí tiene un costo de performans ya que al iterar cada elemento pregunta por su referencia cercana, este lado LinkedList no es recomendada

---

- Comparación entre Arrays y ArrayLists en Java

| ARRAYS                                                                                              | ARRAYLIST                                                                                                                 |
|-----------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| Tamaño fijo: útil cuando se conoce el número exacto de elementos.                                   | Tamaño dinámico: útil cuando se desconoce el número exacto de elementos o cuando se requiere cambiar el tamaño.           |
| Rendimiento ligeramente mejor debido a la falta de métodos adicionales y administración de memoria. | Rendimiento ligeramente inferior en comparación con los arrays debido a la administración adicional de memoria y métodos. |
| Puede contener tipos de datos primitivos.                                                           | No puede contener tipos de datos primitivos directamente (utilizar clases envolventes como Integer, Double, etc.).        |

</br>

Si requerimos una estructura de datos de tamaño fijo y un rendimiento óptimo, los arrays pueden ser una opción adecuada.

Sin embargo, si necesitamos una estructura de datos más flexible que pueda cambiar de tamaño dinámicamente, las ArrayLists serán una mejor opción.

</br>

### Vector (ya no se usa dificil ver este metodo)

Practicamente hace lo mismo que cualquier lista ya que implelmenta la interface Lista y tiene los mismos métodos, pero el Vector es un ArrayList `Thread safe`. El es un unico objeto para todas las pilas de ejecucion

Ya no se usa por el hecho de ser **thread safe** tiene mucho impacto con el performance

![Alt text](../Imagenes/collection.png)

</br>

### Primitivos

```Java
        List<Integer> lista = new ArrayList<Integer>();
```

</br>

#### Autoboxing y Auto-unboxing

El autoboxing/unboxing simplifica y agiliza en gran medida el código que debe convertir los tipos primitivos en objetos, y viceversa. Debido a que tales situaciones se encuentran con frecuencia en el código de Java, los beneficios del autoboxing/unboxing afectan a casi todos los programadores de Java.

- El proceso de encapsular un valor dentro de un objeto se llama **boxing**
- El proceso de extraer un valor de un contenedor de tipo se denomina **unboxing**.

</br>

##### **Wrappers:**

Las clases Wrapper en Java o envoltorio permiten envolver un tipo de dato primitivo para tratarlo como si fuera objeto. (Integer, Double...)

- ¡Son clases que contienen funcionalidades y encapsulan la variable de tipo primitivo!

Creamos objetos de esas clases para envolver o envolver un valor primitivo

![Wrappers](../Imagenes/grappers.png)

</br>

**AutoBoxing:**
Autoboxing es el proceso por el cual un tipo primitivo es automáticamente encapsulado (boxed) en su envoltura (wrapper) de tipo equivalente cuando se necesita un objeto de ese tipo. No hay necesidad de obtener explícitamente un objeto

```java
        class AutoboxingExample1{

           public static void myMethod(Integer num){
                System.out.println(num);
           }

           public static void main(String[] args) {
               /* passed int (primitive type), it would be 
                * converted to Integer object at Runtime
                */
                myMethod(2);
           }
        }


        Integer inum = 3; //Assigning int to Integer: Autoboxing
        Long lnum = 32L; //Assigning long to Long: Autoboxing


        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(11); //Autoboxing - int primitive to Integer
        arrayList.add(22); //Autoboxing
```

</br>

**Unboxing**
El Auto-unboxing es el proceso por el cual el valor de un objeto encapsulado se extrae automáticamente (desencapsula) de una envoltura de tipo cuando se necesita su valor.

```Java

        class UnboxingExample1{
           public static void myMethod(int num){
                System.out.println(num);
           }
        
           public static void main(String[] args) {
        
                Integer inum = new Integer(100);
        
                /* passed Integer wrapper class object, it 
                 * would be converted to int primitive type 
                 * at Runtime
                 */
                myMethod(inum);
            }
        }

```

- Métodos

```Java
        nameWrapper.valueOf(valor) //metodo para crear un nuevo dato primitivo
        nameVariableWrapper.byteValue(); //conveertir un valor a byte (ejemp: int a byte)
        nameVariableWrapper.doubleValue();//conveertir un valor a double (ejemp: int a double)
        nameVariableWrapper.floatValue();//conveertir un valor a float (ejemp: int a float)

        /////ejemplo/////
        Integer numeroObjeto = Integer.valueOf(40); //crear un integer que almacena un int

        byte byteInteger = numeroObjeto.byteValue();
        double doubleInteger = numeroObjeto.doubleValue();
        float floatInteger = numeroObjeto.floatValue();

        
        .SIZE //El tamaño de bits
        .MAX_VALUE //Numero maximo que soporta
        .MIN_VALUE //Numero minimo que soporta
        .BYTES //TAMAÑO EN bytes

```

</br>

**Intefaz `Comparator <T>`**

Interfaz que nos ayuda a comparar reescribiendo su metodo `.compare(T o1, T o2)` y junto con el metodo `.sort()` ordenar una lista.

La interfaz Comparator  `Comparator <? extend T> c` se  puede entender así, "Comparator cualquier clase hija de T"

```Java
//////Interfaz  Comparator<T>  (dentro del generic la clase como parametro para comparar)///// 
        .compare(T o1, T o2)

        ///EJEMPLO para reordenar cuentas con numeros de cuenta///
        class OrdenadorPorNumeroCuenta implements Comparator<Cuenta>{
        
            @Override
            public int compare(Cuenta o1, Cuenta o2) {
            //////Forma Basica//////
            if (o1.getNumero() == o2.getNumero()) {
                return 0;
            } else if (o1.getNumero() > o2.getNumero()){
                return 1;
            } else {
                return -1;
              }
            //////Forma intermedia//////
            return o1.getNumero() - o2.getNumero(); //si son iguales se van a restar retur 0, si o1 es mayor retorba 1, sino retorna negativo (-1)
            /////Forma Wrapper//////
            return Integer.compare(o1.getNumero(), o2.getNumero());
    }
        }


        public static void main(String[] args) {
            Cuenta cc1 = new CuentaCorriente(22,33);
            cc1.depositar(333.0);
        
            Cuenta cc2 = new CuentaAhorros(22,44);
            cc2.depositar(444.0);
        
            Cuenta cc3 = new CuentaCorriente(22,11);
            cc3.depositar(111.0);
        
            Cuenta cc4 = new CuentaAhorros(22,22);
            cc4.depositar(222.0);
        
            List<Cuenta> lista = new ArrayList<>();
            lista.add(cc1);
            lista.add(cc2);
            lista.add(cc3);
            lista.add(cc4);        
        
            System.out.println("Antes de ordenar"); //Antes de ordenar
            for (Cuenta cuenta : lista){
                System.out.println(cuenta);
            }

            Comparator<Cuenta> comparator = new OrdenadorPorNumeroCuenta();
            lista.sort(comparator); //ordenar
            //lista.sort(new OrdenadorPorNumeroCuenta())            


            System.out.println("Despues de ordenar"); // Despues de ordenar
            for (Cuenta cuenta : lista){
                System.out.println(cuenta);
            }
        }

            ///Salida//
            /*
            Antes de ordenar
            Numero: 33 Agencia: 22
            Numero: 44 Agencia: 22
            Numero: 11 Agencia: 22
            Numero: 22 Agencia: 22
            Despues de ordenar
            Numero: 11 Agencia: 22
            Numero: 22 Agencia: 22
            Numero: 33 Agencia: 22
            Numero: 44 Agencia: 22
            
            */
        

        //metodo para comparary ordenar alfabeticamente por nombre que utiliza el metodo compareTo()//
        .compareTo(String OtroString)
        

        //sobrescribimos el metodo compare() de la intefaz Comparator<T>
        @Override
        public int compare(Cuenta o1, Cuenta o2) {
        return o1.getTitular().getNombre().compareTo(o2.getTitular().getNombre());
        }    

        //en el main utilizamos el metodo sort() que recibe de parameetro el nombre de la clase que implemento la interfaz
        lista.sort(comparatorNombre); //ordenar alfabeticamente
        

```

</br>

> Nota (Pregunta de certificacion)
> `@FuncionalInterface`
>
>Son aquellas que tienen un solo método que tienen para implementar.

</br>
</br>


#### Ordenaar listas en versiones antiguas de java 1.8 (1.7, 1.6..)

</br>

En sistemas bancarios aun manejan versiones antiguas de Java

- Clase `Collecttions` java.util

```Java
Collections.sort(lista);//No compila ya que no hemos definido el orden natural
```

</br>

**Orden Natural:** Es el atributo por defecto bajo el cual vas a ordenar una lista o un arreglo, debe ser definido en la clase que vamos a ordenar.

- El orden natural está definido por el propio elemento de la lista a través de la interfaz `java.lang.Comparable`.
- La clase define sus propios criterios de ordenación implementando la interfaz `java.lang.Comparable`.

```Java
        ///////Implementamos la interface que utiliza la clase Collections/////
        public abstract class NombreClase implements Comparable<NombreClase>{

            //implementamos el metodo de la interface//
            @Override
            public int compareTo(NombreClase o) {
            //Orden Natural: Numero de agencia o del atributo
            return Integer.compare(this.atributo, o.getAtributo());//atributos por los cuales ordenar
            /// Orden natural: Saldo//
            //return Double.compare(this.getSaldo(), o.getSaldo());
            }
        }


        ///main///    
        public static void main(String[] args){
        
            Collections.sort(lista);//llamamos a collections.sort y pasamos de parametro la lista
    
            Collections.reverse(lista); //Invertir el orden la lista
            
            Collections.shuffle(lista);//Mezclar todos los elementos de la lista
            
            Collections.rotate(lista, 5); //rotar 5 posiciones de la lista

        
        }
```

</br>

#### Ordenar Arrays

Para ordenar arrays tampoco es difícil, solo usar el método de ordenación de la clase `Arrays`. La clase `Arrays` es similar a `Collections` en el sentido de que también une varios métodos de utilidad:

```Java
        import java.util.Arrays;
        
        public class TestSortArrays{

            public static void main(String[] args){
                int[] numeros = new int[]{43, 15, 64, 22, 89};
        
                Arrays.sort(numeros); //método utilitário sort
                System.out.println(Arrays.toString(numeros)); //método utilitário toString
        
                //Salida : [15, 22, 43, 64, 89]
            }
        }
```