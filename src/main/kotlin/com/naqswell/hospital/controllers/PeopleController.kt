package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.PeopleEntity
import com.naqswell.hospital.services.PeopleService
import com.naqswell.hospital.services.SavePeopleRequestFkByEntities
import com.naqswell.hospital.services.SavePeopleRequestFkByID
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

    @GetMapping("/getPeopleCountInWard/{ward_name}")
    fun findPeoplesCountInWard(@PathVariable("ward_name") ward_name: String): Int =
            peopleService.getPeoplesCountInWard(ward_name)

//    @PostMapping
//    fun create(@Valid @RequestBody requestFkByID: SavePeopleRequestFkByID): StatusResponse {
//        peopleService.createRequest(requestFkByID)
//        return StatusResponse("Created requestFkByID")
//    }

    @PostMapping("/byId")
    fun create(@Valid @RequestBody requestFkByID: SavePeopleRequestFkByID): StatusResponse {
        peopleService.createRequest(requestFkByID)
        return StatusResponse("Created requestFkByID")
    }


    @PostMapping("/byEntities")
    fun create(@Valid @RequestBody requestFkByEntities: SavePeopleRequestFkByEntities): StatusResponse {
        peopleService.createRequest(requestFkByEntities)
        return StatusResponse("Created requestFkByEntities")
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody requestFkByID: SavePeopleRequestFkByID
    ): StatusResponse {
        peopleService.update(id, requestFkByID)
        return StatusResponse("Updated")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        peopleService.delete(id)
        return StatusResponse(("Deleted"))
    }

}