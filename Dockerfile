# Usar una imagen base de Java
FROM openjdk:17-jdk-slim
# Establecer el directorio de trabajo en el contenedor
WORKDIR /app
# Copiar el archivo JAR y renombrarlo a "app.jar"
COPY target/my-food-waste*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]