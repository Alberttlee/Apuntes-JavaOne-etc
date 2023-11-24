# Bases de datos

## SQL Structured Query Language (SEQUEL)

En los años 70´s en los laboratorios de IBM, queria obsevar la vialibilidad de un sistema de datos relacionales.

El britanico Edgar, ideo una forma sencilla de manipular y extraer de los datos aprovechando  este modelo relacional de base de datos.

En los 80´s surgieron otras empresas que usaron SEQUEL para trabajar con base de datos, entro **ANSI** y se establecio una serie de parametros con forme unas normas, que sirve respetando **CRUD** (``Create``, ``Read``, ``Update``, ``Delete``), ANSI dividio en tres grandes de datos.

- **DDL** (date definition languge)- Que se encarga de manipular  todas las estructuras de las bases de datos, se usa el comando create para la base de datos, drop para remover una tabla o base de datos
- **DML** - (Date Manipulacion Language) Selec, insert, update, delete
- **DCL** - (Date Control Language) Administracion de la estructura de la base de datos, accesos a usuarios, logs, toda la parte de politicas y crecimiento de base de datos.

### Principales ventajas

1. Costo reducido de aprendizaje
2. Portabilidad
3. Longevidad
4. Comunicación
5. Libertad de elección

### Principales deventajas

1. Falta de creatividad  (big data muchos datos dificiles de encajar en bases de datos relacionales)
2. NoSQL - Lenguajes que no siguen los estandares ANSI y pueden atender mejor la demanda, el problema de NoSQL es mas especifico, mas puntual con los datos
3. Falta de más estructuracion de su lenguaje (se siguieron a la ANSI, otros crearon sus propios lenguajes convencionales de programacion al interior de SEQUEL partiendo de las bases del origen del lenguaje nativo).

---

## MYSQL

Fue desarrollado en la década de los noventas por los suecos David Axmark, Allan Larsson y el Irlandes Michael Windenius. El nombre de MYSQL viene de la Hija de Michael "My" y SQL por el lenguaje.

Vieron la necesidad de hallar una interfaz compatible con la demanda del mercado, por lo que desarrollaron su propia API de consulta y base de datos utilizando c++, Y así nacio MYSQL (la idea es crear un software de open source, un software que tenga escalabilidad robusta emisión critica, que nunca se caiga).

En 2008 vendieron la empresa a Sun Microsystems(Creadores de Java) por 1Billon de dolares
En 2009 Oracle compro a Sun Microsystems y se quedó con MySQL y con Java.

### Caracteristicas

- **Servidor Robusto**. Multi-access (Siempre al aire, escalabilidad, emision critica), Data Integrity (Mantener la Integridad de los datos), Transaction control (Control de transacciones, acceso a tablas).
- **Portabilidad**. Windows/Linux. Acceso a datos usando DotNET, Phyton, Java, JS, PHP...
- **Muti-threads**. Facilta la Integracion con HardWare + Escalabilidad. Tiene que ver con el Kernel de sistema Operativo, para aumentar la velocidad de procesamiento, permite la integracion de Hardware con la plataforma, si un servidor tiene un "x" numero de procesadores MySQL  permite usar toda esa capacidad de procesamiento para hacer esa base de datos mas rápida.
- **Almacenamiento**. Dependiendo de las necesidades puede priorizar la Velocidad de almacenamiento o el volumen de almacenamiento.
- **Velocidad** +Rapidos, e-commerce, AWS/BigQuery/Azure tienen instancias en MySQL. Es una de las bases de datos veloces en el mercado, de las mas rápidas.
- **Seguridad**. Diversidad de mecanismos para mentener la seguridad en la base de datos, como el acceso de usuarios a especifica informacion
- **Capacidad**. Hasta 65000TB, (una gran corporacion no llega a usar tanto).
- **Aplicabilidad**. Es uno de los preferidos por desarrolladores de Internet/desktop/corporativo.
- **Logs**. Registra todo. Recuperacion facilmente de informacion, Réplica de servidores (Se puede replicar en diverentes lugares, como si en un pais se cae un servidor y otro pais que cuente con la informacion puede suplir esta demanda sin afectar su desempeño).

</br>

### Definiciones

- ¿Comó está organizada una base de datos?

