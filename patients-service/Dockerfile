FROM openjdk:17
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
ADD target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]