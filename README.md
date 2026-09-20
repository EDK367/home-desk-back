# HomeDesk Backend

## 1. Descripción del proyecto

HomeDesk Backend es un sistema ERP monolítico desarrollado para la gestión integral de operaciones de compra, venta, inventario y facturación de equipo y mobiliario de oficina y hogar.

### Propósito principal
Centralizar la lógica de negocio y la persistencia transaccional de la plataforma HomeDesk, garantizando consistencia en el control de existencias, trazabilidad completa de movimientos, seguridad basada en roles y comunicación en tiempo real con los clientes de la aplicación.

### Problema que resuelve
Resuelve la desconexión operativa entre áreas comerciales y de almacén mediante:
* Registro y seguimiento de órdenes de compra con cálculo automático de impuestos.
* Recepción de mercadería con generación automática de lotes de inventario y costeo unitario.
* Facturación y despacho de ventas con asignación y consumo estricto de inventario bajo el método PEPS (Primeras Entradas, Primeras Salidas / FIFO).
* Control de existencias con alertas automáticas cuando el stock cae por debajo del nivel mínimo establecido.
* Gestión de cobranzas y múltiples métodos de pago por venta.
* Trazabilidad y auditoría de eventos de usuario (inicio de sesión, creación, edición y cancelación).
* Notificaciones push en tiempo real a clientes web mediante WebSockets.

### Tecnologías principales
* Java 21 LTS
* Spring Boot 4.x
* Spring Data JPA / Hibernate
* PostgreSQL
* Flyway Migration
* Spring Security con JWT (JJWT) y Cookie HTTP-Only para Refresh Token
* Spring WebSocket con broker STOMP
* MapStruct
* SpringDoc OpenAPI (Swagger UI)
* Docker

---

## 2. Arquitectura

El backend sigue una **Arquitectura en Capas (Layered Architecture)** organizada modularmente por dominios funcionales (**Package by Feature**). 

### Capas del sistema
1. **Capa de Presentación / API (`controller`)**: Controladores REST anotados con `@RestController` que exponen endpoints HTTP.
2. **Capa de Seguridad y Filtros (`config.security`)**: Filtro de autenticación `JwtAuthenticationFilter`, manejadores de excepciones de seguridad `CustomAuthenticationEntryPoint` (401) y `CustomAccessDeniedHandler` (403), y configuración de políticas CORS.
3. **Capa de Lógica de Negocio (`service` / `service.impl`)**: Servicios que implementan las reglas empresariales (cálculo de impuestos, control de estados, despacho PEPS, registro de auditoría y emisión de eventos WebSocket) dentro de transacciones gestionadas (`@Transactional`).
4. **Capa de Transferencia y Mapeo (`dto`, `mapper`)**: DTOs inmutables implementados como `record` de Java y mappers bidireccionales generados en tiempo de compilación con MapStruct.
5. **Capa de Acceso a Datos / Persistencia (`repository`, `model`)**: Entidades JPA mapeadas a tablas y vistas de PostgreSQL, administradas a través de interfaces `JpaRepository` de Spring Data JPA.
6. **Capa de Eventos y Notificaciones (`websocket`)**: Emisión de eventos en tiempo real mediante `SimpMessagingTemplate` y captura automática de cambios en entidades a través de un `EntityListener` global de JPA (`WebSocketEntityListener`).

### Flujo de comunicación
* Las peticiones HTTP entrantes atraviesan la cadena de filtros de Spring Security donde se valida el token JWT.
* El controlador delega la operación al servicio correspondiente.
* El servicio interactúa con uno o varios repositorios JPA para leer o persistir información en PostgreSQL.
* Si la operación modifica entidades clave (compras, ventas, inventario, pagos o auditoría), el listener JPA o el servicio invoca a `WebSocketEventPublisher`, el cual difunde eventos asíncronos a los suscriptores conectados al broker STOMP en `/topic/*`.

## 3. Estructura del proyecto

