# CarFind — cómo levantar la página completa

> Documentación de todos los cambios y avances del proyecto: [`DOCUMENTACION.md`](DOCUMENTACION.md).

La página tiene tres partes que se conectan así:

```
Navegador ──► Frontend (React + Vite, puerto 5173)
                 │  /api/**  y  /uploads/**   (proxy de Vite)
                 ▼
              Backend (Spring Boot, puerto 8080) ──► Base de datos MySQL/MariaDB "compraventa" (puerto 3307)
```

## 1. Base de datos

1. Iniciá MySQL/MariaDB desde XAMPP (puerto **3307**, usuario `root` sin contraseña, como en
   `Backend_CarFind/carfind/src/main/resources/application.properties`).
2. Creá la base `compraventa` e importá `Base_De_Datos_CarFind/compraventa.sql` (por ejemplo desde phpMyAdmin).

No hace falta correr nada más: al iniciar, el backend ejecuta
`Backend_CarFind/carfind/src/main/resources/db/integracion.sql`, que agrega lo que el frontend necesita
(foto de perfil, tipo de reporte, respuestas de la administración, informes, rol `ADMIN`, etc.).
Ese script se puede correr muchas veces sin romper nada.

## 2. Backend

Desde Eclipse: clic derecho sobre `CarfindApplication.java` → **Run As → Java Application**
(o **Spring Boot App**). Con Maven instalado también se puede desde la consola:

```bash
cd Backend_CarFind/carfind
mvn spring-boot:run
```

Queda en http://localhost:8080 (si lo desplegás como WAR en Tomcat con otro context path, cambiá
`BACKEND_URL` en `Frontend_CarFind/vite.config.js`). La API que usa el frontend está bajo `/api/web/**`
(paquete `com.back.carfind.web`); los CRUD originales siguen en `/api/**`.
Las fotos que se suben se guardan en la base (tabla `archivos`) y se sirven en `/uploads/**`.

## 3. Frontend

```bash
cd Frontend_CarFind
npm install
npm run dev
```

Abrí http://localhost:5173. El archivo `.env` ya trae `VITE_USE_MOCK=false` y `VITE_API_URL=/api/web`,
así que todas las pantallas leen y guardan en la base de datos a través del backend.
Con `VITE_USE_MOCK=true` vuelven los datos de prueba (sin backend).

## Usuarios de prueba

| Correo                 | Contraseña   | Rol            |
|------------------------|--------------|----------------|
| juan.perez@mail.com    | Carfind123   | Usuario        |
| maria.gomez@mail.com   | Carfind123   | Administradora (ve el botón "Administración" y el panel `/admin`) |

También podés crear una cuenta nueva desde "Registrarse".

## Qué pantalla usa qué endpoint

| Pantalla                         | Endpoints (`/api/web/...`)                                      | Tablas principales |
|----------------------------------|-----------------------------------------------------------------|--------------------|
| Login / Registro                 | `auth/login`, `auth/registro`, `auth/sesion`, `auth/logout`      | usuarios, sesiones, usuario_roles |
| Inicio (filtros y buscador)      | `publicaciones`, `publicaciones/filtros`                         | publicaciones, autos, modelos, marcas, multimedia |
| Detalle de publicación           | `publicaciones/:id`                                              | ídem + direcciones |
| Crear publicación                | `POST publicaciones` (multipart con fotos)                       | autos, publicaciones, multimedia |
| Wishlist                         | `wishlist`                                                      | favoritos |
| Perfil / Perfil de otro usuario  | `perfil`, `perfil/foto`, `usuarios/:id`                          | usuarios, direcciones |
| Configuración                    | `configuracion/**`                                              | usuarios, direcciones, preferencias_usuario |
| Mensajes                         | `conversaciones/**`                                             | chats, mensajes |
| Reportes                         | `reportes`, `publicaciones/:id/reportes`                         | reportes |
| Panel de administración          | `admin/**`                                                      | reportes, respuestas_reporte, informes_admin |

---

# Publicar en internet gratis (Vercel + Render + Aiven)

Vercel solo aloja el **frontend** (React). El backend de Spring Boot y la base MySQL no pueden correr
en Vercel, así que van en otros servicios con plan gratuito:

```
Navegador ──► Vercel (frontend)
                 │  /api/** y /uploads/**  (rewrites de vercel.json)
                 ▼
              Render (backend, Dockerfile) ──► Aiven (MySQL)
```

Las fotos que se suben se guardan en la base de datos (tabla `archivos`), así no se pierden aunque
Render reinicie el servidor.

> **Orden:** primero la base, después el backend (necesita los datos de la base) y por último el
> frontend (necesita la URL del backend).

## 1. Base de datos en Aiven (MySQL gratis)

