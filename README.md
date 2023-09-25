# akamai-backend

## Assumptions

The following assumptions made in the development process:
1. Created FileModel class to represent each file in the tree.json file provdied
2. Assummed one endpoint files with optional parameter "prefix" will be enough
3. Assummed the read and processing of the tree.json is done on application startup (post construct)
4. Created a cache in form of a Map with a key of the file path and a value of FileModel to get faster results in case of search by prefix
5. Return results from type FileResponse (extends FileModel) to include also the path of file

## Requirements

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

For building and running the application you need:

- [JDK 20](https://www.oracle.com/java/technologies/downloads/#java20)
- [Maven 3](https://maven.apache.org)

## Running the application locally

You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


