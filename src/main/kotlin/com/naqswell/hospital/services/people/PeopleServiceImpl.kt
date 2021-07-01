package com.naqswell.hospital.services.people

import com.naqswell.hospital.models.people.PeopleDiagnosisEntity
import com.naqswell.hospital.models.people.PeopleEntity
import com.naqswell.hospital.models.people.PeopleWardEntity
import com.naqswell.hospital.repositories.PeopleDAO
import com.naqswell.hospital.repositories.PeopleDiagnosisDAO
import com.naqswell.hospital.repositories.PeopleWardsDAO
import com.naqswell.hospital.services.ward.WardNotFoundException
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

    override fun createRequest(requestFkByID: SavePeopleRequestFkByID) {
        log.info("Create new person with name=${requestFkByID.firstName}")

        var peopleDiagnosisEntity: PeopleDiagnosisEntity? = null
        var peopleWardEntity: PeopleWardEntity? = null

        if ((requestFkByID.fkDiagnosis != null) and (requestFkByID.fkWard != null)) {
            peopleDiagnosisEntity = peopleDiagnosisDAO.findByIdOrNull(requestFkByID.fkDiagnosis!!)
                    ?: throw PeopleDiagnosisNotFoundException(requestFkByID.fkDiagnosis)
            peopleWardEntity = peopleWardsDAO.findByIdOrNull(requestFkByID.fkWard!!)
                    ?: throw WardNotFoundException(requestFkByID.fkWard)
        }

        peopleDAO.save(
                PeopleEntity(
                        firstName = requestFkByID.firstName!!,
                        lastName = requestFkByID.lastName!!,
                        patherName = requestFkByID.patherName!!,
                        fkDiagnosis = peopleDiagnosisEntity,
                        fkWard = peopleWardEntity
                )
        )
    }

    override fun createRequest(requestFkByEntities: SavePeopleRequestFkByEntities) {
        log.info("Create new person with name=${requestFkByEntities.firstName}")
        peopleDAO.save(PeopleEntity(
                firstName = requestFkByEntities.firstName!!,
                lastName = requestFkByEntities.lastName!!,
                patherName = requestFkByEntities.patherName!!,
                fkDiagnosis = requestFkByEntities.fkDiagnosis!!,
                fkWard = requestFkByEntities.fkWard!!
        ))

    }

    override fun update(id: Int, requestFkByID: SavePeopleRequestFkByID) {
        log.info("Update person with id=$id by id")

        val peopleDiagnosisEntity: PeopleDiagnosisEntity = peopleDiagnosisDAO.findByIdOrNull(requestFkByID.fkDiagnosis!!)
                ?: throw PeopleDiagnosisNotFoundException(requestFkByID.fkDiagnosis)
        val peopleWardEntity: PeopleWardEntity = peopleWardsDAO.findByIdOrNull(requestFkByID.fkWard!!)
                ?: throw PeopleWardNotFoundException(requestFkByID.fkWard)
        val people = peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)

        people.firstName = requestFkByID.firstName!!
        people.lastName = requestFkByID.lastName!!
        people.patherName = requestFkByID.patherName!!
        people.fkDiagnosis = peopleDiagnosisEntity
        people.fkWard = peopleWardEntity

        peopleDAO.save(people)
    }

    override fun update(id: Int, requestFkByEntities: SavePeopleRequestFkByEntities) {
        log.info("Update person with id=$id by entities")

        val people = peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)

        people.firstName = requestFkByEntities.firstName!!
        people.lastName = requestFkByEntities.lastName!!
        people.patherName = requestFkByEntities.patherName!!
        people.fkDiagnosis = requestFkByEntities.fkDiagnosis
        people.fkWard = requestFkByEntities.fkWard

        peopleDAO.save(people)
    }

    override fun delete(id: Int) {
        log.info("Delete person with id=$id")
        val people = peopleDAO.findByIdOrNull(id) ?: throw PeopleNotFoundException(id)
        peopleDAO.delete(people)
    }

    override fun deletePeopleWithUniqueDiagnosis() {
        log.info("Delete person with unique diagnosis")
        peopleDAO.deletePeopleWithUniqueDiagnosis()
    }

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