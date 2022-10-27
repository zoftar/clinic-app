package com.example.clinic.dto

import java.time.LocalDateTime

class AppointmentCreateDto(val date: LocalDateTime, val location: String, val doctorId: Long, val patientId: Long)