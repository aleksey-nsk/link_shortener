package ru.fineservices.smlr.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.fineservices.smlr.service.KeyConverterService
import ru.fineservices.smlr.service.KeyMapperService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

// В данном классе реализуем хранение
// отображений ключа на ссылку

@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    private val map: MutableMap<Long, String> = ConcurrentHashMap()
    private val sequence = AtomicLong(10_000_000L)

    override fun add(link: String): String {
        val id = sequence.getAndIncrement()
        val key = converter.idToKey(id)
        map[id] = link
        return key
    }

    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        val result = map[id]

        return if (result == null) {
            KeyMapperService.Get.NotFound(key)
        } else {
            KeyMapperService.Get.Link(result)
        }
    }
}
