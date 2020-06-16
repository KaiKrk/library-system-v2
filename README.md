## Library App Update

Pour generer l'executable de l'application effectuez un mvn package 
modifiez le fichier application.properties

spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url = jdbc:postgresql://localhost:5432/P10_database
spring.datasource.username = postgres
spring.datasource.password = 123456
logging.file.name = logs

account=annalibraryoc@gmail.com
password=12345LibraryOC

l'application utilise le serveur stmp gmail, vous pouvez modifier le compte d'accès 

l'application utlise PostGreSQL comme serveur de base de données, les scripts sont présent dans le fichier nommée "SQL"

