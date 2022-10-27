package com.example.clinic.resource

import com.example.clinic.model.Doctor
import com.example.clinic.service.DoctorService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/doctors")
@RestController
class DoctorResource(val doctorService: DoctorService) {

    @GetMapping("/{id}")
    fun getDoctor(@PathVariable id: Long) : Doctor = doctorService.getDoctor(id)

    @PostMapping
    fun createDoctor(@RequestBody doctor: Doctor) : Doctor = doctorService.createDoctor(doctor)

    @PutMapping("/{id}")
    fun updateDoctor(@PathVariable id : Long, @RequestBody doctor: Doctor) = doctorService.updateDoctor(id, doctor)

    @DeleteMapping("/{id}")
    fun deleteDoctor(@PathVariable id: Long) = doctorService.deleteDoctor(id)
}