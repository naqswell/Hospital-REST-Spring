package com.naqswell.hospital.configuration

import com.naqswell.hospital.services.users.HospitalUserServiceImplHospital
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val usersDetailService: HospitalUserServiceImplHospital,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder

) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/diagnosis/getAll").hasRole("USER")
                .antMatchers("/diagnosis/getById/**").hasRole("USER")

                .antMatchers("/wards/getAll").hasRole("USER")
                .antMatchers("/wards/getById/**").hasRole("USER")

                .antMatchers("/people/getAll").hasRole("USER")
                .antMatchers("/people/getById/**").hasRole("USER")

                .antMatchers("/diagnosis/**").hasRole("ADMIN")
                .antMatchers("/wards/**").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/people/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
    }


    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(usersDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
    }

}