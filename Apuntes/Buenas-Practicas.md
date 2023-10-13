# Importante

## Buenas prácticas

- El uso de `this` para señalar que un atributo o un método es de la propia clase, teniendo mayor legibilidad en el código.

---

## Datos interesantes

1. El compilador de Java de manera implicita al terminar un método `void` internamente agrega ``return ;``
  
    ```java
        public static void cambiarValor(Persona persona){
            persona.nombre = "Karla";
            return;
        }
    ```

    > Siempre esta precente la palabra `return` de manera implicita o explicita en nuestro método, podemos mandar llamar varias veces en nuestro método pero la primera anulara a las démas (útil de manera condicionada, en metodos como validadores, etc).

2. El paquete `java.lang` se importa de manera implicita a todos nuestros programas en java, por lo que podemos usar cualquier clase que provenga de ella sin importarla.

## Información importante

- ``null`` una vez terminado nuestro programa igualamos a null, esa variable que estaba apuntando a un objeto ya no contiene su referecia y es candidato para que el **garbage collector** lo elimine de la memoria heap
- System.gc() -- Linea de código que va a programar el recolector de basura para que cuando sea posible se ejecute y pueda eliminar todos los objetos que no estan siendo utilizados o referenciados, no garantiza que se va a ejecutar el recolector de basura en este momento. programa el rc de basura para que pueda ejecutarse cuando sea necesario ya que es un proceso bastante pesado y no lo podemos llamar solamente porque nuestro programa lo necesita

> ![Note]
> No es común utilizar esos métodos ya que los administradores de sistemas tienen sus metodos para **limpiar la memoria**

---

- ### Paso por

  - Paso **por valor** (métodos void)
    Pasar un valor ya establecido a un método se le conoce como **paso por valor**, le estamos pasando una copia al método que estamos mandando llamar, el método no puede modificar el valor de la variable original.

    </br>

  - Paso por valor **por referencia**
    Una variable almacena una referencia y apunta a la memoria en el que se encuentra, cuando esta referencia es enviada a un método como parámetro, este metodo hace uso del párametro apuntando a la misma referencia del objeto pasado, por lo cual puede modificar su contenido sin que el método devuelva nada, al terminar el método la variable se destruye y solo queda la original.

