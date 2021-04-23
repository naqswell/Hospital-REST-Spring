package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.DiagnosisEntity
import org.springframework.data.repository.CrudRepository

interface DiagnosisDAO : CrudRepository<DiagnosisEntity, Int> {

    fun findByOrderById(): List<DiagnosisEntity>
}