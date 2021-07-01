package com.naqswell.hospital.repositories

import com.naqswell.hospital.models.wards.WardEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository

interface WardsDAO : CrudRepository<WardEntity, Int> {
    fun findByOrderById(): List<WardEntity>

    @Query("SELECT wards.id as id, wards.name as name, wards.max_count as max_count FROM wards ORDER BY max_count DESC, name", nativeQuery = true)
    fun getWardsSortAllByDescAndMaxCountByAsc(): List<WardEntity>

    @Query("SELECT count(*) FROM people WHERE fk_ward = (SELECT wards.id FROM wards WHERE name = ?1)", nativeQuery = true)
    fun getPeoplesCountInWard(wardName: String?): Int

    @Procedure("move_from_ward_to_ward")
    fun moveFromWardToWard(id1: Int, id2: Int)

    @Query("SELECT wards.id as id, wards.name as name, wards.max_count as max_count\n" +
            "        from wards\n" +
            "                 join people on people.fk_ward = wards.id\n" +
            "        group by wards.id\n" +
            "        having count(wards.id) < (SELECT avg(people_count)\n" +
            "                                  FROM (\n" +
            "                                           SELECT wards.id, count(wards.id) as people_count\n" +
            "                                           from wards\n" +
            "                                                    join people on people.fk_ward = wards.id\n" +
            "                                           where ((people.fk_diagnosis = ?1) or (people.fk_diagnosis = ?2))\n" +
            "                                           group by wards.id\n" +
            "                                       ) as s1)", nativeQuery = true)
    fun getWardsWhenTakenPlacesFewerThenAvgD1D2(id1: Int, id2: Int): List<WardEntity>
}