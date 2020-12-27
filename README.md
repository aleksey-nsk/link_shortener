## SMLR project (link SMaLleR):
1. Сервис, который укорачивает длинные ссылки
2. Написан на **Kotlin + Spring**

## Что было использовано:
- Spring Boot
- Thymeleaf

## Реализованный функционал:
1. **Proof of concept.**
Начальный минимум: пусть вэб-сервис переадресовывает запрос на какую-то другую страницу.
Начинаю с написания теста (использую TDD).

2. Создал сервис, который осуществляет маппинг ключа в ссылку.

3. Объявил интерфейс конвертера, написал тесты к конвертеру, реализовал конвертер.

4. Использую DBUnit и H2.
Создал репозиторий LinkRepository, и тесты для репозитория.
Класс AbstractRepositoryTest служит для поднятия контекста.
Используется нерабочая БД.

5. Установил Docker, добавил контейнер с PostgreSQL.
В итоге использую PostgreSQL и Liquibase.

6. Создал контроллер AddController для добавления ссылки в систему.
Подправил сервис.
