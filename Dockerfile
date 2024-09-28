
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/PedidoAPI.jar app.jar

# Expondo a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
