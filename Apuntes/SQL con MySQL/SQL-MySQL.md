# SQL con MySQL

**SQL**: Structured Query Language (SEQUEL)

**Trigger** (Disparador)
    - Lo que hace es activar una alerta, avisos automáticos cuando hace algún tipo de cambio en la base de datos o table
    - Ejecuta un función o procedimiento cuando la condición del trigger es satisfecha.

**Texto**: char, varchar, text...
**Númerico**: int, bigint, smallint, float, bool...(1 verdadero, 0 falso)
**Fecha**: date, datetime, timestamp...

---

`AS` -- Alías
`LIMIT` -- Limita los campos seleccionados
`BETWEEN min AND max` -- poner un límite en filtros **`WHERE`**`PRECIO`**`BETWEEN`**`28.49`**`AND`**`2852`

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

