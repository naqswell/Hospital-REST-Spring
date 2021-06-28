package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.people.PeopleDiagnosisEntity
import org.springframework.data.repository.CrudRepository

interface PeopleDiagnosisDAO : CrudRepository<PeopleDiagnosisEntity, Int> {

    fun findByOrderById(): List<PeopleDiagnosisEntity>
}