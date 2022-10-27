package com.example.clinic.repository

import com.example.clinic.model.Appointment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository : CrudRepository <Appointment, Long> {
    fun findAllByPatientId(patientId: Long): Collection<Appointment>
}