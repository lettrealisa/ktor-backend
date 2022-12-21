package com.assignment.dao

import com.assignment.dto.*
import com.assignment.models.Glucose
import com.assignment.models.Role
import com.assignment.models.User
import kotlinx.datetime.LocalDateTime

interface DAOFacade {
    suspend fun allUsers(): List<UserDTO>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(userDTO: UserDTO): User
    suspend fun editUser(id: Int, name: String, age: Int, job: String, pet: String, date: LocalDateTime)
    suspend fun deleteUser(id: Int)
    suspend fun allPets(): List<PetDTO>
    suspend fun allFoods(): List<FoodDTO>
    suspend fun allGlucoseData(): List<GlucoseDTO>
    suspend fun addNewGlucoseData(glucoseDTO: GlucoseDTO): Glucose
    suspend fun addRoleToUser(userId: Int, roleId: Int)
}