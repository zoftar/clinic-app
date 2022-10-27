package com.example.clinic.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Patient(
        var firstName: String,
        var lastName: String,
        var address: String,
        @Id @GeneratedValue val id: Long? = null
)