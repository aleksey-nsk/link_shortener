package ru.fineservices.smlr.service.impl

import org.springframework.stereotype.Component
import ru.fineservices.smlr.service.KeyMapperService
import java.util.concurrent.ConcurrentHashMap

// В данном классе реализуем хранение
// отображений ключа на ссылку

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map: MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        return if (map.contains(key)) {
            KeyMapperService.Add.AlreadyExist(key)
        } else {
            map[key] = link
            KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String) = if (map.contains(key)) {
        KeyMapperService.Get.Link(map[key]!!)
    } else {
        KeyMapperService.Get.NotFound(key)
    }
}
