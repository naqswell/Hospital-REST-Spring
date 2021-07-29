package com.naqswell.hospital.models.users

import com.naqswell.hospital.models.BaseEntity
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "roles_t")
class RoleEntity(
        @Column(name = "name")
        var name: String,

//        @ManyToMany(mappedBy = "roles")
//        var users: MutableList<HospitalUsersEntity> = mutableListOf()
) : BaseEntity<Int>(), GrantedAuthority {
//) : BaseEntity<Int>() {

    fun copy(): RoleEntity =
            RoleEntity(
                    name
            )

    override fun getAuthority(): String {
        return name
    }

}
