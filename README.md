## Library App Update

Pour generer l'executable de l'application effectuez un mvn package. 
Modifiez le fichier application.properties

```
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url = jdbc:postgresql://localhost:5432/P10_database
spring.datasource.username = postgres
spring.datasource.password = 123456
logging.file.name = logs

account=annalibraryoc@gmail.com
password=12345LibraryOC
```
L'application utilise le serveur stmp gmail, vous pouvez modifier le compte d'accès. 

L'application utlise PostGreSQL comme serveur de base de données, les scripts sont présent dans le fichier nommée "SQL".

## Test

pour tester l'application, allez dans la racine de la couche back ou batch et effectuez un mvn test
 
## Batch
l'application contient un module batch qui va executer tous les jours pour effectuer les vérifications des réservations, vous pouvez changer l'heure de son execution en changeant le @Schedule(cron = ) par defaut le batch se lance tous les jours a 5 heures
