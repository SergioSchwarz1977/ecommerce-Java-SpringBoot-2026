# Tienda de productos tecnológicos

Aplicación web para administrar un catálogo de productos tecnológicos. El backend está desarrollado con Spring Boot y persiste los productos en MySQL; el frontend está hecho en React y se incluye en el proyecto como archivos estáticos compilados.

## Tecnologías

- Java 25 y Spring Boot 4.1.2
- Spring Web MVC, Spring Data JPA y Hibernate
- MySQL
- React JavaScript (frontend compilado en `src/main/resources/static`)
- Maven Wrapper

## Requisitos

- JDK 25
- MySQL en ejecución

## Configuración y ejecución

1. Crea la base de datos:

   ```sql
   CREATE DATABASE ecommerce;
   ```

2. Configura la URL, el usuario y la contraseña de MySQL en `src/main/resources/application.properties`. La configuración predeterminada usa `jdbc:mysql://localhost:3306/ecommerce` y el usuario `root`.
3. Inicia la aplicación desde la raíz del proyecto:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   También puedes compilar el proyecto con `.\mvnw.cmd clean package` y ejecutar el JAR generado en `target`.

Spring Boot inicia el servidor en el puerto `8080` de forma predeterminada. Hibernate actualiza el esquema de la base de datos al iniciar (`spring.jpa.hibernate.ddl-auto=update`).

## API de productos

La URL base es `http://localhost:8080/productos`.

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/productos` | Lista todos los productos. |
| `GET` | `/productos/{id}` | Busca un producto por su ID. |
| `POST` | `/productos/crear` | Crea un producto y responde con estado `201`. |
| `PUT` | `/productos/actualizar/{id}` | Actualiza los datos de un producto. |
| `DELETE` | `/productos/eliminar/{id}` | Elimina un producto. |
| `GET` | `/productos/categoria/{categoria}` | Lista productos de una categoría. |

Ejemplo de cuerpo JSON para crear o actualizar un producto:

```json
{
  "nombre": "Teclado mecánico",
  "descripcion": "Teclado mecánico con conexión USB",
  "categoria": "Periféricos",
  "precio": 79.99,
  "stock": 12
}
```

Ejemplo de petición:

```bash
curl -X POST http://localhost:8080/productos/crear \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Teclado mecánico","descripcion":"Teclado mecánico con conexión USB","categoria":"Periféricos","precio":79.99,"stock":12}'
```

## Manejo de excepciones

`GlobalException`, mediante `@RestControllerAdvice`, centraliza estas respuestas:

- `ProductoNoEncontradoException`: responde `404 Not Found`.
- `StockInsuficienteException`: responde `400 Bad Request`.

La entidad `Producto` declara validaciones para nombre, descripción, categoría, precio y stock. Los campos del producto son `id`, `nombre`, `descripcion`, `categoria`, `precio` y `stock`.

## Estructura principal

```text
src/main/java/com/ecommerce/tienda/
├── controllers/   # Endpoints REST de productos
├── entity/        # Entidad JPA Producto
├── exception/     # Excepciones y manejador global
├── repository/    # Acceso a datos con Spring Data JPA
└── service/       # Lógica de productos
```

El backend permite solicitudes CORS desde `http://localhost:5173`, origen habitual del servidor de desarrollo de Vite. El repositorio incluye los archivos compilados del frontend, pero no el código fuente del proyecto React.