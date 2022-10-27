package com.example.clinic.repository

import com.example.clinic.model.Doctor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository : CrudRepository<Doctor, Long> {


}