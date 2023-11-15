# Establecemos la imagen base de Java
FROM openjdk:17-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

ENV variable_env un_valor_desde_dockerfile

# Copiamos el archivo JAR de la aplicación Spring Boot al contenedor
COPY target/prueba-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto en el que la aplicación Spring Boot escuchará las solicitudes
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
