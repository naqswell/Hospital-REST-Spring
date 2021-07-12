package com.naqswell.hospital.models.users


import com.naqswell.hospital.models.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class UsersEntity(

        @Column(name = "login")
        var login: String,

        @Column(name = "hash")
        var hash: String,

        @ManyToOne
        @JoinColumn(name = "fk_role")
        var fkRole: RoleEntity?,

        ) : BaseEntity<Int>() {

    fun copy(): UsersEntity =
            UsersEntity(
                    login, hash, fkRole
            )
}