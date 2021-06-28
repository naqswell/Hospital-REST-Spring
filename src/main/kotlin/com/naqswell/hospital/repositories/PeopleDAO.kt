package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.people.PeopleEntity
import org.springframework.data.repository.CrudRepository


interface PeopleDAO : CrudRepository<PeopleEntity, Int> {

    fun findByOrderByFirstName(): List<PeopleEntity>

}