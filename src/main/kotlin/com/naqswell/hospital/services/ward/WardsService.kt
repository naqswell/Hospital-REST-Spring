package com.naqswell.hospital.services.ward

import com.naqswell.hospital.models.wards.WardEntity
import org.jetbrains.annotations.NotNull

interface WardsService {
    fun findAll(): List<WardEntity>

    fun findById(id: Int): WardEntity

    fun createRequest(request: SaveWardsRequest)

    fun getWardsSortAllByDescAndMaxCountByAsc(): List<WardEntity>

    fun getPeoplesCountInWard(ward_name: String): Int

    fun update(id: Int, request: SaveWardsRequest)

    fun delete(id: Int)

    fun moveFromWardToWard(id1: Int, id2: Int)

    fun getWardsWhenTakenPlacesFewerThenAvgD1D2(id1: Int, id2: Int): List<WardEntity>

    fun getWardsMetrics(id: Int): List<Int?>
}

//Эта сущность представляет собой тело json-запроса - data class с тремя полями и валидацией
// (см. Валидация бинов в Spring). Поскольку мы не пишем здесь get и set-методы в явном виде,
// каждую аннотацию снабжаем префиксом @get, указывающим, что аннотация относится именно к get-методу, а не к полю.

//Также обратите внимание, что мы используем nullable типы данных с аннотацией @NotNull.
// Это сделано для корректной работы валидации в случае отсутствия параметра.
// Ведь если такое поле не придёт в запросе, то в случае с not-null типом мы не сможем десериализовать запрос.
data class SaveWardsRequest(
        @get:NotNull
        val name: String?,

        @get:NotNull
        val maxCount: Int?,

//        @get:NotNull
//        val peoples: MutableList<PeopleEntity>?
)