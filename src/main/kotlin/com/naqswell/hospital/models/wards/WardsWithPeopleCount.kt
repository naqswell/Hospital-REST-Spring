package com.naqswell.hospital.models.wards

import com.naqswell.hospital.models.PeopleEntity
import javax.persistence.Entity

@Entity
class WardsWithPeopleCount(
        name: String?,
        maxCount: Int?,
        peoples: MutableList<PeopleEntity> = mutableListOf()
) : WardEntity(name, maxCount, peoples) {
}