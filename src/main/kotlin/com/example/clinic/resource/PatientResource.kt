package com.example.clinic.resource

import com.example.clinic.model.Patient
import com.example.clinic.service.PatientService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RequestMapping("api/v1/patients")
@RestController
class PatientResource(val patientService: PatientService) {

    @GetMapping
    fun getPatients() = patientService.getPatients()

    @GetMapping("/{id}")
    fun getPatient(@PathVariable id: Long) : Patient {
        return patientService.getPatient(id)
    }

    @PostMapping
    fun createPatient(@RequestBody patient: Patient): Patient {
        return patientService.createPatient(patient)
    }

    @PutMapping("/{id}")
    fun updatePatient(@PathVariable id: Long, @RequestBody patient: Patient): Patient {
        return patientService.updatePatient(id, patient)
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable id: Long) = patientService.deletePatient(id)
}