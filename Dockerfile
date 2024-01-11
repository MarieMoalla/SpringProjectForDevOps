FROM openjdk:17
ADD target/GestionBibliotheque-0.0.1-SNAPSHOT.jar springboot-mysql-docker.jar 
ENTRYPOINT [ "java","-jar","/springboot-mysql-docker.jar" ]

# Add SQL script
COPY init_script.sql /docker-entrypoint-initdb.d/


