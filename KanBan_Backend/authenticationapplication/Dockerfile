FROM openjdk
WORKDIR src/usr
EXPOSE 8888
ADD ./target/kanban_user_authentication-0.0.1-SNAPSHOT.jar /src/usr/kanban_user_authentication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kanban_user_authentication-0.0.1-SNAPSHOT.jar"]