package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.people.PeopleEntity
import com.naqswell.hospital.services.people.PeopleService
import com.naqswell.hospital.services.people.SavePeopleRequestFkByEntities
import com.naqswell.hospital.services.people.SavePeopleRequestFkByID
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/people", produces = [MediaType.APPLICATION_JSON_VALUE])
class PeopleController(private val peopleService: PeopleService) {

    @GetMapping("/getAll")
    fun findAll() = peopleService.findAll()

    @GetMapping("/getById/{id}")
    fun findById(@PathVariable("id") id: Int): PeopleEntity {
        return peopleService.findById(id)
    }

    @PostMapping("/create/byId")
    fun create(@Valid @RequestBody requestFkByID: SavePeopleRequestFkByID): StatusResponse {
        peopleService.createRequest(requestFkByID)
        return StatusResponse("People created requestFkByID")
    }


    @PostMapping("/create/byEntities")
    fun create(@Valid @RequestBody requestFkByEntities: SavePeopleRequestFkByEntities): StatusResponse {
        peopleService.createRequest(requestFkByEntities)
        return StatusResponse("People created requestFkByEntities")
    }

    @PutMapping("/update/byId/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody requestFkByID: SavePeopleRequestFkByID
    ): StatusResponse {
        peopleService.update(id, requestFkByID)
        return StatusResponse("People with id=$id updated by id")
    }

    @PutMapping("/update/byEntities/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody requestFkByEntities: SavePeopleRequestFkByEntities
    ): StatusResponse {
        peopleService.update(id, requestFkByEntities)
        return StatusResponse("People with id=$id updated by entities")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        peopleService.delete(id)
        return StatusResponse(("People with id=$id deleted"))
    }

    @DeleteMapping("/deletePeopleWithUniqueDiagnosis")
    fun deletePeopleWithUniqueDiagnosis(): StatusResponse {
        peopleService.deletePeopleWithUniqueDiagnosis()
        return StatusResponse(("People with unique diagnosis deleted"))
    }

}