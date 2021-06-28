package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.wards.WardEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface WardsDAO : CrudRepository<WardEntity, Int> {
    fun findByOrderById(): List<WardEntity>

    @Query("SELECT wards.id as id, wards.name as name, wards.max_count as max_count FROM wards ORDER BY max_count DESC, name", nativeQuery = true)
    fun getWardsSortAllByDescAndMaxCountByAsc(): List<WardEntity>

    @Query("SELECT count(*) FROM people WHERE fk_ward = (SELECT wards.id FROM wards WHERE name = ?1)", nativeQuery = true)
    fun getPeoplesCountInWard(wardName: String?): Int
}