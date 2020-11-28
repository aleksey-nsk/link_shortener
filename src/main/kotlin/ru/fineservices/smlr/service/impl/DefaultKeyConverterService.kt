package ru.fineservices.smlr.service.impl

import org.springframework.stereotype.Component
import ru.fineservices.smlr.service.KeyConverterService

@Component
class DefaultKeyConverterService : KeyConverterService {

    private val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()

    private val charToLong = (chars.indices)
            .map { i -> Pair(chars[i], i.toLong()) }
            .toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()

        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }

        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key
            .map { c -> charToLong[c]!! }
            .fold(0L, { a, b -> a * chars.size + b })
}