```text
home-desk-back/
├── Dockerfile                      # Archivo de construcción de imagen de contenedor multi-stage
├── HELP.md                         
├── mvnw                            
├── mvnw.cmd                       
├── pom.xml                         # Configuración de dependencias, plugins y build de Maven
├── .env.example                    # Plantilla de variables de entorno requeridas
├── datos.sql                       # Volcado SQL de datos iniciales
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── solairy/
│       │           └── homedesk/
│       │               ├── HomeDeskBackApplication.java   # Clase principal ejecutable de Spring Boot
│       │               ├── audit/                         # Módulo de auditoría de acciones del sistema
│       │               ├── auth/                          # Módulo de autenticación, usuarios, roles y sesiones
│       │               ├── catalog/                       # Módulo de categorías y productos
│       │               ├── common/                        # Clases transversales (respuestas, excepciones, validaciones)
│       │               ├── config/                        # Configuraciones de seguridad, CORS, Swagger y WebSocket
│       │               ├── enums/                         # Enumeraciones de roles, métodos de pago y medidas
│       │               ├── inventory/                     # Módulo de existencias, lotes y movimientos (Kardex)
│       │               ├── payment/                       # Módulo de métodos de pago y pagos de ventas
│       │               ├── purchases/                     # Módulo de órdenes de compra, ítems y estados
│       │               ├── reports/                       # Módulo de reportes analíticos basados en vistas SQL
│       │               ├── sales/                         # Módulo de clientes, ventas, facturación y estados
│       │               ├── suppliers/                     # Módulo de proveedores y teléfonos
│       │               └── websocket/                     # Módulo de mensajería y eventos en tiempo real
│       └── resources/
│           ├── application.yaml                           # Configuración de propiedades de la aplicación                         
│           └── db/
│               └── migration/                             # Scripts de migración versionados de Flyway
│                   ├── V1__init_schema.sql
│                   ├── V2__sessions.sql
│                   ├── V3__seed_data.sql
│                   ├── V4__user_employee_sequence.sql
│                   ├── V5__seed_data_full.sql
│                   └── V6__views_report.sql
```
---

## 4. Tecnologías y dependencias

