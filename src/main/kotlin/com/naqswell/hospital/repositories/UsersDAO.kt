package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.users.UsersEntity
import org.springframework.data.repository.CrudRepository

interface UsersDAO : CrudRepository<UsersEntity, Int> {

    fun findByOrderByLogin(): List<UsersEntity>

}