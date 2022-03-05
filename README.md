[![Build Status](https://travis-ci.org/aleksey-nsk/link_shortener.svg?branch=master)](https://travis-ci.org/aleksey-nsk/link_shortener)
[![codecov](https://codecov.io/gh/aleksey-nsk/link_shortener/branch/master/graph/badge.svg?token=GU31JRCDMH)](https://codecov.io/gh/aleksey-nsk/link_shortener)

# SMLR project (link SMaLleR)
- Сервис, который укорачивает длинные ссылки
- Написан на **Kotlin + Spring**

# Реализованный функционал
1. Используем **TDD**.

2. Установили **Docker**. Добавили контейнер с БД PostgreSQL через консоль командой:  
`docker run --name pgsql-test -e POSTGRES_PASSWORD=password -d -p 6000:5432 postgres`  
В итоге видим скачанный образ, и запущенный контейнер:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/08_image_and_container.png)  

3. Создали простой **Web UI**:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/01_verstka.png)  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/02_verstka.png)

4. Используем **Travis CI** для непрерывной интеграции.   

5. Создали в корне проекта файл **Dockerfile**: 
  
       FROM java:8-jre
       MAINTAINER Aleksey Zhdanov <aleksey.zhd@gmail.com>
       ADD ./target/smaller.jar /app/
       CMD ["java", "-jar", "/app/smaller.jar"]
       EXPOSE 8080
  
где  
`ADD ./target/smaller.jar /app/` - копировать jar-файл в наш контейнер в папку app (краткое имя `smaller` задали
в pom-файле, в разделе `<build>`, с помощью тега `<finalName>`);  
`CMD ["java", "-jar", "/app/smaller.jar"]` - команда для запуска приложения внутри контейнера.  

Далее надо билдить образ и загружать его на **Docker Hub**. Для этого используем **Travis CI**. Команды
прописываем в конфигурационном файле **.travis.yml**. Итоговый файл имеет вид:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/03_config_for_travis_ci.png)  

6. Сборка на **Travis CI**:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/04_build_on_travis_ci.png)

7. Настроили отправку отчётов на email:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/05_report_to_email.png)

8. Для проверки покрытия проекта тестами используем **сервис Codecov**:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/06_test_coverage_on_codecov.png)

9. Собранный в итоге образ контейнера размещён в публичном репозитории **Docker Hub**:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/07_container_image_on_docker_hub.png)  
Ссылка на образ: https://hub.docker.com/repository/docker/alexz2/smaller

# Как запустить приложение на рабочем сервере
1. Есть виртуальная машина, на которой установлены только **Docker** и **docker-compose**.

2. Создать папку и перейти в неё:  
`mkdir smlr/`  
`cd smlr/`

3. В папке создать файл **docker-compose.yml** с таким содержимым:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/09_docker_compose.png)  

где `db` и `smlr` - 2 сервиса, присутствующие в приложении.  

Образ, из которого будет создаваться БД:    
`image: postgres:latest`  

Задан пароль:  
`environment:`  
`- POSTGRES_PASSWORD=12345`  

Чтобы рестартовалось если вдруг упадёт:  
`restart: always`  

Образ, из которого будет создаваться наш сервис:  
`image: alexz2/smaller`  

Необходимо прописать линки (в данном случае только до базы данных, т.е. до сервиса `db`). Это позволит
smlr связываться с контейнером с БД:  
`links:`  
`- db`  

Прокинуть порты:  
`ports:`  
`- "80:80"`  
т.е. прокинули порт из 80 в 80. Это значит, что мы будем открывать наше приложение внутри контейнера на 80 порту,
и его же будем прокидывать во вне. И соответственно у нас на сервере будет открыто приложение на 80 порту.  

Наше приложение имеет файл **application.properties**:

    spring.liquibase.change-log=classpath:db-changelog.xml
    
    # database connection
    spring.jpa.database=POSTGRESQL
    spring.datasource.platform=postgres
    spring.jpa.show-sql=false
    spring.jpa.hibernate.ddl-auto=validate
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.url=jdbc:postgresql://localhost:6000/postgres
    spring.datasource.username=postgres
    spring.datasource.password=password
    
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
    
    smlr.prefix=http://localhost:8080/

откуда надо указать несколько настроек. Писать названия переменных большими буквами, и вместо точки 
символ подчёркивания.
    
4. Далее запустить docker-compose командой:  
`sudo docker-compose up`  

В итоге образы будут скачаны и контейнеры запущены:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/10_images_containers.png)  

5. Открыть браузер и ввести адрес:  
**localhost:80**  

Отображается приложение:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/11_test_app.png)  

Проверить работу:  
![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/12_test_app.png)  

6. Данный файл **docker-compose.yml** можно запускать на разных серверах. Его можно хранить
в приватном репозитории. В данном случае положили этот файл в папку **docker/docker-compose.yml**

