# Guía de Ejecución - Sakila Microservices

Este documento detalla los pasos necesarios para poner en marcha el ecosistema de microservicios.

## 🛠 Requisitos
* **Java 21**
* **MySQL** (Puerto 3306)
* **Maven**

## 1. Base de Datos
El proyecto utiliza la base de datos de ejemplo **Sakila**.
1. Asegúrate de que MySQL esté corriendo.
2. Crea el esquema: `db/sakila-schema.sql`.
3. Carga los datos: `db/sakila-data.sql`.
4. Verifica las credenciales en el archivo `.env` en la raíz (Usuario: `root`, Password: ``).

## 2. Construcción Inicial
Ejecuta desde la raíz del proyecto para descargar dependencias e instalar los módulos:
```powershell
mvn clean install -DskipTests
```

## 3. Secuencia de Inicio
Es vital seguir este orden para que el registro en Eureka funcione correctamente. Abre una terminal nueva para cada uno:

### Fase 1: Servicios Core
1. **Discovery Service (Eureka Server)**
   ```powershell
   mvn spring-boot:run -pl discovery-service
   ```
   *Acceso: http://localhost:8761*

2. **Auth Service (Segmento de Seguridad)**
   ```powershell
   mvn spring-boot:run -pl auth-service
   ```

3. **Gateway Service (Punto de entrada)**
   ```powershell
   mvn spring-boot:run -pl gateway-service
   ```
   *URL Base: http://localhost:8080*

### Fase 2: Microservicios de Dominio
Estos pueden correr en paralelo:
* **Business Service (GraphQL):** `mvn spring-boot:run -pl business-service` (Puerto 8082)
* **Customer Service (REST):** `mvn spring-boot:run -pl customer-service` (Puerto 8083)
* **Inventory Service (SOAP):** `mvn spring-boot:run -pl inventory-service` (Puerto 8084)
* **Reports Service (WebSockets):** `mvn spring-boot:run -pl reports-service` (Puerto 8085)

## 📡 Tabla de Puertos
| Servicio | Puerto | Protocolo |
| :--- | :--- | :--- |
| Gateway | 8080 | REST |
| Auth | 8081 | REST / JWT |
| Business | 8082 | GraphQL |
| Customer | 8083 | REST |
| Inventory | 8084 | SOAP |
| Reports | 8085 | WebSockets |
| Discovery | 8761 | Eureka Dashboard |
