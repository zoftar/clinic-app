package com.example.clinic.resource

import com.example.clinic.dto.AppointmentCreateDto
import com.example.clinic.dto.AppointmentUpdateDto
import com.example.clinic.model.Appointment
import com.example.clinic.service.AppointmentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/appointments")
@RestController
class AppointmentResource(val appointmentService: AppointmentService) {

    @GetMapping
    fun getAppointments() = appointmentService.getAppointments()

    @GetMapping("/{patientId}")
    fun getAppointmentsByPatient(@PathVariable patientId: Long) = appointmentService.getAppointmentsByPatient(patientId)

    @PostMapping
    fun createAppointment(@RequestBody appointment: AppointmentCreateDto) = appointmentService.createAppointment(appointment)

    @PutMapping("/{id}")
    fun updateAppointment(@PathVariable id: Long, @RequestBody updateDto: AppointmentUpdateDto): Appointment = appointmentService.updateAppointment(id, updateDto)

    @DeleteMapping("/{id}")
    fun deleteAppointment(@PathVariable id: Long) = appointmentService.deleteAppointment(id)
}