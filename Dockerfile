FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} reading_is_good.jar
ENTRYPOINT ["java", "-jar", "reading_is_good.jar"]