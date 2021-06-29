package com.naqswell.hospital.services.diagnosis

import com.naqswell.hospital.models.diagnosis.DiagnosisEntity
import com.naqswell.hospital.models.people.PeopleEntity
import org.jetbrains.annotations.NotNull

interface DiagnosisService {
    fun findAll(): List<DiagnosisEntity>

    fun findById(id: Int): DiagnosisEntity

    fun createRequest(request: SaveDiagnosisRequest)

    fun update(id: Int, request: SaveDiagnosisRequest)

    fun delete(id: Int)
}

//Эта сущность представляет собой тело json-запроса - data class с тремя полями и валидацией
// (см. Валидация бинов в Spring). Поскольку мы не пишем здесь get и set-методы в явном виде,
// каждую аннотацию снабжаем префиксом @get, указывающим, что аннотация относится именно к get-методу, а не к полю.

//Также обратите внимание, что мы используем nullable типы данных с аннотацией @NotNull.
// Это сделано для корректной работы валидации в случае отсутствия параметра.
// Ведь если такое поле не придёт в запросе, то в случае с not-null типом мы не сможем десериализовать запрос.
data class SaveDiagnosisRequest(
        @get:NotNull
        val name: String?,

        @get:NotNull
        val peoples: MutableList<PeopleEntity>?
)