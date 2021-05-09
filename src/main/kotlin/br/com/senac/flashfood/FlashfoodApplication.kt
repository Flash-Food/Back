package br.com.senac.flashfood

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class FlashfoodApplication

	fun main(args: Array<String>) {
		runApplication<FlashfoodApplication>(*args)
	}



