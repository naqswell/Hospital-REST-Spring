package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.wards.WardEntity
import com.naqswell.hospital.services.ward.SaveWardsRequest
import com.naqswell.hospital.services.ward.WardsService
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
            wardsService.getWardsSortAllByDescAndMaxCountByAsc()

    @GetMapping("/getPeopleCountInWard/{ward_name}")
    fun findPeoplesCountInWard(@PathVariable("ward_name") ward_name: String): Int =
            wardsService.getPeoplesCountInWard(ward_name)


    @PostMapping("/create")
    fun create(@Valid @RequestBody request: SaveWardsRequest): StatusResponse {
        wardsService.createRequest(request)
        return StatusResponse("Ward created")
    }

    @PutMapping("/update/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody request: SaveWardsRequest
    ): StatusResponse {
        wardsService.update(id, request)
        return StatusResponse("Ward with id=$id updated")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        wardsService.delete(id)
        return StatusResponse(("Ward with id=$id deleted"))
    }

}