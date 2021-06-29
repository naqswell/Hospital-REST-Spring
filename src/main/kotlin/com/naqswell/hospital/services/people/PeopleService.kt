package com.naqswell.hospital.services.people

import com.naqswell.hospital.models.people.PeopleDiagnosisEntity
import com.naqswell.hospital.models.people.PeopleEntity
import com.naqswell.hospital.models.people.PeopleWardEntity
import org.jetbrains.annotations.NotNull


interface PeopleService {
    fun findAll(): List<PeopleEntity>

    fun findById(id: Int): PeopleEntity

    fun createRequest(requestFkByID: SavePeopleRequestFkByID)

    fun createRequest(requestFkByEntities: SavePeopleRequestFkByEntities)

    fun update(id: Int, requestFkByID: SavePeopleRequestFkByID)

    fun update(id: Int, requestFkByEntities: SavePeopleRequestFkByEntities)

    fun delete(id: Int)
}

data class SavePeopleRequestFkByID(

        @get:NotNull
        val firstName: String?,

        @get:NotNull
        val lastName: String?,

        @get:NotNull
        val patherName: String?,

        @get:NotNull
        val fkDiagnosis: Int?,

        @get:NotNull
        val fkWard: Int?,
)

data class SavePeopleRequestFkByEntities(
        @get:NotNull
        val firstName: String?,

        @get:NotNull
        val lastName: String?,

        @get:NotNull
        val patherName: String?,

        @get:NotNull
        val fkDiagnosis: PeopleDiagnosisEntity?,

        @get:NotNull
        val fkWard: PeopleWardEntity?,
)
