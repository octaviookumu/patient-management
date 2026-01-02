FROM maven:3.9.9-eclipse-temurin-21 AS builder

# keep all code in a single location we know about
WORKDIR /app

COPY pom.xml .

# cache dependencies unless a change has been detected
RUN mvn dependency:go-offline -B

# copy source code
COPY src ./src

# create a new package from our springboot app
# will create a single .jar file that has all our stuff in it
RUN mvn clean package

# instructions on how to run and build code
# the step that runs the code
FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

# copy the snapshot.jar that was created in the builder step and place it as app.jar
COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

# specify the port that the app runs on
EXPOSE 4000

# specify a command for the run step to use to start our code
ENTRYPOINT ["java", "-jar", "app.jar"]
