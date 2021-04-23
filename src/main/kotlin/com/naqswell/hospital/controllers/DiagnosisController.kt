package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.DiagnosisEntity
import com.naqswell.hospital.services.DiagnosisService
import com.naqswell.hospital.services.SaveDiagnosisRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/diagnosis", produces = [MediaType.APPLICATION_JSON_VALUE])
class DiagnosisController(private val diagnosisService: DiagnosisService) {

    @GetMapping
    fun findAll() = diagnosisService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): DiagnosisEntity {
        return diagnosisService.findById(id)
    }

    @PostMapping
    fun create(@Valid @RequestBody request: SaveDiagnosisRequest): StatusResponse {
        diagnosisService.createRequest(request)
        return StatusResponse("Created")
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody request: SaveDiagnosisRequest
    ): StatusResponse {
        diagnosisService.update(id, request)
        return StatusResponse("Updated")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        diagnosisService.delete(id)
        return StatusResponse(("Deleted"))
    }

}