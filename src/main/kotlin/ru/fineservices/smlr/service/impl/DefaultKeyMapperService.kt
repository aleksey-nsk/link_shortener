package ru.fineservices.smlr.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.fineservices.smlr.model.Link
import ru.fineservices.smlr.model.repositories.LinkRepository
import ru.fineservices.smlr.service.KeyConverterService
import ru.fineservices.smlr.service.KeyMapperService

// В данном классе реализуем хранение
// отображений ключа на ссылку

@Component
class DefaultKeyMapperService : KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    @Autowired
    lateinit var repo: LinkRepository

    @Transactional
    override fun add(link: String) = converter.idToKey(repo.save(Link(link)).id)

    override fun getLink(key: String): KeyMapperService.Get {
        val result = repo.findById(converter.keyToId(key))

        return if (result.isPresent) {
            KeyMapperService.Get.Link(result.get().text)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}
