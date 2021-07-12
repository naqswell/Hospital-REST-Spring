package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.users.UsersEntity
import com.naqswell.hospital.services.users.SaveUsersRequestFkByEntities
import com.naqswell.hospital.services.users.SaveUsersRequestFkByID
import com.naqswell.hospital.services.users.UsersService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users", produces = [MediaType.APPLICATION_JSON_VALUE])
class UsersController(private val usersService: UsersService) {

    @GetMapping("")
    fun findAll() = usersService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): UsersEntity {
        return usersService.findById(id)
    }

    @PostMapping("/create/byId")
    fun create(@Valid @RequestBody requestFkByID: SaveUsersRequestFkByID): StatusResponse {
        usersService.createRequest(requestFkByID)
        return StatusResponse("User created requestFkByID")
    }


    @PostMapping("/create/byEntity")
    fun create(@Valid @RequestBody requestFkByEntities: SaveUsersRequestFkByEntities): StatusResponse {
        usersService.createRequest(requestFkByEntities)
        return StatusResponse("User created requestFkByEntities")
    }

    @PutMapping("update/byId/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody requestFkByID: SaveUsersRequestFkByID
    ): StatusResponse {
        usersService.update(id, requestFkByID)
        return StatusResponse("User with id=$id updated by id")
    }

    @PutMapping("update/byEntities/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody requestFkByEntities: SaveUsersRequestFkByEntities
    ): StatusResponse {
        usersService.update(id, requestFkByEntities)
        return StatusResponse("User with id=$id updated by entities")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        usersService.delete(id)
        return StatusResponse(("User with id=$id deleted"))
    }

}