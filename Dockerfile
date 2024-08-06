
FROM openjdk:17-jdk

WORKDIR /app

COPY target/spring-todo-0.0.1-SNAPSHOT.jar /app/todo.jar


EXPOSE 8080


CMD ["java", "-jar", "todo.jar"]