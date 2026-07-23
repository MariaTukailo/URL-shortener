# URL Shortener - Backend

A link shortening service. Users enter a long URL and receive a short link,
which redirects to the original address when visited.

##  Frontend

Client application: [url-shortener-frontend](https://github.com/MariaTukailo/url-shortener-frontend)

## 🛠 Technologies

- Java 17
- Spring Boot 4
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- MapStruct
- Lombok
- Swagger (OpenAPI 3)
- Docker / Docker Compose
- JUnit 5 / Mockito

##  Security

The main focus is on Spring Security. Authentication and authorization are implemented using JWT tokens:

- User logs in or registers → receives a token
- Token is passed in the `Authorization: Bearer <token>` header
- Token lifetime — 24 hours
- Role-based access: USER and ADMIN

##  Roles

| Role | Capabilities |
|------|-------------|
| **USER** | Registration, login, URL shortening, viewing own links, deleting own links |
| **ADMIN** | Login, viewing all users, editing and deleting users, viewing all links, deleting any links |

##  How URL Shortening Works

1. User submits a long URL
2. Server generates a unique short code (6 characters)
3. The link is saved in the `links` table in the database
4. When visiting `http://localhost:8080/r/{shortCode}`, the server looks up the code in the database, finds the original URL, and performs a redirect (302)

##  Getting Started

### Local
1. Create `url_shortener` database in PostgreSQL
2. Configure `application.properties`
3. Run: `mvn spring-boot:run`
4. Swagger: `http://localhost:8080/swagger-ui/index.html`

### Docker
```bash
docker compose up --build