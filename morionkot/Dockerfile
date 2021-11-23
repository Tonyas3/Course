FROM openjdk:11-jdk-slim
ADD build/libs/morionkot-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
EXPOSE 8081
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]