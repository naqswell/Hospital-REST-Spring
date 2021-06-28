package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.PeopleEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository


interface PeopleDAO : CrudRepository<PeopleEntity, Int> {

    fun findByOrderByFirstName(): List<PeopleEntity>

}