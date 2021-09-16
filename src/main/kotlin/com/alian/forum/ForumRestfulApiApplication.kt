package com.alian.forum

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class ForumRestfulApiApplication

fun main(args: Array<String>) {
	runApplication<ForumRestfulApiApplication>(*args)
}
