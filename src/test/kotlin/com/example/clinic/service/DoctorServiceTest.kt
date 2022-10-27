package com.example.clinic.service

import com.example.clinic.model.Doctor
import com.example.clinic.repository.DoctorRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

internal class DoctorServiceTest {

    private val _doctorId1 = 1L
    private val _doctorId2 = 2L

    private val repository = mock(DoctorRepository::class.java)

    private val doctorService = DoctorService(repository)

    @Test
    fun getDoctor() {
        // given
        val firstName = "John"
        val lastName = "Smith"
        val specialization = "oncologist"
        val doctor1 = Doctor(firstName, lastName, specialization, _doctorId1)
        whenever(repository.findById(eq(_doctorId1))).thenReturn(Optional.of(doctor1))

        // when
        val result = doctorService.getDoctor(_doctorId1)

        // then
        assertEquals(_doctorId1, result.id)
        assertEquals(firstName, result.firstName)
        assertEquals(lastName, result.lastName)
        assertEquals(specialization, result.specialization)
    }

    @Test
    fun getDoctor_NotFoundException() {
        // when
        val exception = assertThrows(ResponseStatusException::class.java) {
            doctorService.getDoctor(_doctorId2)
        }

        // then
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }

    @Test
    fun createDoctor() {
    }

    @Test
    fun updateDoctor() {
    }

    @Test
    fun deleteDoctor() {
    }

    @Test
    fun getRepository() {
    }
}