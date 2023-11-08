# SQL con MySQL

**SQL**: Structured Query Language (SEQUEL)

- **Trigger** (Disparador)
  - Lo que hace es activar una alerta, avisos automáticos cuando hace algún tipo de cambio en la base de datos o table
  - Ejecuta un función o procedimiento cuando la condición del trigger es satisfecha.

</br>

- **Texto**: char, varchar, text...
- **Númerico**: int, bigint, smallint, float, bool...(1 verdadero, 0 falso)
- **Fecha**: date, datetime, timestamp...

</br>

> **FLOAT**. Permite almacenar números **decimales de punto flotante pequeños**, por lo que **sus cálculos son aproximados**
>

---

- `AS` -- Para asignar un alias, utilizando un nombre diferente
- `WHERE` -- para filtrar (donde)
- `LIMIT` -- [Comando LIMIT](#comando-limit), Limita los campos seleccionados.
- `BETWEEN` -- (entre) Selecciona valores dentro de un rango _min_ y _max_, pueden ser números, texto. Hace uso de `AND`:  **WHERE**`PRECIO`**BETWEEN**`28.49`**AND**`28.52`
- `IN` -- [El operador IN](#operador-in) es una abreviatura de múltiples condiciones `OR`
- `LIKE` -- [Operador LIKE](#operador-like), se utiliza en una WHERE cláusula para buscar un patrón específico en una columna.
- `DISTINCT` -- [Comando DISTINCT](#distinct), Solo devuelve registros con valores diferentes.
- `ORDEN BY` -- [Comando ORDEN BY](#order-by), Ordena los registros en orden ascendente (menor a mayor) y descendente (mayor a menor).
- `GROUP BY` -- [Group by](#group-by), Presenta el resultado agrupando valores numéricos empleando una clave de criterio.

</br>

```sql
/*---INSERTAR---*/
INSERT INTO tbporductos(producto, nombre, envase)
VALUES ('838819', 'clean', 'botella pet');
/*---ACTUALIZAR, ALTERAR REGISTROS---*/
UPDATE tbproductos SET nombre = 'ahorrador'WHERE producto = '838819';
/*---AGREGAR LLAVE PRIMARIA---*/
ALTER TABLE tbproductos ADD PRIMARY KEY(producto);
/*---INSERTAR UNA COLUMNA---*/
ALTER TABLE tbcliente ADD COLUMN(FECHA_NACIMIENTO DATE);
/*---FILTRAR POR FECHAS---*/
SELECT * FROM tbclientes WHERE YEAR(FECHA_NACIMIENTO) = 1995; /*aqui lo lee como entero, en string fecha completa*/
SELECT * FROM tbclientes WHERE DAY(FECHA_NACIMIENTO) = 20;
```

---

- Alias `AS`

```SQL
    SELECT DNI AS IDENTIFICACION, NOMBRE AS CLIENTE FROM tabla_de_clientes;
```

---

## Condicionales

- Operación **OR** - es verdadero si una de sus condiciones es verdadero
- Operación **AND** - verdadero si todas sus condiciones son verdaderas
- Operación NOR (**NOT OR**) - Negación de la operación OR
- Operación NAND **NOT AND** - Negación de la operación AND

> [!Note]
> Verdadero es 1 y Falso es 0

```SQL
    NOT ((V AND F) OR NOT (F OR F))
    NOT ((F) OR NOT (F))
    NOT ((F OR V))
    NOT ((V))
    /*Falso*/

    (NOT ((3 > 2) OR (4 >= 5)) AND (5 > 4) ) OR (9 > 0)
    (NOT ((3 > 2) OR (4 >= 5)) AND (5 > 4) ) OR (9 > 0)
    (NOT ((Verdadero) OR (Falso)) AND (Verdadero) ) OR (Verdadero)
    (NOT (Verdadero) AND (Verdadero) ) OR (Verdadero)
    (Falso AND Verdadero) OR (Verdadero)
    (Falso) OR (Verdadero)
    /*Verdadero*/ 
```

</br>

### Operador IN

El operador `IN` permite especificar múltiples valores en una cláusula `WHERE`.
El operador `IN` es una abreviatura de múltiples condiciones `OR`.

- Sintaxis

```sql
    SELECT column_name(s)
    FROM table_name
    WHERE column_name IN (value1, value2, ...);

    /*  OR  */

    SELECT column_name(s)
    FROM table_name
    WHERE column_name IN (SELECT STATEMENT);
```

- Ejemplos:

```sql
    /*Selects all customers that are located in "Germany", "France" or "UK"*/
    SELECT * FROM Customers
    WHERE Country IN ('Germany', 'France', 'UK');

    /*selects all customers that are NOT located in "Germany", "France" or "UK"*/
    SELECT * FROM Customers
    WHERE Country NOT IN ('Germany', 'France', 'UK');

    /*selects all customers that are from the same countries as the suppliers*/
    SELECT * FROM Customers
    WHERE Country IN (SELECT Country FROM Suppliers);

    SELECT * FROM tabla_de_clientes WHERE CIUDAD IN ('ciudad de mexico', 'guadalajara') AND (EDAD between 20 AND 25)
```

</br>

### Operador LIKE

El operador `LIKE` se utiliza en una cláusula `WHERE` para buscar un patrón específico en una columna.
Hay dos comodines que se utilizan a menudo junto con el operador `LIKE`:

- El signo de porcentaje (%) representa cero, uno o varios caracteres.
- El signo de subrayado (_) representa un solo carácter.

> ¡El signo de porcentaje y el guión bajo también se pueden utilizar en combinaciones!

**Sintaxis:**

- **< condicion>** -- El texto utilizado
- **%** -- Representa cualquier registro génerico antes, despues o antes y dedpués de la condición .

```sql
    SELECT *(column1, ...) FROM table_name WHERE CAMPO LIKE ´%<condicion>´;
```

Algunos ejemplos:

| LIKE Operator                  | Description                                                                  |
|--------------------------------|------------------------------------------------------------------------------|
| WHERE CustomerName LIKE 'a%'   | Finds any values that start with "a"                                         |
| WHERE CustomerName LIKE '%a'   | Finds any values that end with "a"                                           |
| WHERE CustomerName LIKE '%or%' | Finds any values that have "or" in any position                              |
| WHERE CustomerName LIKE '_r%'  | Finds any values that have "r" in the second position                        |
| WHERE CustomerName LIKE 'a_%'  | Finds any values that start with "a" and are at least 2 characters in length |
| WHERE CustomerName LIKE 'a__%' | Finds any values that start with "a" and are at least 3 characters in length |
| WHERE ContactName LIKE 'a%o'   | Finds any values that start with "a" and ends with "o"                       |

</br>

### DISTINCT

Solo devuelve registros con valores diferentes. Devuelve de un campo "x" los valores que son solo diferentes de ese mismo campo

- Sitaxis

```sql
    SELECT DISTINCT column1, column2, ...
    FROM nametable;
```

- Ejemplo:

```SQL
    SELECT DISTINCT ENVASE, TAMANO FROM tabla_productos;
    SELECT DISTINCT ENVASE, TAMANO, SABOR FROM tabla_de_productos WHERE SABOR = 'naranja';
```

> [!Note]
> Recordar que `DISTINCT` va después de `SELECT`

</br>

### Comando LIMIT

Limita el número de registros exhibidos. Se pone al final de nuestra consulta

- Sintaxis

```sql
    SELECT column1, .... FROM tb LIMIT numero;
```

Tambien se pueden limitar otros campos usando otros indices

- Ejemplos:

```sql
    /*muestra 5 registros*/
    SELECT * FROM tabla_de_productos LIMIT 5;
    /*selecciona las columnas de la tabla empezando por el indice numero 3 y mostrando 2 consultas*/
    SELECT column1, .... FROM tb LIMIT 3,2;
    /*Obtener las 10 primeras ventas del día 01/01/2017*/
    SELECT * FROM facturas WHERE FECHA_VENTA = '2017/01/01' LIMIT 10;
```

</br>

### ORDER BY

La palabra clave `ORDER BY` se utiliza para ordenar el conjunto de resultados en ascendente o orden descendente.

La palabra clave `ORDER BY` ordena los registros en orden ascendente (menor a mayor) y descendente (mayor a menor) de forma predeterminada. Para ordenar los registros en orden descendente, utilice la palabra clave `DESC`

> [!Note]
> Por defecto `ORDER BY` usa los valores en orden **ascendente** en el caso de letras lo hace en orden alfabetico

- Sintaxis

```sql
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC;
```

Se pueden emplear "**n**" campos al mismo tiempo como criterio de selección.

```sql
    SELECT * FROM tb ORDER BY campo_1 DESC, Campo_2 ASC;
```

- Ejemplo:

```sql
    SELECT * FROM tabla_de_productos ORDER BY NOMBRE_DEL_PRODUCTO DESC;
    /**Ordena la consulta con envase de forma descendiente y su nombre de producto en ascendiente*/
    SELECT * FROM tabla_de_productos ORDER BY ENVASE DESC, NOMBRE_DEL_PRODUCTO ASC;
```

</br>

### GROUP BY

Presenta el resultado agrupando valores numéricos empleando una clave de criterio, osea, un campo "x" es agrupado usando alguna función como `SUM` dando un solo valor al campo "x" que se relaciona con un campo "y" (normalmente este campo tiene valores identicos lo que hace que el campo x pueda agruparses).

La sentencia `Group By` tiene como finalidad la de agrupar valores idénticos de una tabla. Al aplicar esta sentencia a una/s columna/s de la tabla origen, tenemos como resultado una única fila resumen por cada grupo de elementos únicos formados.

La declaración `GROUP BY` a menudo se usa con funciones de agregacion que nos permiten efectuar operaciones sobre un conjunto de resultados, pero devolviendo un único valor agregado para todos ellos. Alguna de estas funciones de agregación son: ``COUNT()``, ``SUM()``, ``MAX()``, ``MIN()``, ``AVG()`` (valor promedio), ``STDEV()``, ``STDEV()``, ``VAR()``, ``VARP()``.

- Sintaxis

```sql
SELECT campos
FROM tb GROUP BY campo;


SELECT campos
FROM table_name
WHERE condition
GROUP BY column_name(s)
ORDER BY column_name(s);
```

- Ejemplos

```sql

```