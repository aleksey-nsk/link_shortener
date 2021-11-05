[![Build Status](https://travis-ci.org/aleksey-nsk/link_shortener.svg?branch=master)](https://travis-ci.org/aleksey-nsk/link_shortener)
[![codecov](https://codecov.io/gh/aleksey-nsk/link_shortener/branch/master/graph/badge.svg?token=GU31JRCDMH)](https://codecov.io/gh/aleksey-nsk/link_shortener)

## SMLR project (link SMaLleR):
- Сервис, который укорачивает длинные ссылки
- Написан на **Kotlin + Spring**

## Реализованный функционал:
1. Использую **TDD**.

2. Установил **Docker**, добавил контейнер с БД PostgreSQL.

3. Создал простой **Web UI**:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/01_verstka.png)

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/02_verstka.png)

4. Использую **Travis CI** для непрерывной интеграции. Конфиг:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/03_config_for_travis_ci.png)

**Сборка на Travis CI**:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/04_build_on_travis_ci.png)

**Настроил отправку отчётов на email**:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/05_report_to_email.png)

5. **Для проверки покрытия проекта тестами использую сервис Codecov**:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/06_test_coverage_on_codecov.png)

6. **Собранный в итоге образ контейнера размещаю в публичном репозитории Docker Hub**:

![](https://github.com/aleksey-nsk/link_shortener/blob/master/screenshots/07_container_image_on_docker_hub.png)
