package com.assignment.dao

import com.assignment.models.User
import kotlinx.datetime.LocalDateTime

interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(name: String, age: Int, job: String, pet: String, date: LocalDateTime): User?
    suspend fun editUser(name: String, age: Int, job: String, pet: String, date: LocalDateTime): Boolean
    suspend fun deleteUser(id: Int): Boolean
}