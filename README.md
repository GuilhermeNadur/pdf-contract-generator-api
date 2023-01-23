# PDF Contract Generator API

## 📝 Descrição
Aplicação Java com Spring Boot responsável por gerar um contrato no formato PDF.

## 🤖 Tecnologias
- Java [17.0.4.1]
- Maven [3.8.6]
- Spring Boot [3.0.2] (MVC, JUnit5, Actuator & Validation)
- Lombok [1.18.24]
- iText PDF [5.5.13.3]
- Gson [2.10.1]

## ✅ Requisitos
- [JDK 17.0.4.1](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.6](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [IntelliJ IDEA (recomendado)](https://www.jetbrains.com/idea/download)
- [Postman (recomendado)](https://www.postman.com/downloads/)
- [Git (recomendado)](https://git-scm.com/downloads)

## 🖥️ Como Rodar
Execute o seguinte comando na raiz do projeto para executar os testes unitários:
```bash
mvn test
```
Execute o seguinte comando na raiz do projeto para executar o Spring Boot:
```bash
mvn spring-boot:run
```

## 🧪 Testes
A cobertura atual é de 81%. Os testes ajudam a garantir a qualidade do código e a descobrir falhas no funcionamento da aplicação.

## 👩‍🚀 Postman
A biblioteca do Postman foi exportada para raiz do projeto. O caminho do arquivo é "postman/PDF Contract Generator API.postman_collection.json".

## 📊 Spring Actuator
O Actuator foi configurado para rodar na porta 8080. Acesse os componentes através dos seguintes caminhos:
```
http://localhost:8080/actuator
```
```
http://localhost:8080/actuator/health
```