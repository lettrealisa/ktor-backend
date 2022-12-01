package com.assignment.dao

import com.assignment.dto.UserDTO
import com.assignment.models.User
import kotlinx.datetime.LocalDateTime

interface DAOFacade {
    suspend fun allUsers(): List<UserDTO>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(userDTO: UserDTO): User
    suspend fun editUser(id: Int, name: String, age: Int, job: String, pet: String, date: LocalDateTime)
    suspend fun deleteUser(id: Int)
}