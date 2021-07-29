package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.users.RoleEntity
import org.springframework.data.repository.CrudRepository

interface RoleDAO : CrudRepository<RoleEntity, Int> {

    fun findByName(name: String): RoleEntity?
}