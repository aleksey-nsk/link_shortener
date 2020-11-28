package ru.fineservices.smlr.service

interface KeyMapperService {

    // Интерфейс-маркер
    interface Get {
        data class Link(val link: String) : Get
        data class NotFound(val key: String) : Get
    }

    fun add(link: String): String

    fun getLink(key: String): Get
}
