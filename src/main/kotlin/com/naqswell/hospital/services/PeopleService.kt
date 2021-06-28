package com.naqswell.hospital.services

import com.naqswell.hospital.models.PeopleEntity
import org.jetbrains.annotations.NotNull


interface PeopleService {
    fun findAll(): List<PeopleEntity>

    fun findById(id: Int): PeopleEntity

    fun createRequest(request: SavePeopleRequest)

    fun update(id: Int, request: SavePeopleRequest)

    fun delete(id: Int)

    fun getPeoplesCountInWard(ward_name: String): Int
}

data class SavePeopleRequest(

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