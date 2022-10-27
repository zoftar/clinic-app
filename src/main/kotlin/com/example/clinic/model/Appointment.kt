package com.example.clinic.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Appointment(
        var date: LocalDateTime,
        val location: String,
        @ManyToOne
        val doctor: Doctor,
        @ManyToOne
        val patient: Patient,
        @Id @GeneratedValue val id: Long? = null
        )