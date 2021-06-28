package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.PeopleEntity
import com.naqswell.hospital.services.PeopleService
import com.naqswell.hospital.services.SavePeopleRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/people", produces = [MediaType.APPLICATION_JSON_VALUE])
class PeopleController(private val peopleService: PeopleService) {

    @GetMapping("")
    fun findAll() = peopleService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): PeopleEntity {
        return peopleService.findById(id)
    }



    @PostMapping
    fun create(@Valid @RequestBody request: SavePeopleRequest): StatusResponse {
        peopleService.createRequest(request)
        return StatusResponse("Created")
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody request: SavePeopleRequest
    ): StatusResponse {
        peopleService.update(id, request)
        return StatusResponse("Updated")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        peopleService.delete(id)
        return StatusResponse(("Deleted"))
    }

}