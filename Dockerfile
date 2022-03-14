FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/${project.artifactId}.${project.version} ${project.artifactId}.${project.version}
COPY . .
ENTRYPOINT ["java","-jar","/${project.artifactId}.${project.version}.jar"]

