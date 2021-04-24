package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.PeopleWardEntity
import com.naqswell.hospital.models.WardEntity
import com.naqswell.hospital.services.SaveWardsRequest
import com.naqswell.hospital.services.WardsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/wards", produces = [MediaType.APPLICATION_JSON_VALUE])
class WardsController(private val wardsService: WardsService) {

    @GetMapping
    fun findAll() = wardsService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): WardEntity {
        return wardsService.findById(id)
    }

    @GetMapping("/selectWardsSortAllByDescAndMaxCountByAsc")
    fun findWardsSortedAllByDescAndMaxCountByAsc(): List<WardEntity> =
            wardsService.selectWardsSortAllByDescAndMaxCountByAsc()

    @PostMapping
    fun create(@Valid @RequestBody request: SaveWardsRequest): StatusResponse {
        wardsService.createRequest(request)
        return StatusResponse("Created")
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody request: SaveWardsRequest
    ): StatusResponse {
        wardsService.update(id, request)
        return StatusResponse("Updated")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        wardsService.delete(id)
        return StatusResponse(("Deleted"))
    }

}