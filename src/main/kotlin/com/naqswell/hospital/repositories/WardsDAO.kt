package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.WardEntity
import org.springframework.data.repository.CrudRepository

interface WardsDAO : CrudRepository<WardEntity, Int> {
    fun findByOrderById(): List<WardEntity>
}