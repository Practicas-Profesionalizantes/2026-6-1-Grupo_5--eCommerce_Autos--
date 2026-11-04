# Imagen del backend para desplegarlo en la nube (Render, Railway, etc.).
# Compila con Maven y corre el .war ejecutable de Spring Boot con Java 17.

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/carfind-0.0.1-SNAPSHOT.war app.war
# Ajustes de memoria para planes chicos (Render gratis: 512 MB).
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70 -XX:+UseSerialGC -Xss512k"
EXPOSE 8080
CMD ["java", "-jar", "app.war"]
