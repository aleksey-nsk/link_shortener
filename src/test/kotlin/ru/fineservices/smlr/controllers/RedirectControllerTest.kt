package ru.fineservices.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.fineservices.smlr.SmlrApplication

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(SmlrApplication::class))
@WebAppConfiguration
class RedirectControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    private val PATH = "/aAbBcCdD"
    private val REDIRECT_STATUS: Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "https://www.eveonline.com/"

    private val BAD_PATH = "/trololotrololo"
    private val NOT_FOUND: Int = 404

    // Метод, который будет запускаться перед тестами,
    // и инициализировать mockMvc
    @Before
    fun init() {
        println("method init()")
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        println("method controllerMustRedirectUsWhenRequestIsSuccessful()")
        mockMvc.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    @Test
    fun controllerMustReturn404IfBadKey() {
        println("method controllerMustReturn404IfBadKey()")
        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }
}
