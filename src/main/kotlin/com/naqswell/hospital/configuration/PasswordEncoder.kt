package com.naqswell.hospital.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class Config {
    @Bean
    fun encoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
