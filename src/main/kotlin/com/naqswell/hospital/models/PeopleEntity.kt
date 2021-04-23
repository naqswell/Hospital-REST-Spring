package com.naqswell.hospital.models

import javax.persistence.*

@Entity
@Table(name = "people")
class PeopleEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override val id: Int = 0,

        @Column(name = "first_name")
        var firstName: String,

        @Column(name = "last_name")
        var lastName: String,

        @Column(name = "pather_name")
        var patherName: String,

        @ManyToOne
        @JoinColumn(name = "fk_diagnosis")
        var fkDiagnosis: PeopleDiagnosisEntity,

        @ManyToOne
        @JoinColumn(name = "fk_ward")
        var fkWard: PeopleWardEntity,

        ) : BaseEntity<Int>() {

    fun copy(): PeopleEntity =
            PeopleEntity(
                    id, firstName, lastName, patherName, fkDiagnosis, fkWard
            )
}