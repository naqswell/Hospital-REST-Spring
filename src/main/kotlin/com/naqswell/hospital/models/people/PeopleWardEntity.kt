package com.naqswell.hospital.models.people

import com.naqswell.hospital.models.wards.BaseWardEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name ="wards")
class PeopleWardEntity : BaseWardEntity()
