package ru.fineservices.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.fineservices.smlr.service.impl.DefaultKeyMapperService

class DefaultKeyMapperServiceTest {

    private val KEY = "aAbBcCdD"
    private val LINK_A = "https://www.google.ru/"
    private val LINK_B = "https://www.yahoo.com/"
    private val KEY_A = "abc"
    private val KEY_B = "cde"
    private val ID_A = 10_000_000L
    private val ID_B = 10_000_001L

    @InjectMocks
    val service: KeyMapperService = DefaultKeyMapperService()

    @Mock
    lateinit var converter: KeyConverterService

    @Before
    fun init() {
        println("Init")

        MockitoAnnotations.initMocks(this)

        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)

        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }

    @Test
    fun clientCanAddLinks() {
        println("Client Can Add Links")

        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))

        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))

        assertNotEquals(keyA, keyB)
    }

    @Test
    fun clientCanNotTakeLinkIfKeyIsNotFoundInService() {
        println("Client Can Not Take Link If Key Is Not Found In Service")
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}
