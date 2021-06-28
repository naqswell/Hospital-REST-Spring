package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.wards.WardEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface WardsDAO : CrudRepository<WardEntity, Int> {
    fun findByOrderById(): List<WardEntity>

    @Query("SELECT wards.id as id, wards.name as name, wards.max_count as max_count\n FROM wards ORDER BY max_count DESC, name", nativeQuery = true)
    fun selectWardsSortAllByDescAndMaxCountByAsc(): List<WardEntity>

    @Query("SELECT wards.name, count(people.id) FROM wards RIGHT JOIN people ON wards.id = people.fk_ward GROUP BY wards.name;", nativeQuery = true)
    fun getAllWardsAndPeopleCount(): List<Objects>

    @Query("SELECT count(*) FROM people WHERE fk_ward = (SELECT id FROM wards WHERE name = ?1)", nativeQuery = true)
    fun getPeoplesCountInWard(ward_name: String?): Int

}