En toda Base de datos hay una entidad mas grande de todas llamada base de datos que es un repositorio que almacena datos y esta base de datos se almacena en un disco duro, en una SSD (Espacio en memoria para guardar los datos).

La base de datos esta construida de diversas entidades que se encargan de organizar los datos al interior de la base de datos

- **Entidades de la base de datos**

1. La **tabla** (La más importante)
   En MySQL es un objeto que estaremos creando que previamente predefiniremos su número de columnas que va a tener, el tipo de calumna y a medida que se ingresen muevas filas a nuestra calumna entonces nuestra tabla tambien va a crecer.
   - **Columnas** (**``Campos``** en MySQL)
      - Lo definimos al momento de la creación de la tabla
      - Numero limitado
      - No admite datos qe no fueron especificos
   - **Filas** (Son los **``registros``**)
     - Datos contenidos en los campos
     - Número ilimitado (siempre limitado por el espacio de almacenaje en disco o por una politica de crecimiento de base de datos)
   - **Indice**
     - Es un número que nos ayuda a ubicar con facilidad un registro o registros al interior de la base de la tabla (tiene un algoritmo que lo que hace es buscar un elemento)
   - **(*)Clave primaria** Externa/Foránea (opcional pero a la vez tener en cuenta)
     - Es un campo en el cual ninguno de sus registros de pueden repetir en sí mismo. Ejem: si hablamos de una clave primaria de una cédula o número de identificacion de un cliente, no puede haber 2 registros en la lista. La clave primaria no permite que este registro sea repetido con el objetivo de que haya integridad con los datos.
     - No obligatorio, es opcional pero en algunos casos necesitamos una clave primaria para que no tengamos un problema, en el caso de servicios a clientes es obligatorio ya que no vamos a registrar a un mismo cliente 2 veces
     - Se puede tener una clave compuesta por 2 campos pero su combinación no se puede repetir en ningún otro registro de la tabla. Ejem: tienes el número de cédula y el registro de identificacion tributaria del cliente, la combinacion de estos 2 no se puede repetir en ninguna otra linea, fila, registro de mi tabla
    ![Tabla](../Imagenes/tablasMySQL.png)

    Tenemos que definir:

   - ¿Cuantos campos tendremos?
   - ¿Qué tipo de datos asignaremos a cada campo?

    Tipos de campo:

    **Texto**: char, varchar, text...
    **Númerico**: int, bigint, smallint, float, bool...(1 verdadero, 0 falso)
    **Fecha**: date, datetime, timestamp...

    > Si no introducimos la imformación de acuerdo con el tipo de campo definido, se generarán errores.

    Una base de datos esta compuesta por muchas tablas y en cada tabla puedo tener 1 o mas claves primarias, claro que puedes no tener pero para yo poder relacionar cada una de mis tablas necesito una Clave Externa (Foránea) que es un campo que me va a permirtir relacionar cada una de las tablas entre si, crear una conexión/vínculo entre mis tablas.

2. **Esquemas** (Agrupar tablas)
    Facilitan la agrupación de tablas por temas.
    Tengo mi tablas y puedo agruparlas por temas, por grupos que facilitan la organizacion al interior de la base de datos.

3. **View** (Vista)
   - Para realizarlo se realiza de la siguiente manera, se consulan un "n" determinado de tablas y se trae la informacion al mismo tiempo
   - Alto costo de procesamiento. Dependiendo de los la funcion o comandos SQL para ver este tipo de vista puede tener un costo muy alto de procesamiento y en algún punto puede no ser recomendable pero generalmente facilitan mucho el tomar elementos de varias tablas

    Tengo todas mis tablas, entre mis tablas hay una relación a través de una clave externa que me permite realizar una consulta o Query y obtengo mi información. Esta consulta entre tablas la puedo hacer a través de *join*, juntas las tablas a través de una clave externa que serian los campos que coinciden entre 2 tablas diferentes y puedo haciendo al mismo tiempo filtrar mi información en un solo lugar, en un instante a través de una vista

    ![View](../Imagenes/view.png)

