## Eiti Solutions 

## Arquivos do Projeto

```text
├── docker-compose.yml
├── Dockerfile
├── eiti-crud.iml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── com
    │   │           └── befullstack
    │   │               └── eiticrud
    │   │                   ├── controllers
    │   │                   │   └── UserController.java
    │   │                   ├── DockerRunner.java
    │   │                   ├── EitiCrudApplication.java
    │   │                   ├── errors
    │   │                   │   ├── BadRequestException.java
    │   │                   │   └── UserNotFoundException.java
    │   │                   ├── models
    │   │                   │   ├── UserDTO.java
    │   │                   │   └── User.java
    │   │                   ├── repositories
    │   │                   │   └── UserRepository.java
    │   │                   └── services
    │   │                       └── UserService.java
    │   └── resources
    │       ├── application-dev.properties
    │       ├── application-prod.properties
    │       ├── application.properties
    │       ├── i18n
    │       │   └── messages.properties
    │       ├── static
    │       │   └── images
    │       │       └── eiti-logo.png
    │       └── templates
    │           ├── 404.html
    │           ├── fragments
    │           │   ├── footer.html
    │           │   ├── header.html
    │           │   └── head.html
    │           ├── index.html
    │           └── users
    │               ├── user-form.html
    │               └── user-list.html
    └── test
        └── java
            └── br
                └── com
                    └── befullstack
                        └── eiticrud
                            ├── controllers
                            │   └── UserControllerTest.java
                            ├── EitiCrudApplicationTests.java
                            └── services
                                └── UsersServiceTest.java

27 directories, 31 files
```

### Pré-requisitos

* [Java 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org)
* [Docker CE](https://www.docker.com/community-edition)
* [Docker Compose](https://docs.docker.com/compose/)



### Clone do respositório

```text
git clone https://github.com/deyvedvm/eiti-crud.git
```


### Ambiente local de Dev

```text
cd eiti-crud/

docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db_eiti -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql:5.6

mvn spring-boot:run
```

Endereço local:  `http://localhost:8088/`



### Ambiente de Produção

```text
cd eiti-crud/

mvn package

docker-compose build

docker-compose up
```
Endereço em produção:  `http://localhost:8081/`


# Autor

Deyve Vieira 

[Twitter](https://twitter.com/deyvedvm)

[Github](https://github.com/deyvedvm)


