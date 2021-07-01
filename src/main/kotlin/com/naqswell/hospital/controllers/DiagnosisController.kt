package com.naqswell.hospital.controllers

import com.naqswell.hospital.models.diagnosis.DiagnosisEntity
import com.naqswell.hospital.services.diagnosis.DiagnosisService
import com.naqswell.hospital.services.diagnosis.SaveDiagnosisRequest
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

    @PostMapping("/create")
    fun create(@Valid @RequestBody request: SaveDiagnosisRequest): StatusResponse {
        diagnosisService.createRequest(request)
        return StatusResponse("Diagnosis created")
    }

    @PutMapping("/update/{id}")
    fun update(
            @PathVariable("id") id: Int,
            @Valid @RequestBody request: SaveDiagnosisRequest
    ): StatusResponse {
        diagnosisService.update(id, request)
        return StatusResponse("Diagnosis with id=$id updated")
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Int
    ): StatusResponse {
        diagnosisService.delete(id)
        return StatusResponse(("Diagnosis with id=$id deleted"))
    }

    @DeleteMapping("/deleteTopDiagnosis")
    fun deleteTopDiagnosis(): StatusResponse {
        diagnosisService.deleteTopDiagnosis()
        return StatusResponse(("Top diagnosis deleted"))
    }
}