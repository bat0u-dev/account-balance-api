# 💰 Account Balance API

Spring Boot REST API для управления балансом аккаунтов с транзакциями и поддержкой валют.

## 🛠️ Стек
- Java 21 + Gradle 8.5
- Spring Boot (Web, Data JPA)
- H2 Database (in-memory)
- MapStruct
- REST Assured, JUnit 5
- Docker + Docker Compose
- GitHub Actions CI
- Swagger (OpenAPI UI)

## 🚀 Запуск

### Локально
```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

### Через Docker
```bash
docker build -t account-api .
docker run -p 8087:8087 -e SPRING_PROFILES_ACTIVE=dev account-api
```

### Через Docker Compose
```bash
docker-compose up --build
```

## 📘 Swagger UI
[http://localhost:8087/swagger-ui.html](http://localhost:8087/swagger-ui.html)

## 📫 API Примеры

### Создать баланс
```http
POST /balances
{
  "name": "my-wallet"
}
```

### Получить баланс
```http
GET /balances/my-wallet
```

### Добавить транзакцию
```http
POST /balances/my-wallet/transactions
{
  "type": "DEPOSIT",
  "amount": 100.0,
  "currency": "USD"
}
```

## 🧪 Тесты
```bash
./gradlew test
```

## 🐙 CI
GitHub Actions: `.github/workflows/ci.yml`
