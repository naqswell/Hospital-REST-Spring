package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.users.HospitalUsersEntity
import org.springframework.data.repository.CrudRepository

interface HospitalUsersDAO : CrudRepository<HospitalUsersEntity, Int> {

    fun findByOrderByLogin(): List<HospitalUsersEntity>

    fun findByLogin(login: String): HospitalUsersEntity?

}