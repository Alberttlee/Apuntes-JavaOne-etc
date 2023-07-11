Paquetes

Para hacer que una clase sea más fácil de localizar y utilizar así como evitar conflictos de nombres y controlar el acceso a los miembros de una clase, las clases se agrupan en paquetes.

Paquete

Un paquete es un conjunto de clases e interfaces relacionadas.
La forma general de la declaración package es la siguiente:
   package nombrePaquete;
donde nombrePaquete puede constar de una sola palabra o de una lista de nombres de paquetes separados por puntos.

```Java
Ejemplo

package miPaquete;  //paquete

class MiClase
{
  ...
}
Ejemplo

package nombre1.nombre2.miPaquete; //lista de nombre de paquetes separados por puntos

class TuClase
{
  ...
}
```

Los nombres de los paquetes se corresponden con nombre de directorios en el sistema de archivos.

De esta manera, cuando se requiera hacer uso de estas clases se tendrán que importar de la siguiente manera.

```java

Ejemplo

import miPaquete.MiClase;
import nombre1.nombre2.miPaquete.TuClase;

class OtraClase
{
   /* Aqui se hace uso de la clase 'Miclase' y de la
   clase 'TuClase' */
   ...
}
```

Para importar todas las clases que están en un paquete, se utiliza el asterisco  *

Ejemplo
import miPaquete.*;

Si no se utiliza la sentencia package para indicar a que paquete pertenece una clase, ésta terminará en el package por default, el cual es un paquete que no tiene nombre.

```java
Ejemplo

/* Usuario5.java */

package paquete1;

class Usuario5
{
     static char MAS = 'm';            
     static char FEM = 'f';

     String nombre;
     int edad;
     String direccion;                     
     char sexo;                     

     Usuario5( )
     {
        nombre = null;
        edad = 0;
        direccion = null;
        sexo = '\0';
     }

     Usuario5(String nombre, int edad, String direccion,char sexo)
     {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.sexo = sexo;
     }

     Usuario5(Usuario5 usr)
     {
        nombre = usr.getNombre();
        edad = usr.getEdad();
        direccion = usr.getDireccion();
        sexo = usr.getSexo();
     }

     void setNombre(String n)
     {
        nombre = n;
     }

     String getNombre()
     {
        return nombre;
     }

     void setEdad(int e)
     {
        edad = e;
     }

     int getEdad()
     {
        return edad;
     }

     void setDireccion(String d)
     {
        direccion = d;
     }

     String getDireccion()
     {
        return direccion;
     }

     void setSexo(char s)
     {
        sexo = s;
     }

     char getSexo()
     {
        return sexo;
     }

     public String toString()
     {
       return nombre;
     }
}
```

```java
Ejemplo

/* ProgUsuario5.java */

package paquete1;

import java.util.Vector;

class ProgUsuario5
{
   static int NUM_USUARIOS = 0;

   static Vector usuarios = new Vector();
   /* La siguiente línea sería obligatoria si
      se omitiera la linea import java.util.Vector; */
//   static java.util.Vector usuarios = new java.util.Vector();

   String nombreObj = null;

   ProgUsuario5(String nombre)
   {
     this.nombreObj = nombre;
   }

   static int getNumUsuarios()
   {
     return NUM_USUARIOS;
   }

   static void imprimeUsuario(Usuario5 usr)
   {
     System.out.println("\nNombre: " + usr.nombre );
     System.out.println("Edad: " + usr.getEdad() );
     System.out.println("Sexo: " + usr.getSexo() );
     System.out.println("Direccion: " + usr.getDireccion() );
   }

   void addUsuario(Usuario5 usr)
   {
     usuarios.addElement(usr);
     System.out.print(usr.toString( )+ " agregado por el "+ this.toString() +",");
     NUM_USUARIOS ++;
   }

   void delUsuario(Usuario5 usr)
   {
     boolean b = usuarios.removeElement(usr);
     if( b == true )
     {
        NUM_USUARIOS--;
        System.out.print(usr.toString( )+ " eliminado por el "+ this.toString() +","); 
     }
     else System.out.println("No se pudo eliminar al usuario.");
   }

   public String toString()
   {
      return nombreObj;
   }

   public static void main(String args[])
   {
    
      ProgUsuario5 obj1 = new ProgUsuario5("objeto1");
      ProgUsuario5 obj2 = new ProgUsuario5("objeto2");

      Usuario5 usr1,usr2,usr3,usr4; 

      usr1 = new Usuario5( ); 
      usr2 = new Usuario5("Usuario B",24,"La direccion A",Usuario5.FEM); 
      usr1 = new Usuario5(usr2);  
      usr1.setNombre("Usuario A");
      usr3 = new Usuario5("Usuario C",35,"La direccion C",Usuario5.MAS); 
      usr4 = new Usuario5("Usuario D",15,"La direccion D",Usuario5.MAS); 
      obj1.addUsuario(usr1);
      System.out.println( "\t Total: " +ProgUsuario5.getNumUsuarios() );
      obj2.addUsuario(usr2);
      System.out.println( "\t Total: " +obj1.getNumUsuarios() );
      obj1.addUsuario(usr3);
      System.out.println( "\t Total: " +ProgUsuario5.NUM_USUARIOS );
      obj2.addUsuario(usr4);
      System.out.println( "\t Total: " +getNumUsuarios() +"\n");

      obj2.delUsuario(usr4);
      System.out.println( "\t Total: " +ProgUsuario5.getNumUsuarios() );
      obj1.delUsuario(usr3);
      System.out.println( "\t Total: " +obj1.getNumUsuarios() );
      obj2.delUsuario(usr2);
      System.out.println( "\t Total: " +ProgUsuario5.NUM_USUARIOS );
      obj1.delUsuario(usr1);
      System.out.println( "\t Total: " +getNumUsuarios() +"\n");
   }
}
```

## Enums

Son los tipos de datos especiales donde una variable puede contener el valor de un grupo de constantes predefinidas.

Basicamente es un conjunto de constantes que se las asocia a un tipo `enum`. En lugar de `class`
utilizamos la palabra clave `enum` y entre llaves definimos las constantes.

Es como un array solo que esta en una clase tipo enum.

**¿Cuando usar un tipo enums**
Cundo quieras representar un conjunto fijo de constantes, incluye enumeraciones de tipo natural como planetas del sistema solar y conjunto de datos en donde se conoce el valor de cada constante en tiempo de compilación como las opciones de un menú, los colores o los dias de la semana.

