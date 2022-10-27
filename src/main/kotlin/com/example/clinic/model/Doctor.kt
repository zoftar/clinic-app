package com.example.clinic.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Doctor(
        val firstName: String,
        val lastName: String,
        val specialization: String,
        @Id @GeneratedValue val id: Long? = null
)