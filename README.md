# Examen Tecnico Stefanini

API REST desarrollada con Spring Boot para la consulta y creación de mascotas usando la API externa de Swagger Petstore.

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Gradle Groovy
- Spring Web MVC
- JUnit 5
- Mockito
- MockMvc

## Arquitectura

El proyecto esta organizado siguiendo una aproximacion de Clean Architecture por contexto:

```text
src/main/java/com/examen/stefanini/context/pet
├── application
│   ├── CreatePet.java
│   └── GetPet.java
├── domain
│   ├── entities
│   ├── exceptions
│   └── repositories
└── infraestructure
    ├── config
    ├── handler
    └── repositories
```

- `domain`: entidades, excepciones y puertos.
- `application`: casos de uso de negocio.
- `infraestructure/handler`: controllers, requests, responses y manejo global de errores.
- `infraestructure/repositories`: adaptador para consumir Swagger Petstore.
- `infraestructure/config`: configuracion de clientes externos.

## Endpoints

### Obtener mascota

```http
GET /api/pet/{petId}
```

Respuesta exitosa:

```json
{
  "id": 10,
  "name": "Max",
  "status": "available"
}
```

### Crear mascota

```http
POST /api/pet
Content-Type: application/json
```

Request:

```json
{
  "id": 30,
  "status": "available",
  "name": "Luna"
}
```

Respuesta exitosa:

```json
{
  "transactionId": "uuid-v4",
  "dateCreated": "2026-07-03T10:00:00-06:00",
  "status": "available",
  "name": "Luna"
}
```

## Manejo de errores

Los errores de la API externa se convierten a `GeneralException` y se responden mediante `GeneralExceptionHandler`.

Ejemplo:

```json
{
  "timestamp": "2026-07-03T10:00:00-06:00",
  "status": 404,
  "message": "Pet not found"
}
```

## Ejecutar el proyecto

En Windows:

```powershell
.\gradlew.bat bootRun
```

La aplicacion queda disponible en:

```text
http://localhost:8080
```

## Probar endpoints

GET:

```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/api/pet/10
```

POST:

```powershell
$body = @{
  id = 30
  status = "available"
  name = "Luna"
} | ConvertTo-Json

Invoke-RestMethod -Method Post `
  -Uri http://localhost:8080/api/pet `
  -ContentType "application/json" `
  -Body $body
```

## Tests

Ejecutar pruebas:

```powershell
.\gradlew.bat clean test
```

El proyecto incluye:

- Tests unitarios para los casos de uso `GetPet` y `CreatePet`.
- Tests de integracion para el controller usando `MockMvc`.
- Repositorio fake en pruebas de integracion para no depender de Swagger Petstore real.

## Evidencias

Si se requiere alguna evidencia de la ejecucion o pruebas del proyecto, se puede consultar la carpeta [evidencias](C:/Users/omarm/Documents/examen-tecnico-stefanini/evidencias).