| Componente | Tecnología | Versión / Detalle | Propósito en el proyecto |
| :--- | :--- | :--- | :--- |
| **Lenguaje** | Java | 21 (LTS) | Lenguaje base del proyecto |
| **Framework Base** | Spring Boot | 4.1.1  | Framework de desarrollo de la aplicación |
| **Acceso a Datos** | Spring Data JPA / Hibernate | Incluido en starter | Mapeo objeto-relacional y repositorios de datos |
| **Base de Datos** | PostgreSQL Driver | Runtime | Conexión con el motor PostgreSQL |
| **Migraciones de BD** | Flyway (`flyway-database-postgresql`) | Incluido en starter | Versionado y ejecución de scripts DDL y DML |
| **Seguridad** | Spring Security | 6+ | Autenticación, autorización RBAC y filtros HTTP |
| **Tokens JWT** | JJWT (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`) | 0.12.6 | Generación, firma y validación de tokens de acceso y refresco |
| **Tiempo Real** | Spring WebSocket + STOMP | Incluido en starter | Broker de mensajería y eventos push a clientes |
| **Mapeo de Objetos** | MapStruct + Processor | 1.6.3 | Transformación automática entre entidades y DTOs |
| **Utilidades de Código** | Project Lombok | Runtime/Provided | Generación de getters, setters y constructores |
| **Binding Lombok-MapStruct**| `lombok-mapstruct-binding` | 0.2.0 | Compatibilidad entre procesadores de anotaciones |
| **Documentación API** | SpringDoc OpenAPI Starter WebMVC UI | 3.1.0 | Generación de especificación OpenAPI y Swagger UI |
| **Contenedores** | Docker | Multi-stage | Empaquetado y ejecución portable del servicio |

---

## 5. Requisitos previos

Para compilar y ejecutar el proyecto localmente se requiere:
* **Java Development Kit (JDK)**: Versión 21 instalada y configurada en la variable de entorno `JAVA_HOME`.
* **PostgreSQL**: Versión 18 o superior ejecutándose localmente o en un contenedor accesible.
* **Apache Maven**: Versión 3.9 o superior (opcional si se utiliza el script `./mvnw` provisto en la raíz).
* **Docker**: Opcional, requerido únicamente si se desea ejecutar el servicio empaquetado en contenedor.

---

## 6. Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/EDK367/home-desk-back.git
cd home-desk-back
```

### 2. Crear la base de datos en PostgreSQL
Conéctese a su servidor PostgreSQL y ejecute:
```sql
CREATE DATABASE homedesk;
```

### 3. Configurar variables de entorno
Copie la plantilla de variables de entorno y complete sus valores:
```bash
cp .env.example .env
```
Edite `.env` con los parámetros correspondientes a su entorno local (#7-variables-de-entorno)).

### 4. Compilar el proyecto
Ejecute la compilación mediante el Maven Wrapper para verificar la descarga de dependencias y la generación de mappers de MapStruct:
```bash
./mvnw clean compile
```

Las migraciones de Flyway se ejecutarán de forma automática en el primer arranque de la aplicación.

---

## 7. Variables de entorno

Las propiedades de configuración están parametrizadas en `src/main/resources/application.yaml` a través de variables de entorno.

| Variable | Descripción | Obligatoria | Ejemplo seguro |
| :--- | :--- | :--- | :--- |
| `DB_URL` | URL de conexión JDBC hacia PostgreSQL | Sí | `jdbc:postgresql://localhost:5432/homedesk` |
| `DB_USER` | Usuario con permisos en la base de datos | Sí | `postgres` |
| `DB_PASSWORD` | Contraseña del usuario de base de datos | Sí | `tu_password_local` |
| `DB_NAME` | Nombre de la base de datos | No (referencial) | `homedesk` |
| `JWT_KEY` | Clave secreta para firma HMAC-SHA de tokens JWT (mínimo 256 bits / 32 caracteres) | Sí | `una_clave_secreta_muy_segura_de_mas_de_32_caracteres_123` |
| `JWT_COOKIE_NAME` | Nombre de la cookie para almacenar el refresh token | Sí | `refreshToken` |
| `JWT_COOKIE_PATH` | Ruta de validez para la cookie del refresh token | Sí | `/api/v1/auth` |
| `JWT_COOKIE_DOMAIN` | Dominio asociado a la cookie del refresh token | Sí | `localhost` |
| `JWT_COOKIE_SECURE` | Define si la cookie requiere transporte HTTPS (`true`/`false`) | Sí | `false` (desarrollo) / `true` (producción) |
| `JWT_COOKIE_SAME_SITE` | Política de SameSite para la cookie (`Strict`, `Lax`, `None`) | Sí | `Lax` |
| `JWT_COOKIE_HTTP_ONLY` | Define si la cookie tiene bandera HttpOnly (`true`/`false`) | Sí | `true` |
| `HOME_DESK_FRONT` | Origen del frontend permitido para solicitudes CORS | Sí | `http://localhost:4200` |
| `PEPPER_SECRET` | Valor secreto adicional utilizado en el algoritmo de cifrado de contraseñas | Sí | `un_secreto_pepper_local_456` |

---

## 8. API

### Documentación Swagger / OpenAPI
El proyecto incluye la dependencia `springdoc-openapi-starter-webmvc-ui` versión `3.1.0` y la configuración `SwaggerConfig.java` para autenticación Bearer (`bearerAuth`).
* URL de la interfaz interactiva: `http://localhost:8080/swagger-ui/index.html` o `http://localhost:8080/swagger-ui.html`
* Especificación OpenAPI en formato JSON: `http://localhost:8080/v3/api-docs`

*(Nota: Habilite el swagger en `aplication.yaml`).*


## 9. Base de datos

### Motor y conexión
* **Motor**: PostgreSQL.
* **Pool de conexiones**: HikariCP (gestionado por Spring Boot Starter JPA).
* **Validación de esquema**: `spring.jpa.hibernate.ddl-auto: validate`. La estructura debe coincidir estrictamente con los esquemas creados por Flyway.

### Migraciones con Flyway
Las migraciones se encuentran en `src/main/resources/db/migration` y se ejecutan secuencialmente:
1. `V1__init_schema.sql`: Creación de 21 tablas del dominio, claves primarias, foráneas, índices y restricciones únicas.
2. `V2__sessions.sql`: Creación de la tabla `sessions` e índices para gestión de refresh tokens revocables.
3. `V3__seed_data.sql`: Carga inicial de roles y 4 usuarios operativos (`adminstrador`, `comprador`, `inventario`, `vendedor`).
4. `V4__user_employee_sequence.sql`: Secuencia y trigger para autoincrementar el número de empleado en la tabla `users`.
5. `V5__seed_data_full.sql`: Carga extensiva de datos de prueba para clientes, productos, proveedores, órdenes de compra y ventas.
6. `V6__views_report.sql`: Creación de 7 vistas analíticas de PostgreSQL (`vw_*`) consumidas por la capa de reportes.

Existe un archivo complementario `datos.sql` en la raíz con un volcado completo de la base de datos.