4. **Procedures** (Procedimientos)
   - Son lo mismo que una lógica estructurada que a través de un lenguaje nativo del mismo SQL (if, while, for...)
   - Podemos crear funciones:

    ```SQL
    IF a > 0 THEN
    X = y + z
    z = INSTR(Z + 1)
    SELECT * FROM alpha
    PUT alpha IN TABLE X    
    ```

    Hay funciones propias de SQL que nos permiten estableces fechas, quitar espacios, casos en los que se presenta la necesidad de cambiar minúsculas a mayúsculas y diversas más. pero tambien podemos crear funciones

5. **Trigger** (Disparador)
    - Lo que hace es activar una alerta, avisos automáticos cuando hace algún tipo de cambio en la base de datos o table
    - Ejecuta un función o procedimiento cuando la condición del trigger es satisfecha.

---

- `USE` + nombreDeBaseDatos
Usado para acceder a una base de datos
- `SELECT` * `FROM` city
Selecciona todo de ciudad, el simbolo asterisco `*` se refiere a todo

Para ejecutar solo una parte, podriamos solo subrayar el código que queremos e iciamos

#### Crear una base de datos (`CREATE`)

Sintaxis:

```sql
CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] bd_name //Crea una database si no existe
    [create_option] ...

create_option: [DEFAULT] {
    CHRACTER SET [=] charset_name //caracter set por defecto, caracter de codificacion que se utilizara UTF-8, UTF-16
    | COLLATE [=] collation_name //ayuda a organizar y comparar los datos
    | ENCRYPTION [=] {'Y' | 'N'}
}

```

La database se guarda por default en el disco local C en `PorgramsData`

- Creamos una base de datos a través de comandos:

```sql
        CREATE DATABASE jugos; 
```

- Creando una base de datos con el asistente:

Dar click derecho en el apartado de SHEMAS y seleccionamos `Create Schema...`
Se abrira un apartado en el cual elegiremos el nombre de la base de datos y su `charset` con su `Collation`: Si seleccionamos `utf8`, la parte de Collation seleccionar `utf8_general_ci` ya que es de los mas comunes y el que mejor se adapta a utf8 (utf8 son empleados cuando se usan caracteres especiales como la letra 'ñ' o por lo acentos que permite digitar en la base de datos y lo reconozca)

</br>

#### Eliminar una base de datos (`DROP`)

Este privilegio se le da al administrador de la base de datos (DBA) ya que el algo delicado, peligroso

`drop` Se refiere a objetos, como a una columna o una propia tabla, incluso la base de datos

Sintaxis

```SQL
    DROP {DATABASE | SCHEMA} [IF EXISTS] bd_name 
```

- Eliminamos una base de datos a través de comandos

```SQL
        DROP DATABASE jugos;
        DROP SCHEMA jugos;
```

- Eliminar una base de datos desde el Asistente:

Dar click izquierdo en el base de datos a eliminar y seleccionamos `Drop Schema...`

#### Lineas de comando

- Entrar a `C:\Programs Files\MYSQL\MySQL Server 8.0\bin`
- En la ruta seleccionada entramos al ejecutable de MySQL, ejecutamos: `dir mysql.exe`
- O directamente Ingresamos al LocalHost digitando: `mysql.exe -h localhost -u root -p` enter (`-h` es el host, `-u` es el user y `-p` el password)
- Ingresamos la contraseña que nos dara acceso al servidor
- Y ya podemos utilizar los comandos.
- Seleccionamos la database que usaremos o borrar, editar, mostrar: `USE world;`, `CREATE DATABASE jugos;`
- Para salir digitar `exit`

> **Observación**: Los comandos en SQL no son case sensitive. Se pueden escribir tanto en mayúsculas como en minúsculas. (Aunque las buenas prácticas nos sugieren escribir siempre los comandos en mayúscula).

</br>

### Tipos de datos

- **Números enteros**.

| Tipo      | Valor en Bytes | Menor valor (signed) | Menor valor (Unsigned) | Mayor valor (Signed) | Mayor valor (Unsigned) |
|-----------|----------------|----------------------|------------------------|----------------------|------------------------|
| TINYINT   | 1 (8bits)      | -128                 | 0                      | 127                  | 255                    |
| SMALLINT  | 2 (16bits)     | -32768               | 0                      | 32737                | 65535 (2^16(-1))       |
| MEDIUMINT | 3 (24bits)     | -838868              | 0                      | 8388607              | 16777215               |
| INT       | 4 (32bits)     | -2147483648          | 0                      | 2147483647           | 4294967295             |
| BIGINT    | 8 (64bits)     | -2E63                | 0                      | 2E63(-1)             | 2E64(-1)               |

