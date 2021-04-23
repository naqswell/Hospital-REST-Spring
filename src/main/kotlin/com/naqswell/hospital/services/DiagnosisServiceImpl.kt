package com.naqswell.hospital.services

import com.naqswell.hospital.models.DiagnosisEntity
import com.naqswell.hospital.repositories.DiagnosisDAO
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service //Позволяем IoC контейнеру внедрять класс
class DiagnosisServiceImpl(
        private val diagnosisDAO: DiagnosisDAO) : DiagnosisService {

    override fun findAll(): List<DiagnosisEntity> {
        log.info("Find all diagnosis")
        return diagnosisDAO.findByOrderById()
    }

    override fun findById(id: Int): DiagnosisEntity {
        log.info("Find diagnosis with id=$id")
        return diagnosisDAO.findByIdOrNull(id) ?: throw DiagnosisNotFoundException(id)
    }


    override fun createRequest(request: SaveDiagnosisRequest) {
        log.info("Create new diagnosis with name=${request.name}")
        diagnosisDAO.save(
                DiagnosisEntity(
                        name = request.name!!,
                        peoples = request.peoples!!
                )
        )
    }

    override fun update(id: Int, request: SaveDiagnosisRequest) {
        log.info("Update diagnosis with id=$id")
        val diagnosis = diagnosisDAO.findByIdOrNull(id) ?: throw DiagnosisNotFoundException(id)
        diagnosis.name = request.name!!
        diagnosis.peoples = request.peoples!!
        diagnosisDAO.save(diagnosis)
    }

    override fun delete(id: Int) {
        log.info("Delete diagnosis with id=$id")
        val diagnosis = diagnosisDAO.findByIdOrNull(id) ?: throw DiagnosisNotFoundException(id)
        diagnosisDAO.delete(diagnosis)
    }

    companion object {
        private val log = LoggerFactory.logger(DiagnosisServiceImpl::class.java)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class DiagnosisNotFoundException(id: Int) : RuntimeException("Diagnosis with id=$id not found")