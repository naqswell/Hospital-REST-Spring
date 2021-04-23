package com.naqswell.hospital.services

import com.naqswell.hospital.models.PeopleDiagnosisEntity
import com.naqswell.hospital.models.PeopleEntity
import com.naqswell.hospital.models.PeopleWardEntity
import com.naqswell.hospital.repositories.PeopleDAO
import com.naqswell.hospital.repositories.PeopleDiagnosisDAO
import com.naqswell.hospital.repositories.PeopleWardsDAO
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service //Позволяем IoC контейнеру внедрять класс
class PeopleServiceImpl(
        private val peopleDAO: PeopleDAO,
        private val peopleDiagnosisDAO: PeopleDiagnosisDAO,
        private val peopleWardsDAO: PeopleWardsDAO) : PeopleService {

    override fun findAll(): List<PeopleEntity> {
        log.info("Find all peoples")
        return peopleDAO.findByOrderByFirstName()
    }

    override fun findById(id: Int): PeopleEntity {
        log.info("Find person with id=$id")
        return peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)
    }

    override fun createRequest(request: SavePeopleRequest) {
        log.info("Create new person with name=${request.firstName}")

        val peopleDiagnosisEntity: PeopleDiagnosisEntity = peopleDiagnosisDAO.findByIdOrNull(request.fkDiagnosis!!) ?: throw PeopleDiagnosisNotFoundException(request.fkDiagnosis)
        val peopleWardEntity: PeopleWardEntity = peopleWardsDAO.findByIdOrNull(request.fkWard!!) ?: throw WardNotFoundException(request.fkWard)

        peopleDAO.save(
                PeopleEntity(
                        firstName = request.firstName!!,
                        lastName = request.lastName!!,
                        patherName = request.patherName!!,
                        fkDiagnosis = peopleDiagnosisEntity,
                        fkWard = peopleWardEntity
                )
        )
    }

    override fun update(id: Int, request: SavePeopleRequest) {
        log.info("Update person with id=$id")

        val peopleDiagnosisEntity: PeopleDiagnosisEntity = peopleDiagnosisDAO.findByIdOrNull(request.fkDiagnosis!!) ?: throw PeopleDiagnosisNotFoundException(request.fkDiagnosis)
        val peopleWardEntity: PeopleWardEntity = peopleWardsDAO.findByIdOrNull(request.fkWard!!) ?: throw PeopleWardNotFoundException(request.fkWard)
        val people = peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)

        people.firstName = request.firstName!!
        people.lastName = request.lastName!!
        people.patherName = request.patherName!!
        people.fkDiagnosis = peopleDiagnosisEntity
        people.fkWard = peopleWardEntity

        peopleDAO.save(people)
    }

    override fun delete(id: Int) {
        log.info("Delete person with id=$id")
        val people = peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)
        peopleDAO.delete(people)
    }

    override fun getPeoplesCountInWard(ward_name: String): Int = peopleDAO.getPeoplesCountInWard(ward_name)

    companion object {
        private val log = LoggerFactory.logger(PeopleServiceImpl::class.java)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class PeopleNotFoundException(id: Int) : RuntimeException("People with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class PeopleDiagnosisNotFoundException(id: Int) : RuntimeException("PeopleDiagnosis with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class PeopleWardNotFoundException(id: Int) : RuntimeException("PeopleWard with id=$id not found")