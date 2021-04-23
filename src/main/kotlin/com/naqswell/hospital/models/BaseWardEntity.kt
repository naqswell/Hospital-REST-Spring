package com.naqswell.hospital.models

import javax.persistence.*

@MappedSuperclass
@Table(name = "wards")
abstract class BaseWardEntity(
        @Column(name = "name")
        var name: String? = null,

        @Column(name = "max_count")
        var maxCount: Int? = null
) : BaseEntity<Int>()