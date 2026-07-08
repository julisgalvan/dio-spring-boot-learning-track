# DIO Spring Boot - Final Project 05: Spring AI (budgeting)

## Introduction

This final module applies Spring AI in a budgeting API while preserving the same layered architecture used across the track.

The goal is to integrate AI capabilities without bypassing domain and use case boundaries.

## Code Context

The project processes voice commands to create and query financial transactions.

Primary flow:

1. Client uploads an audio file.
2. Audio is transcribed into text.
3. The model selects an application tool/use case.
4. The use case persists or queries transaction data.
5. The final response is converted to audio.

## Project Structure

- `src/main/java/dio/budgeting/domain`
  - Domain model and repository contract.
- `src/main/java/dio/budgeting/application`
  - Use cases used by both REST and AI tool calling.
- `src/main/java/dio/budgeting/infrastructure`
  - HTTP adapters, JPA adapters, and integration glue.

## Module-Specific Topics

### Speech-to-text

- Uses `TranscriptionModel` for audio transcription.
- Model settings are configured in `application.properties`.

### Tool calling

- `ChatClient` registers use-case tools.
- `@Tool` methods expose business capabilities to the model.

### Text-to-speech

- `TextToSpeechModel` produces MP3 output from final text.
- AI endpoint returns generated audio.

## How to Run

### Prerequisites
- Java 17
- Ollama installed and running

### Install Ollama
```bash
# Install Ollama
curl -fsSL https://ollama.com/install.sh | sh

# Download the model
ollama pull llama3.2

# Start Ollama service (keep it running)
ollama serve
```

### Run the application
```bash
# Compile and run
./gradlew bootRun

# Run tests
./gradlew test
```

### Available Categories

| Category | Description |
|----------|-------------|
| `GROCERIES` | Food and supermarket expenses |
| `PHARMA` | Pharmacy and health expenses |
| `AUTO` | Vehicle and transportation expenses |
| `ENTERTAINMENT` | Entertainment expenses (added as improvement) |

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/transactions` | List all transactions |
| GET | `/api/transactions/category/{category}` | Filter by category |
| POST | `/api/transactions` | Create a new transaction |
| POST | `/api/transactions/load-sample` | Load sample data |

### Example Requests
```bash
# Load sample data
curl -X POST http://localhost:8081/api/transactions/load-sample

# List all transactions
curl http://localhost:8081/api/transactions

# Filter by category
curl http://localhost:8081/api/transactions/category/GROCERIES

# Create a new transaction
curl -X POST http://localhost:8081/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"description":"Supermarket","amount":15050,"category":"GROCERIES"}'
```

## Improvements Made

This project was evolved from the original with the following improvements:

1. **Replaced OpenAI with Ollama** - Using a free, local AI model (llama3.2) instead of paid OpenAI API
2. **Added ENTERTAINMENT category** - Extended the Category enum with a new option
3. **Created REST API endpoints** - Full CRUD operations with GET, POST endpoints
4. **Added DTO for transaction creation** - Improved separation between layers
5. **Configured alternative port (8081)** - Avoids port conflicts with other applications
6. **H2 Console enabled** - Easy database inspection at `/h2-console`

## Technologies Used
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring AI 0.8.1
- Ollama (llama3.2)
- H2 Database
- Lombok
- Gradle

## What I Learned
- Integrating Spring AI with Ollama for local LLM
- Building REST APIs with Spring Boot
- DDD/Clean Architecture with layered design
- Using Tool Calling with AI models
- Persistence with Spring Data JPA and H2
- Working with DTOs and Value Objects

## Spring AI Documentation

- Spring AI Reference: https://docs.spring.io/spring-ai/reference/index.html
- ChatModel API: https://docs.spring.io/spring-ai/reference/api/chatmodel.html
- ChatClient API: https://docs.spring.io/spring-ai/reference/api/chatclient.html
- Tools API: https://docs.spring.io/spring-ai/reference/api/tools.html
- Audio Transcriptions API: https://docs.spring.io/spring-ai/reference/api/audio/transcriptions.html
- Audio Speech API: https://docs.spring.io/spring-ai/reference/api/audio/speech.html

## Shared Architecture References

Common architecture concepts are documented in the root README:

- [DDD layers](../README.md#ddd-layered-architecture)
- [Class vs record](../README.md#java-class-vs-java-record-in-domain-modeling)
- [Strong typed identifiers](../README.md#strong-typed-identifiers)
- [Repository pattern](../README.md#repository-pattern)
- [Use cases and Clean Architecture](../README.md#use-cases-and-clean-architecture)
- [Docker Compose support](../README.md#docker-compose-support-in-development)

## Notes

- Educational final project focused on AI plus architectural discipline.
- External provider integration tests may require active credentials.
- This version uses **Ollama** instead of OpenAI for local, free AI processing.