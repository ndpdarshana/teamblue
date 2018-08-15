# Teamblue

### Description

A cross-language plagiarism detection tool 

### System Requirements

- java version 8+
- MySQL 5.6+
- Gradle 4.9+  

### Tools

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    - You can obtain free student license for IntelliJ using uow email 
    - Let's use same IDE in order to maintain style consistence on code.
- [phpMyadmin](https://www.phpmyadmin.net/) or [MySQL workbench](https://www.mysql.com/products/workbench/)
- [Postman](https://www.getpostman.com/apps)
- [Meld](http://meldmerge.org/)

### Technologies

- Java 
    - Spring Framework
        - Spring Hibernate
        - Spring Boot
        - Spring REST service
 - MySQL 

### How to setup
1. Get the project cloned

`git clone git@github.com:uowteamblue/teamblue.git`

2. Setup database

```SQL 
CREATE DATABASE teamblue;
CREATE USER 'teamblue'@'localhost' IDENTIFIED BY 'test123;
GRANT ALL PRIVILEGES ON teabblue.* TO 'teamblue'@'localhost';
FLUSH PRIVILEGES;
```

3. Build project

`./gradlew build`

4. Run

`./gradlew bootRun`

5. Server up and running

`GET http://localhost:8080/health`

### Contributing to the repository

Let's use git branches and pull request approach to maintain healthy repository. 

[This tutorial set include quick heads up on git](http://gitimmersion.com/index.html)

#### Basic git commands

- Clone repository

`git clone url_to_repository`
- Check current status

`git status`
-  Create a branch: 

`git checkout branch_name`

- Commit 
```
git add .
git commit -m "Comment"
 ```
    
- Push changes to remote repository

`git push`

- To commit push newly created bracnh with changes

`git push -u origin branch_name`

- Update existing repository

`git pull`

To create pull requests, review and merge, use github web.   
 

### References
- https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
- https://spring.io/guides
- http://www.asjava.com/core-java/java-md5-example/
