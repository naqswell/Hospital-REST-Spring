package com.naqswell.hospital.services.users

import com.naqswell.hospital.models.users.HospitalUsersEntity
import com.naqswell.hospital.models.users.RoleEntity
import com.naqswell.hospital.repositories.HospitalUsersDAO
import com.naqswell.hospital.repositories.RoleDAO
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus


@Service
class HospitalUserServiceImplHospital(
        private val hospitalUsersDAO: HospitalUsersDAO,
        private val roleDAO: RoleDAO,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : HospitalUsersService, UserDetailsService {
//        val roleDAO: RoleDAO) : UsersService  {
    override fun findAll(): List<HospitalUsersEntity> {
        log.info("Find all peoples")
        return hospitalUsersDAO.findByOrderByLogin()
    }

    override fun findById(id: Int): HospitalUsersEntity {
        log.info("Find user with id=$id")
        return hospitalUsersDAO.findByIdOrNull(id) ?: throw UserNotFoundByIdException(id)
    }

    override fun createRequest(request: SaveUsersRequestFkByEntities): Boolean {
        log.info("Create new user with login=${request.login}")

        val user = hospitalUsersDAO.findByLogin(request.login!!)

        if (user != null) {
            return false
        }

        if (!request.roles.isNullOrEmpty()) {
            request.roles!!.forEach {
                val role = roleDAO.findByName(it.name)

                if (role == null) {
                    request.roles!!.remove(it)
                }
            }
        }

        hospitalUsersDAO.save(
                HospitalUsersEntity(
                        login = request.login,
                        hash = bCryptPasswordEncoder.encode(request.hash!!),
//                        hash = request.hash!!,
                        roles = request.roles!!
                )
        )
        return true
    }

    override fun createRequest(request: SaveUsersRequestFkByID): Boolean {
        log.info("Create new user with login=${request.login}")

        val user = hospitalUsersDAO.findByLogin(request.login!!)

        if (user != null) {
            return false
        }

        val roles: MutableList<RoleEntity> = mutableListOf()

        if (!request.roles.isNullOrEmpty()) {
            request.roles!!.forEach {
                val role = roleDAO.findByIdOrNull(it)

                if (role != null) {
                    roles.add(role)
                }
            }
        }

        hospitalUsersDAO.save(HospitalUsersEntity(
                login = request.login,
                hash = request.hash!!,
                roles = roles
        ))
        return true
    }


    override fun update(id: Int, request: SaveUsersRequestFkByID) {
        log.info("Update user with id=$id by id")

        val user = hospitalUsersDAO.findByIdOrNull(id) ?: throw UserNotFoundByIdException(id)

        val roles: MutableList<RoleEntity> = mutableListOf()

        if (!request.roles.isNullOrEmpty()) {
            request.roles!!.forEach {
                val role = roleDAO.findByIdOrNull(it)

                if (role != null) {
                    roles.add(role)
                }
            }
        }

        user.login = request.login!!
        user.hash = request.hash!!
        user.roles = roles

        hospitalUsersDAO.save(user)
    }

    override fun update(id: Int, request: SaveUsersRequestFkByEntities) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        log.info("Delete user with id=$id")
        val user = hospitalUsersDAO.findByIdOrNull(id) ?: throw UserNotFoundByIdException(id)
        hospitalUsersDAO.delete(user)
    }

    companion object {
        private val log = LoggerFactory.logger(HospitalUserServiceImplHospital::class.java)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return hospitalUsersDAO.findByLogin(username) ?: throw UserNotFoundByLoginException(username)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundByIdException(id: Int) : RuntimeException("User with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundByLoginException(login: String) : RuntimeException("User with id=$login not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class RoleNotFoundException(name: String) : RuntimeException("Role with id=$name not found")
