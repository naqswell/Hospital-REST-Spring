package com.naqswell.hospital.models.users

import com.naqswell.hospital.models.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity(
        @Column(name = "name")
        var name: String,
) : BaseEntity<Int>() {

    fun copy(): RoleEntity =
            RoleEntity(
                    name
            )
}