**Unsigned** : Sin signo (+/-)

</br>

- **Números decimales**

| Tipo    | Precisión en Bytes | Tipo   |
|---------|:------------------:|--------|
| FLOAT*  | 4                  | Simple |
| DOUBLE* | 8                  | Doble  |

``*`` coma flotante

Ejemplo: Si declaramos un ``FLOAT (6,4)`` e incluímos el número 76.00009 eñ valor almacenado será 76.0001, un FLOAT con 6 digitos de los cuales 4 van a ser decimales

Si queremos un número con demasiada precisión usamos DOUBLE y si no tanto... usamos FLOAT

</br>

- **Números decimales fijos**

| Tipo              | Cantidad de dígitos |
|-------------------|:-------------------:|
| DECIMAL o NUMERIC | 65                  |

Es un número fijo, Esta es la mejor opción a la hora de mantener un valor exacto con los centavos, incluyendo los ceros a la derecha de la coma. Si declaramos ``DECIMAL (5,3)`` solo podremos almacenar desde -99.999 hasta 99.999

</br>

- **Bit**

| Tipo | Cantidad de bits |
|------|:----------------:|
| BIT  | 64               |

Ejemplo: `BIT(1)` puede ser 0 o 1
`BIT(3)` puede ser 000, 001, 010, 011, 100, 101, 110, 111 (en binario (1,2,3,4,5,6,7))

</br>

Atributos de los campos numéricos

1. ``SIGNED`` o ``UNSIGNED`` : con signo o sin signo.
2. ``ZEROFILL``: Llena los especios con cero. Ejmplo: `INT(5);` si almacenamos 54, el campo va a quedar 00054. Lo malo de zerofill es que ocupa espacio de memoria para almacenar
3. ``AUTO_UNCREMENT``: Hay un incremento secuencial. Ejemplo: 1,2,3,4,5,...; 2,4,8,10... deacuerdo al incremento de rango que nosotros definamos (2 en 2, en 5 en 5, etc)
4. ``OUT OF RANGE``: Es un error que se presenta cuando los valores se salen de los límites.

</br>

- **Fecha y hora**

  </br>

  - ``DATE``: 1000-01-01 hasta 9999-12-31 año/mes/dia. Es el más usado en SQL
  - ``DATETIME``: 1000-01-01 00:00:00 hasta 9999-12-31 23:59:59. Almacena la fecha y la hora. (puede ser utilizado si trabajamos con logs, si un usuario mofico un archivo, esto es muy importante)
  - ``TIMESTAMP``: 1970-01-01 00:00:01 UTC hasta 2038-01-19 UTC. Tiene en consideracion el cambio de horario de cada pais UTC
  - ``TIME``: -838:59:59  Y 839:59:59
  - ``YEAR``: 1901 hasta 2155 (ouede expresarse en formato de 2 o 4 dígitos)

</br>

- **String**

    </br>

  - ``CHAR``: Cadena de caracteres con valor fijoo de a 255. Ejemplo: `CHAR(4)` = "aa" -> "∙∙aa", deacuerdddo a CHAR(4) almacena aa con 2 espacios para ocupar los 4 tamaños de memoria aunque usemos solo 2
  - ``VARCHAR``: Cadena de caracteres con valor variable de 0 a 255. Ejemplo `VARCHAR(4)` = "aa" -> "aa". Solo ocupa los espacios de memoria necesarios y el resto no los utiliza, es lo que mas se utiliza
  - ``BINARY``: Cadena de caracteres con valor fijo de 0 a 255. (Con números binarios - bits). Trabaja con bits, 0 u 1 y se usa bastante
  - ``VARBINARY``: Cadena de caracteres con valor fijo de 0 a 255. (Con numeros binarios - bits). Usa los espacios de memoria que se necesita
  - ``BLOB``: Binarios largos -> ``TIMYBLOB``, ``MEDIUMBLOB``, ``LONGBLOB``. Como si queremos almacenar una foto o un formato grande que este en binario
  - ``TEXT``: Texto Largo -> TIMYTEXT, ``MEDIUMTEXT``, ``LONGTEXT``.
  - ``ENUM``: Definir *opciones* en una lista predefinida -> Talla ENUM('pequeño','medio','grande') es muy útil ya que lo limitamos a esas opciones

    </br>

    **Atributos de los campos string**

    1. `SET` y `COLLATE`: El tipo de conjunto de caracteres que va a aceptar -> utf8, utf-16..., Establecer el tipo de tabla ASCII que estaremos trabajando

    </br>

    **Campos espaciales (GPS)**
    Hoy en dia todos quieren saber de mapas

    1. ``GEOMETRY`` -> Área, 2 dimenciones
    2. ``LINESTRING`` -> Línea
    3. ``POINT`` -> Punto es el más utilizado porque almacena la cordenada axacta, un punto gografico almacena tal cual y es el que se necesita para tener mayor presicion
    4. ``POLYGON`` -> Área, 2 dimenciones

