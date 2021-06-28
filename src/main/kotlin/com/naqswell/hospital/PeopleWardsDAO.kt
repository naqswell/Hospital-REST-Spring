package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.PeopleWardEntity
import org.springframework.data.repository.CrudRepository

interface PeopleWardsDAO : CrudRepository<PeopleWardEntity, Int> {
    fun findByOrderById(): List<PeopleWardEntity>
}