package ru.fineservices.smlr.service

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.fineservices.smlr.service.impl.DefaultKeyConverterService
import java.util.*
import kotlin.math.abs

class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun givenIdMustBeConvertableBothWays() {
        println("Given Id Must Be Convertable Both Ways")

        val rand = Random()

        for (i in 0..1_000L) {
            val initialId = abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            assertEquals(initialId, id)
        }
    }
}
