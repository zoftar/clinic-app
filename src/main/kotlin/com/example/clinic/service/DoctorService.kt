package com.example.clinic.service

import com.example.clinic.model.Doctor
import com.example.clinic.repository.DoctorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DoctorService(val repository: DoctorRepository) {

    fun getDoctor(id: Long) : Doctor {
        val doctor = repository.findById(id);
        if (doctor.isPresent) {
            return doctor.get();
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Doctor with id %d does not exist", id));
    }

    fun createDoctor(doctor: Doctor) : Doctor = repository.save(doctor);

    fun updateDoctor(id : Long, doctor: Doctor): Doctor {
        val existingDoctor = repository.findById(id);
        if (!existingDoctor.isPresent) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Doctor with id %d does not exist", id));
        }
        return repository.save(doctor)
    }

    fun deleteDoctor(id: Long) = repository.deleteById(id)
}