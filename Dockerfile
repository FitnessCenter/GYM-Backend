FROM java:8

LABEL maintainer="pycon@kakao.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/fitness-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} fitness.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/fitness.jar"]
