package ru.fineservices.smlr.service

interface KeyMapperService {

    // Интерфейс-маркер (хранит в себе дата-классы, которые может вернуть метод)
    interface Add {
        data class Success(val key: String, val link: String) : Add
        data class AlreadyExist(val key: String) : Add
    }

    // Интерфейс-маркер
    interface Get {
        data class Link(val link: String) : Get
        data class NotFound(val key: String) : Get
    }

    fun add(key: String, link: String): Add

    fun getLink(key: String): Get
}
