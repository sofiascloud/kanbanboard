FROM openjdk
WORKDIR usr/lib
EXPOSE 7878
ADD ./target/MessagingService-0.0.1-SNAPSHOT.jar /usr/lib/MessagingService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java,"-jar","MessagingService-0.0.1-SNAPSHOT.jar"]