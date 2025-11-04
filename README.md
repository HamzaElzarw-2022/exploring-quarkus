# exploring-quarkus

This project uses Quarkus to expose a simple household services SaaS backend. It includes auth with JWT, categories, services, and bookings.

## Quick start (Native only, no GraalVM required)

Build a native executable using the Quarkus CLI and containerized native toolchain, then run it in a Docker container.

1) clone the repository:

```bash
git clone https://github.com/HamzaElzarw-2022/exploring-quarkus.git
cd exploring-quarkus
```

2) Build native (uses container build, so no local GraalVM is needed):

```bash
quarkus build --native --no-tests -Dquarkus.native.container-build=true
```

This produces a native binary at `target/exploring-quarkus-1.0.0-SNAPSHOT-runner`.

3) Build and run the native container image:

```bash
docker build -f src/main/docker/Dockerfile.native -t exploring-quarkus-native:latest .
docker run --rm -p 8080:8080 exploring-quarkus-native:latest
```

- Swagger UI: http://localhost:8080/q/swagger-ui
- Dev UI is not available in container runtime; for hot-reload use the Java dev mode below.

## Quick start (Java dev mode)

If you have Java installed and want live reload during development:

```bash
./mvnw quarkus:dev
```

Dev UI: http://localhost:8080/q/dev
Swagger UI: http://localhost:8080/q/swagger-ui

Seed data is loaded from `src/main/resources/import.sql` into an in-memory H2 database on start.

## Auth
- Register: POST /auth/register { username, password }
- Login: POST /auth/login { username, password } -> { token }

Use the returned JWT as `Authorization: Bearer <token>`.

## Catalog
- GET /categories -> list categories
- GET /categories/{id}/services -> services in a category
- GET /services?categoryId=... -> list services, optional filter
- GET /services/{id} -> service details

## Bookings (auth required)
- GET /bookings -> list my bookings
- POST /bookings { serviceId, scheduledAt: ISO-8601, address, notes? } -> created booking

## CORS
CORS is enabled for all origins to simplify local mobile development. Adjust in `application.properties` for production.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Packaging and running the application (JVM)

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable (local or container)

You can also use Maven to create a native executable:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/exploring-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)