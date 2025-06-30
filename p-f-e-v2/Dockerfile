# Utilise une image JDK officielle
FROM openjdk:17-jdk-slim

# Dossier de travail
WORKDIR /app

# Copie le jar généré
COPY target/p-f-e-v2-0.0.1-SNAPSHOT.jar app.jar

# Port exposé
EXPOSE 8081

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "app.jar"]

