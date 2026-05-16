# Example Scala

A Scala + Spring Boot REST API example demonstrating a simple Todo and Person domain with controllers, repositories, and a layered architecture.

## Tech Stack

- Scala
- Spring Boot
- Gradle (with Groovy DSL)
- Docker / Docker Compose
- JaCoCo (test coverage)

## Project Structure

```
src/main/scala/example/
  Application.scala           # Spring Boot entry point
  controllers/
    PersonController.scala
    TodoController.scala
  domain/
    Todo.scala
  repositories/
    TodoRepository.scala
```

## Build & Run

```bash
# Run with Gradle
./bootrun.sh

# Run with Docker
docker-compose up
```

## Testing

```bash
./gradlew test
./gradlew jacocoTestReport
```

## Configuration

Environment profiles:

| File | Description |
|------|-------------|
| `env` | Default environment variables |
| `env.prod` | Production overrides |
| `env.console` | Console/debug settings |
