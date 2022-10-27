package com.example.clinic.service

import com.example.clinic.dto.AppointmentCreateDto
import com.example.clinic.dto.AppointmentUpdateDto
import com.example.clinic.model.Appointment
import com.example.clinic.model.Doctor
import com.example.clinic.model.Patient
import com.example.clinic.repository.AppointmentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import java.util.*

class AppointmentServiceTest {

    private val _patientId = 1L
    private val _doctorId = 2L
    private val _appointmentId = 3L
    private val _appointmentDate = LocalDateTime.of(2022, 6, 2, 9, 0)

    private val repository = mock(AppointmentRepository::class.java)

    private val doctorService = mock(DoctorService::class.java)

    private val patientService = mock(PatientService::class.java)

    private val appointmentService = AppointmentService(repository, doctorService, patientService)

    @Test
    fun getAppointments() {
        // given
        whenever(repository.findAll()).thenReturn(appointments())

        // when
        val result = appointmentService.getAppointments()

        // then
        assertEquals(1, result.count())
    }

    @Test
    fun getAppointmentsByPatient() {
        // given
        whenever(repository.findAllByPatientId(eq(_patientId))).thenReturn(appointments())

        // when
        val result = appointmentService.getAppointmentsByPatient(_patientId)

        // then
        assertEquals(1, result.count())
    }

    @Test
    fun createAppointment() {
        // given
        val doctor = Doctor("Jane", "Doe", "Ophthalmologist", _doctorId)
        val patient = Patient("John", "Smith", "15 Yemen Road", _patientId)
        whenever(doctorService.getDoctor(eq(_doctorId))).thenReturn(doctor)
        whenever(patientService.getPatient(eq(_patientId))).thenReturn(patient)
        whenever(repository.save(any<Appointment>())).thenReturn(Appointment(_appointmentDate, "Clinic", doctor, patient, _appointmentId))

        // when
        val result = appointmentService.createAppointment(AppointmentCreateDto(_appointmentDate, "Clinic", _doctorId, _patientId))

        // then
        assertNotNull(result)
        assertEquals(_appointmentDate, result.date)
        assertEquals(_appointmentId, result.id)
        assertEquals(doctor, result.doctor)
        assertEquals(patient, result.patient)
    }

    @Test
    fun updateAppointment() {
        // given
        val existingAppointment = appointment()
        val newDate = LocalDateTime.of(2022, 7, 1, 10, 0)
        val updatedAppointment = Appointment(newDate, existingAppointment.location, existingAppointment.doctor, existingAppointment.patient, _appointmentId)
        whenever(repository.findById(eq(_appointmentId))).thenReturn(Optional.of(existingAppointment))
        whenever(repository.save(any<Appointment>())).thenReturn(updatedAppointment)

        // when
        val result = appointmentService.updateAppointment(_appointmentId, AppointmentUpdateDto(newDate))

        // then
        assertNotNull(result)
        assertEquals(newDate, result.date)
        assertEquals(_appointmentId, result.id)
    }

    @Test
    fun deleteAppointment() {
        appointmentService.deleteAppointment(_appointmentId)
    }

    fun appointment(): Appointment {
        val doctor = Doctor("Jane", "Doe", "Ophthalmologist", _doctorId)
        val patient = Patient("John", "Smith", "15 Yemen Road", _patientId)
        return Appointment(_appointmentDate, "Poznan",
                doctor, patient, _appointmentId)
    }

    fun appointments(): Set<Appointment> {
        return setOf(appointment())
    }
}