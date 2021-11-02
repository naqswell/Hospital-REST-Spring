package com.naqswell.hospital

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@SpringBootApplication
class HospitalApplication

fun main(args: Array<String>) {
    runApplication<HospitalApplication>(*args)
}