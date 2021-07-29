package com.naqswell.hospital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HospitalApplication

fun main(args: Array<String>) {
    runApplication<HospitalApplication>(*args)
}