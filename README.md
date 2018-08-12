# teamblue

###System Requirements

- java version 8+
- MySQL 5.6+
- Gradle 4.9+  

### How to setup
1. Get the project cloned

`git clone git@github.com:uowteamblue/teamblue.git`

2. Setup database

`CREATE DATABASE teamblue;`

`CREATE USER 'teamblue'@'localhost' IDENTIFIED BY 'test123;`

`GRANT ALL PRIVILEGES ON teabblue.* TO 'teamblue'@'localhost';`

`FLUSH PRIVILEGES;`

3. Build project

`./gradlew build`

4. Run

`./gradlew bootRun`

5. Server up and running

`GET http://localhost:8080/health`

###References
- https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
- https://spring.io/guides
