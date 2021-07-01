package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.people.PeopleEntity
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository


interface PeopleDAO : CrudRepository<PeopleEntity, Int> {

    fun findByOrderByFirstName(): List<PeopleEntity>

    @Procedure("delete_people_with_unique_diagnosis")
    fun deletePeopleWithUniqueDiagnosis()
}