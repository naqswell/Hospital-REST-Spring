package com.naqswell.hospital.models.diagnosis

import com.naqswell.hospital.models.people.PeopleEntity
import org.hibernate.annotations.JoinFormula
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "diagnosis")
class DiagnosisEntity(

        name: String?,

        @OneToMany(
//                должно указывать на поле класса того объекта, которого будет хранит
                mappedBy = "fkDiagnosis",
//                FetchType.LAZY связанные объекты загружаются только по мере
//                необходимости, т.е. при обращении. Но при этом требуется,
//                чтобы соединение с базой (или транзакция) сохранялись
                fetch = FetchType.LAZY,
//                FetchType.EAGER в памяти будут находиться все
//                загруженные объекты, даже если нужен только один объект из десятка
                orphanRemoval = true
        )
        var peoples: MutableList<PeopleEntity> = mutableListOf(),

) : BaseDiagnosisEntity(name) {

//    fun addPeoples(block: DiagnosisEntity.() -> PeopleEntity) {
//        peoples.add(block())
//    }
//
//    fun setPeoples(block: DiagnosisEntity.() -> MutableSet<PeopleEntity>) {
//        peoples.clear()
//        peoples.addAll(block())
//    }

    fun copy(): DiagnosisEntity =
            DiagnosisEntity(
                    name, peoples
            )
}