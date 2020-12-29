package ru.fineservices.smlr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmlrApplication

// Отвечает за старт приложения
fun main(args: Array<String>) {
	runApplication<SmlrApplication>(*args)
}
