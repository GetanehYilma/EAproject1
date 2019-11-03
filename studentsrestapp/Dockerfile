FROM openjdk:latest
VOLUME /tmp
EXPOSE 9999
ADD target/studentsrestapp.jar studentsrestapp.jar
#ADD Student.csv Students.csv
ENTRYPOINT ["java", "-jar", "/studentsrestapp.jar"]