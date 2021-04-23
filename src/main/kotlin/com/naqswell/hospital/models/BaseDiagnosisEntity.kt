package com.naqswell.hospital.models

import javax.persistence.*

@MappedSuperclass
@Table(name = "diagnosis")
abstract class BaseDiagnosisEntity(
        @Column(name = "name")
        var name: String? = null,
) : BaseEntity<Int>()