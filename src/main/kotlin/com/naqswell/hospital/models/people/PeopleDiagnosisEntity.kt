package com.naqswell.hospital.models.people

import com.naqswell.hospital.models.diagnosis.BaseDiagnosisEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name ="diagnosis")
class PeopleDiagnosisEntity : BaseDiagnosisEntity()