package com.example.clinic.service

import com.example.clinic.model.Patient
import com.example.clinic.repository.PatientRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PatientService(val repository: PatientRepository) {
    fun getPatients(): Collection<Patient> = repository.findAll()

    fun getPatient(id: Long): Patient {
        val patient = repository.findById(id)
        if (patient.isEmpty) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Patient with id %d does not exist", id))
        }
        return patient.get()
    }

    fun createPatient(patient: Patient): Patient = repository.save(patient)

    fun updatePatient(id: Long, patient: Patient): Patient {
        val existingPatient = repository.findById(id)
        if (!existingPatient.isPresent) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Patient with id %d does not exist", id))
        }
        return repository.save(patient)
    }

    fun deletePatient(id: Long) = repository.deleteById(id)

}