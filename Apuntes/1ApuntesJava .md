# JAVA

VRE: Java Runtime Environment
JDK: Java Devevlopment Kit
JVM: Java Virtual Machine

Plataforma de Java : Maquina virtual de Java, frankwords, librerias.

- Portable
- Fácil
- Segura
- Omniprente

## Maquina Virtul de Java (JVM)

**James Gosling** (Padre de Java)

En el mundo Java siempre tendremos el mismo archivo "ejecutable" (Bytecode) que será ejecutado por la máquina virtual de Java (JVM) independientemente del sistema operativo estemos usando. De esta forma, no es necesario reescribir el código o adaptarlo a cada sistema operativo. ¡Tenemos un único ejecutable para todas las plataformas!

> 1. Lenguaje Java  (Codigo Java - archivos ***.java***)
> 2. JAVAC (Java Compiler - ***Java Development***)
> 3. Byte Code (Archivos ***.class***) resultado de la compilacion
> 4. JVM (Java Virtual Machine - ***Java Runtime Environment***)
> 5. Sistema Operativo (SO)

</br>

La JVM se encarga de:

- Adimistracion de Memoria
- Multiplataforma
- Seguridad
- Optimización
- Librerias

**Compilacion:**
Es el proceso de tranformacion de codigo fuente a codigo ejecutable, el codigo fuente se compila en binario y debe ser compatible con el sisteka operativo

**`W.O.R.A (Write Once, Run Everywhere)`**

<img src="/Imagenes/image.png" alt="drawing" width="500"/>

| `Si queremos ejecutar codigo java Utilizar JRE que contienes el JVM y las librerias, para desarrllo el JDK aue tiene un conjunto de herramientas para poder escribir codigo.` |

---

*La entrada de una aplicación Java es siempre la **función/método** public static void main(String[] args)*

**Workspace (Espacio de trabajo):** Es como una carpeta para varios proyectos.

</br>

---

### ``JAVA es fuertemente tipado, es decir, dado el valor de una variable de un tipo concreto, no se puede usar como si fuera de otro tipo distinto a menos que se haga una conversión``

</br>

## TIPOS DE VARIABLES

**```Las variables almacenan valores y no referencias.```**

- byte
- short
- char: El tipo char contiene solo un carácter de la tabla Unicode
- int
- float
- double

**Casting:** El casting es un procedimiento para transformar una variable primitiva de un tipo a otro, o transformar un objeto de una clase a otra clase siempre y cuando haya una relación de herencia entre ambas.

Ejemplo:

```Java
public class TestConversion {

    public static void main(String[] args){
        double salario = 1270.50;
        int valor = (int) salario;    //cast
    }
}
```

<img src="/Imagenes/image-1.png" alt="drawing" width="450"/>

---

El operador lógico **AND** está representado por los caracteres `&&` y el **OR** por `||`.

Los operadores lógicos deben tener una expresión booleana en los lados izquierdo y derecho.

```java
if (edad > 18 && edad < 65) {

}
```

| Los Boolean por defecto son `false` |
|                -                        |

---

### Java Scope (Alcance  de Java)

En Java, solo se puede acceder a las variables dentro de la región en la que se crearon. Esto se llama scope.

- **Alcance de metodo**: Las variables declaradas directamente dentro de un método están disponibles en cualquier parte del método siguiendo la línea de código en la que fueron declaradas
- **Ambito de bloque**: Un bloque de código se refiere a todo el código entre llaves ``{}``. Solo se puede acceder a las variables declaradas dentro de bloques de código mediante el código entre llaves, que sigue la línea en la que se declaró la variable.

</br>

---

## if - else

Expresiones booleanas

```java
if (condicion) {
    "codigo si es verdadero"
}
else {
    "   codigo si es falso"
}
```

### if - else if

```java
if (condicion) {
            
        }
        else if (condicion){
            
            
        }
```  

- **Ejemplo**

```java
int mes = 3; // Marzo
String estacion;

if (mes == 12 || mes == 1 || mes == 2) {
    estacion = "Invierno";
} else if (mes >= 3 && mes <= 5) {
    estacion = "Primavera";
} else if (mes >= 6 && mes <= 8) {
    estacion = "Verano";
} else {
    estacion = "Otoño";
}

System.out.println("La estación del año es: " + estacion);
```

- *Else sirve para romper la comprobacion de los siguientes casos*

---

## CICLOS

- ### While

El ciclo while acepta dos valores: ´true´ o ´false´. El valor ´true´ mantiene el ciclo en ejecución, cuando cambia a ´false´ este ciclo es finalizado. Esos valores pueden ser resultado de una expresión como `contador <=10`

```java
contador = 0;

            while (condicion){
                "codigo si es verdadero"
                contador++
            }
```

- **Ejemplo**

```java
int contador = 0;

        while (contador <= 10){  //Mientras que (conficion)
            //ejecuta esto
            System.out.println(contador);
            //contador = contador + 1;
            //contador += 1;
            contador++;
        }
```

- ### For

```java
            for (variable; condicion; ejecutar al Final) {
                "Codigo si es verdadero"
        }

```

- **Ejemplo**

```JAVA
            for (int contador = 0; contador <= 10; contador++){
                System.out.println(contador);
            }
```

```java
            for(int numero = 3; numero <= 100; numero += 3){
                System.out.print(numero + " ");
        }
```

```java
            for (int i = 1; i < 100; i++ ){
                if (i % 3 == 0)    {
                    System.out.println(i + " ");
                }
            }
```

- ### Bucles anidados

```java
            for(int i = 0; i <= 5; i++) {
                for (int j = 0; j <= i; j++){
                    System.out.print(j +  1 + " ");
                }
            System.out.println();
            }
```

---