</br>

---

`SHOW DATABASES;` - Saber cuantas databases tines de MySQL.

### Crear una tabla

Ejemplos de Base de datos

Cliente:

- DNI
- Nombre completo
- dirección1
- dirección2
- Barrio
- Ciudad
- Estado
- CP
- edad
- sexo
- límite de credito para compras
- volumen mínimo de jugo que se puede comprar
- y si ya se hizo la primera compra

```Sql
        CREATE DATABASE jugos; //creauns una base de datos
        
        USE jugos; //para acceder a la base de datos
        
        //Creamos una tabla//
        CREATE TABLE TBCLIENTES(
        DNI VARCHAR(20),
        NOMBRE VARCHAR(150),
        DIRECCION1 VARCHAR(150),
        DIRECCION2 VARCHAR(150),
        BARRIO VARCHAR(50),
        CIUDAD VARCHAR(50),
        ESTADO VARCHAR(50),
        CP VARCHAR(10),
        EDAD SMALLINT,
        SEXO VARCHAR(1),
        LIMITE_CREDITO FLOAT,  //float forma de como lo almacenamos, nada que ver con la exhibicion
        VALUMEN_COMPRA FLOAT,
        PRIMERA_COMPRA BIT(1)) //1 si si, 0 si no
        
```

Cada empresa tiene sus propias politicas para la creacion de sus propias bases de datos, de que forma se van a crear las bases de datos, sitaxis, caracteres, varibales...

</br>

#### Registro de productos

- PRODUCTO
- NOMBRE
- ENVASE
- VALUMEN
- SABOR
- PRECIO

```SQL
        //creacion de la tabla por medio del asistente//
        CREATE TABLE `jugos`.`tbproductos`(
        `producto` varchar(20) NULL,
        `nombre` varchar(150) NULL,
        `envase` varchar(50) NULL,
        `valumen` varchar(20) NULL,
        `sabor` varchar(50) NULL,
        `precio` float NULL);

```

</br>

#### Eliminar una tabla

```Sql
        DROP TABLE TABLA_DE_VENDEDORES2;    
```

</br>

#### Insertando registros en la tabla

```SQL
    //insertar en tbproductos
    INSERT INTO tbproductos(  
     producto, nombre, envase, volumen, sabor, precio)
     VALUES ('773912', 'clean', 'Botella pet', '1 litro', 'naranja', 8.01);
```

- Insertando vario valores en un campo

```SQL
    INSERT INTO categoria(
    nombre)
    VALUES('Muebles'), ('Tecnologia'), ('Cocina'), ('Zapatillas');
```

</br>

#### Actualizando registros

```sql
    UPDATE tbproductos SET envase = 'Botella pet' //usamos Update para actualizar
    WHERE producto = '773912'; //filtramos (where) con un campo unico que sea como identificador a donde actualizar

    //Actualizamos producto, el campo donde todo sabor sea limonn, cambiara todas las coincidencias //
    UPDATE tbproducto SET SABOR = 'Cítrico' WHERE SABOR = 'Limón';
```

</br>

#### Remover registros

- `DELETE` se refiere a borrar y esta enfocado mas a los **registros**
- `DROP` Se refiere a **objetos**, como a una columna o una propia tabla, incluso la base de datos

