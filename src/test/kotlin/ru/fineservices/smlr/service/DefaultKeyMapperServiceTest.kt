package ru.fineservices.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.fineservices.smlr.service.impl.DefaultKeyMapperService

class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aAbBcCdD"
    private val LINK: String = "https://www.eveonline.com/"
    private val LINK_NEW: String = "https://wow.ru"

    @Test
    fun clientCanAddNewKeyWithLink() {
        println("Client Can Add New Key With Link")
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotAddExistingKey() {
        println("Client Can Not Add Existing Key")
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundInService() {
        println("Client Can Not Take Link If Key Is Not Found In Service")
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}
