package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.wards.WardEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface WardsDAO : CrudRepository<WardEntity, Int> {
    fun findByOrderById(): List<WardEntity>

    @Query("SELECT wards.id as id, wards.name as name, wards.max_count as max_count\n" +
            "        FROM wards\n" +
            "        ORDER BY max_count DESC, name", nativeQuery = true)
    fun selectWardsSortAllByDescAndMaxCountByAsc(): List<WardEntity>
}