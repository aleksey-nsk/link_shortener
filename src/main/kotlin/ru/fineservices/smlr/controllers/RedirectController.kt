package ru.fineservices.smlr.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.fineservices.smlr.service.KeyMapperService
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{key}")
class RedirectController {

    @Autowired
    lateinit var service: KeyMapperService

    companion object {
        private val HEADER_NAME = "Location"
    }

    @RequestMapping()
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        println("method redirect()")
        println("  key: '$key'")

        val result = service.getLink(key)

        when (result) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link)
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }
    }
}
