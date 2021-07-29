package com.naqswell.hospital.services.users

import com.naqswell.hospital.models.users.RoleEntity
import com.naqswell.hospital.models.users.HospitalUsersEntity
import org.jetbrains.annotations.NotNull

interface HospitalUsersService {
    fun findAll(): List<HospitalUsersEntity>

    fun findById(id: Int): HospitalUsersEntity

    fun createRequest(request: SaveUsersRequestFkByID): Boolean

    fun createRequest(request: SaveUsersRequestFkByEntities): Boolean

    fun update(id: Int, request: SaveUsersRequestFkByID)

    fun update(id: Int, request: SaveUsersRequestFkByEntities)

    fun delete(id: Int)
}


data class SaveUsersRequestFkByID(

        @get:NotNull
        val login: String?,

        @get:NotNull
        val hash: String?,

        @get:NotNull
        var roles: MutableList<Int>?
)

data class SaveUsersRequestFkByEntities(
        @get:NotNull
        val login: String?,

        @get:NotNull
        val hash: String?,

        @get:NotNull
        var roles: MutableList<RoleEntity>?
)