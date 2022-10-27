package com.example.clinic.service

import com.example.clinic.dto.AppointmentCreateDto
import com.example.clinic.dto.AppointmentUpdateDto
import com.example.clinic.model.Appointment
import com.example.clinic.repository.AppointmentRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AppointmentService(val repository: AppointmentRepository, val doctorService: DoctorService, val patientService: PatientService) {

    /**
     * Returns all appointments.
     * @return list of appointments
     */
    fun getAppointments() = repository.findAll()

    /**
     * Returns all appointments registered for a patient
     * @param patientId id of the patient
     * @return list of appointments registered for the given patient
     */
    fun getAppointmentsByPatient(patientId: Long) = repository.findAllByPatientId(patientId)

    /**
     * Creates an appointment
     * @param appointmentCreateDto new appointment
     * @return the created appointment
     */
    fun createAppointment(appointmentCreateDto: AppointmentCreateDto): Appointment {
        val doctor = doctorService.getDoctor(appointmentCreateDto.doctorId)
        val patient = patientService.getPatient(appointmentCreateDto.patientId)
        return repository.save(Appointment(appointmentCreateDto.date, appointmentCreateDto.location, doctor, patient))
    }

    /**
     * Updates an appointment
     * @param id id of the appointment
     * @param updateDto DTO containing information to be updated
     * @return the updated appointment
     */
    fun updateAppointment(id: Long, updateDto: AppointmentUpdateDto): Appointment {
        val existingAppointment = repository.findById(id)
        if (!existingAppointment.isPresent) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Appointment with id %d does not exist", id))
        }
        val appointmentToUpdate = existingAppointment.get()
        appointmentToUpdate.date = updateDto.date
        return repository.save(appointmentToUpdate)
    }

    /**
     * Deletes an appointment.
     * @param id appointment id
     */
    fun deleteAppointment(id: Long) = repository.deleteById(id)
}