Estos comandos son peligroso y debamos saber donde utilizarlo porque podemos borrar algo que no queremos.

```SQL
    DELETE FROM tbproductos WHERE producto = '773912'; //borra de tabla donde 
```

</br>

> Nota! : Siempre usar `WHERE` para especificar

</br>

#### Crear una llave Primaria

Es una buena practica siempre que se crea una tabla establecer una llave primaria, con ella impedimos que nuetros registros tenga duplicidad de la informacion.

La **llave primaria** es el campo o campos donde ninguno de sus registros se puede repetir y ayuda a mantener la integridad de los datos

`ALTER` = Alterar

```sql

        //alterar tabla productos y agrega la llava primaria al campo producto
        ALTER TABLE tbproductos ADD PRIMARY KEY(producto);    
```

</br>

#### Agregar un campo nuevo a la tabla

``DATE``: año/mes/dia, y se coloca entre comilla simple '1971-05-25'

```sql
//Alteramos la tabla, agregamos una columna llamada "fecha de nacimiento" del tipo date//
ALTER TABLE tbclientes ADD COLUMN(FECHA_NACIMIENTO DATE);
```

</br>

#### Seleccionar

`SELECT` - Seleccionar. Seleccionar campos:

```SQL
SELECT * FROM tbnombre;  //para seleccionar todos los campos "*"

SELECT DNI, NOMBRE, CIUDAD FROM tbnombre;   //para seleccionar determinados campos
```

##### Sleccionando campos y asignandole un alias

`AS` - Para asignar un alias, utilizado cuando quieres leer

```sql
//Seleccionamos los campos con un alias  asignado con "AS"
SELECT NOMBRE AS Nombre_Completo, SEXO AS genero FROM tbnombre; 
```

`LIMIT` - Estamblecemos un límite, en este caso para limitar las lineas que queremos ver

```sql
//Establecemos un limite de 6 refistros por ver//
SELECT NOMBRE AS Nombre_Completo, SEXO AS genero FROM tbnombre LIMIT 6; 
```

##### Filtrar registros

`WHERE` - donde, para especificar en que sitio

```SQL
//mostrara los resultados que su campo tenga maracuya
SELECT * FROM tbproducto WHERE SABOR = 'maracuyá';  
```

###### Filtrando usando mayor que, menor que y diferente

`>` - mayor que
`<` - menor que
`<>` - diferente (diferente en MySQL)
`BETWEEN` - entre. Establece un rango mayor y menor en numeros (puede ser float)

```sql
SELECT * FROM tbcliente WHERE EDAD > 27;

SELECT * FROM tbcliente WHERE EDAD < 27;

SELECT * FROM tbcliente WHERE EDAD <> 26;

//Todos los nombres que esten despues de la E y por orden alfabetico
SELECT * FROM tbcliente WHERE NOMBRE > 'Erica Carvajo';


SELECT * FROM tbproducto WHERE PRECIO_LISTA BETWEEN 28.49 AND 28.52;
```

###### Filtrando fechas

```SQL
SELECT * FROM tbcliente WHERE FECHA_NACIMIENTO = '1995-01-13';

//Busqueda por año, regresa a todos los que coincidan con el año
SELECT * FROM tbcliente WHERE YEAR(FECHA_NACIMIENTO) = 1995;

//Por dia 
SELECT * FROM tbcliente WHERE DAY(nombreCampo) = 20;
```

###### Filtro compuesto

```SQL
//Son lo mismo pero en una tiene logica 
SELECT * FROM tbproducto WHERE PRECIO_LISTA BETWEEN 28.49 AND 28.52;

SELECT * FROM tbproducto WHERE PRECIO_LISTA >= 28.49 AND PRECIO_LISTA <= 28.52;

///Usando operadores condicionales
SELECT * FROM tbproducto WHERE ENVASE = 'LATA' OR ENVASE = 'BOTELLA PET';

SELECT * FROM tbproducto WHERE (PRECIO_LISTA >= 15 AND PRECIO_LISTA <= 25) OR (ENVASE = 'LATA' OR ENVASE = 'BOTELLA PET');
```

DESCRIBE tabla = muestra la estructura de la tabla
