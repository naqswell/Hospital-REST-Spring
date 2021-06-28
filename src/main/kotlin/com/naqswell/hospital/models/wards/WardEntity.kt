package com.naqswell.hospital.models.wards

import com.naqswell.hospital.models.PeopleEntity
import javax.persistence.*

@Entity
@Table(name = "wards")
open class WardEntity(

        name: String?,
        maxCount: Int?,

        @OneToMany(
//                должно указывать на поле класса того объекта, которого будет хранит
                mappedBy = "fkWard",
//                FetchType.LAZY связанные объекты загружаются только по мере
//                необходимости, т.е. при обращении. Но при этом требуется,
//                чтобы соединение с базой (или транзакция) сохранялись
                fetch = FetchType.LAZY,
//                FetchType.EAGER в памяти будут находиться все
//                загруженные объекты, даже если нужен только один объект из десятка
                orphanRemoval = false
        )
        var peoples: MutableList<PeopleEntity> = mutableListOf()
) : BaseWardEntity(name, maxCount) {

    fun addPeoples(block: WardEntity.() -> PeopleEntity) {
        peoples.add(block())
    }

    fun setPeoples(block: WardEntity.() -> MutableList<PeopleEntity>) {
        peoples.clear()
        peoples.addAll(block())
    }

}