package com.naqswell.hospital.services.users

import com.naqswell.hospital.models.users.RoleEntity
import com.naqswell.hospital.models.users.UsersEntity
import org.jetbrains.annotations.NotNull

interface UsersService {
    fun findAll(): List<UsersEntity>

    fun findById(id: Int): UsersEntity

    fun createRequest(request: SaveUsersRequestFkByID)

    fun createRequest(request: SaveUsersRequestFkByEntities)

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
        val fkRole: Int?,
)

data class SaveUsersRequestFkByEntities(
        @get:NotNull
        val login: String?,

        @get:NotNull
        val hash: String?,

        @get:NotNull
        val fkRole: RoleEntity?,

)