FROM openjdk:11

# Copia el script Bash al contenedor
COPY out/artifacts/probandojar_jar/probandojar.jar /home

#EXPOSE
EXPOSE 8080

# Comando de inicio para ejecutar la aplicación Java
CMD ["java", "-jar", "/home/probandojar.jar"]