1. Creá una cuenta en https://aiven.io → **Create service → MySQL** → plan **Free**.
2. Cuando el servicio esté en *Running*, en **Overview → Connection information** anotá:
   **Host**, **Port**, **User** (`avnadmin`), **Password** y **Database name** (`defaultdb`).
3. Importá **`Base_De_Datos_CarFind/compraventa_nube.sql`** (no `compraventa.sql`). Es la misma base,
   pero preparada para la nube: Aiven exige clave primaria en cada tabla desde su creación y no permite
   vistas con `DEFINER=root`. Con la consola de MySQL:
   ```bash
   mysql --ssl-mode=REQUIRED -h <host> -P <port> -u avnadmin -p defaultdb < Base_De_Datos_CarFind/compraventa_nube.sql
   ```
   O con MySQL Workbench / HeidiSQL: nueva conexión con esos datos (activando SSL) → abrir el archivo → ejecutar.

   Si cambiás `compraventa.sql` (por ejemplo, exportándolo de nuevo desde phpMyAdmin), regenerá la
   versión para la nube con `python preparar_para_nube.py` dentro de `Base_De_Datos_CarFind`.

   Si al importar falla la creación de los *triggers* por permisos, en Aiven entrá a
   **Service settings → Advanced configuration**, activá `mysql.log_bin_trust_function_creators` e
   importá de nuevo.

## 2. Backend en Render (gratis)

1. Creá una cuenta en https://render.com con tu GitHub → **New → Web Service** → elegí este repositorio.
2. Configuración:
   - **Branch**: la rama con este código.
   - **Root Directory**: `CarFindWeb/Backend_CarFind/carfind`
   - **Language / Runtime**: `Docker` (usa el `Dockerfile` de esa carpeta).
   - **Instance Type**: `Free`.
3. **Environment Variables** (con los datos de Aiven del paso 1):
   ```
   DB_URL=jdbc:mysql://<host>:<port>/defaultdb?sslMode=REQUIRED
   DB_USER=avnadmin
   DB_PASSWORD=<password de Aiven>
   ```
4. **Create Web Service**. La primera vez tarda varios minutos (compila con Maven).
   Cuando termine, abrí la URL que te da Render (ej: `https://carfind-backend.onrender.com`):
   tiene que decir "¡Backend levantado con éxito!".

**Importante del plan gratis de Render:** el servidor se duerme después de 15 minutos sin visitas y la
primera visita siguiente tarda alrededor de un minuto en despertarlo. Para que no se duerma podés usar
https://uptimerobot.com (gratis): un monitor HTTP a la URL del backend cada 10 minutos. Las 750 horas
gratis por mes alcanzan para tenerlo prendido todo el mes.

## 3. Frontend en Vercel (gratis)

1. En `Frontend_CarFind/vercel.json` reemplazá `https://TU-BACKEND.onrender.com` (aparece dos veces)
   por la URL de tu backend en Render. Hacé commit y push.
2. Entrá a https://vercel.com con tu GitHub → **Add New → Project** → importá este repositorio.
3. Configuración del proyecto:
   - **Root Directory**: `CarFindWeb/Frontend_CarFind`
   - **Framework Preset**: Vite (lo detecta solo). Build: `npm run build`, Output: `dist`.
   - No hace falta agregar variables: el `.env` ya trae `VITE_USE_MOCK=false` y `VITE_API_URL=/api/web`.
4. **Deploy**. Vercel despliega en producción la rama elegida en **Settings → Git → Production Branch**;
   cada push a esa rama vuelve a desplegar solo.

`vercel.json` reenvía `/api/**` y `/uploads/**` al backend (igual que el proxy de Vite en local) y manda
el resto de las rutas a `index.html`, para que recargar `/perfil` o `/publicacion/3` no dé 404.

## Antes de compartir la página

- Los usuarios de prueba (`juan.perez@mail.com` y `maria.gomez@mail.com`, contraseña `Carfind123`)
  existen también en la base de la nube, y María es administradora. Iniciá sesión con cada uno y
  cambiá la contraseña desde **Configuración**.
- Si la página muestra "El servidor no responde", el backend de Render probablemente estaba dormido:
  esperá un minuto y recargá.

## Alternativa: Railway (pago, con crédito de prueba)

Railway aloja el backend y MySQL juntos y no se duerme, pero no es gratis después del crédito de prueba.
Mismos pasos: base **MySQL** (importar `compraventa_nube.sql`), servicio desde GitHub con
**Root Directory** `CarFindWeb/Backend_CarFind/carfind` y las variables
`DB_URL=jdbc:mysql://${{MySQL.MYSQLHOST}}:${{MySQL.MYSQLPORT}}/${{MySQL.MYSQLDATABASE}}`,
`DB_USER=${{MySQL.MYSQLUSER}}`, `DB_PASSWORD=${{MySQL.MYSQLPASSWORD}}`. En `vercel.json` va la URL de Railway.
