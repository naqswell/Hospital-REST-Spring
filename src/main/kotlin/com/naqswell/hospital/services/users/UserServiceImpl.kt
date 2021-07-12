package com.naqswell.hospital.services.users

import com.naqswell.hospital.models.users.RoleEntity
import com.naqswell.hospital.models.users.UsersEntity
import com.naqswell.hospital.repositories.RoleDAO
import com.naqswell.hospital.repositories.UsersDAO
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class UserServiceImpl(
        val usersDAO: UsersDAO,
        val roleDAO: RoleDAO) : UsersService {

    override fun findAll(): List<UsersEntity> {
        log.info("Find all peoples")
        return usersDAO.findByOrderByLogin()
    }

    override fun findById(id: Int): UsersEntity {
        log.info("Find user with id=$id")
        return usersDAO.findByIdOrNull(id) ?: throw UserNotFoundException(id)
    }

    override fun createRequest(request: SaveUsersRequestFkByID) {
        log.info("Create new user with login=${request.login}")

        var role: RoleEntity? = null

        if (request.fkRole != null) {
            role = roleDAO.findByIdOrNull(request.fkRole!!)
                    ?: throw RoleNotFoundException(request.fkRole)
        }

        usersDAO.save(
                UsersEntity(
                        login = request.login!!,
                        hash = request.hash!!,
                        fkRole = role
                )
        )
    }

    override fun createRequest(request: SaveUsersRequestFkByEntities) {
        log.info("Create new user with login=${request.login}")
        usersDAO.save(UsersEntity(
                login = request.login!!,
                hash = request.hash!!,
                fkRole = request.fkRole
        ))
    }


    override fun update(id: Int, request: SaveUsersRequestFkByID) {
        log.info("Update user with id=$id by id")

        val role = roleDAO.findByIdOrNull(request.fkRole!!) ?: throw RoleNotFoundException(request.fkRole)
        val user = usersDAO.findByIdOrNull(id) ?: throw UserNotFoundException(id)

        user.login = request.login!!
        user.hash = request.hash!!
        user.fkRole = role

        usersDAO.save(user)
    }

    override fun update(id: Int, request: SaveUsersRequestFkByEntities) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        log.info("Delete user with id=$id")
        val user = usersDAO.findByIdOrNull(id) ?: throw UserNotFoundException(id)
        usersDAO.delete(user)
    }

    companion object {
        private val log = LoggerFactory.logger(UserServiceImpl::class.java)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(id: Int) : RuntimeException("User with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class RoleNotFoundException(id: Int) : RuntimeException("Role with id=$id not found")
