package com.assignment.dao

import com.assignment.dto.FoodDTO
import com.assignment.dto.GlucoseDTO
import com.assignment.dto.PetDTO
import com.assignment.dto.UserDTO
import com.assignment.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class DAOFacadeImpl : DAOFacade {
    override suspend fun allUsers(): List<UserDTO> = newSuspendedTransaction(Dispatchers.IO)  {
        val users = User.all()
        users.map { it.toUsersDTO() }
    }

    override suspend fun user(id: Int): User? = newSuspendedTransaction(Dispatchers.IO) {
        User.findById(id)
    }

    override suspend fun userByName(name: String): UserDTO? = newSuspendedTransaction(Dispatchers.IO) {
        User.find {  Users.name eq name }.firstOrNull()?.toUsersDTO()
    }

    override suspend fun addNewUser(userDTO: UserDTO): User = newSuspendedTransaction(Dispatchers.IO) {
        User.new {
            this.name = userDTO.name
            this.age = userDTO.age
            this.job = userDTO.job
            this.pet = userDTO.pet
            this.date = userDTO.date
        }
    }

    override suspend fun editUser(id: Int, name: String, age: Int, job: String, pet: String, date: LocalDateTime) {
        val user = User.findById(id)
        user?.name = name
        user?.age = age
        user?.job = job
        user?.pet = pet
        user?.date = date
    }

    override suspend fun deleteUser(id: Int) {
        val user = User.findById(id)
        user?.delete()
    }

    override suspend fun allPets(): List<PetDTO> = newSuspendedTransaction(Dispatchers.IO) {
        val pets = Pet.all()
        pets.map { it.toPetsDTO() }
    }

    override suspend fun allFoods(): List<FoodDTO> = newSuspendedTransaction(Dispatchers.IO) {
        val foods = Food.all()
        foods.map { it.toFoodsDTO() }
    }

    override suspend fun allGlucoseData(): List<GlucoseDTO> = newSuspendedTransaction(Dispatchers.IO) {
        val glucoseData = Glucose.all()
        glucoseData.map { it.toGlucoseDataDTO() }
    }

    override suspend fun addNewGlucoseData(glucoseDTO: GlucoseDTO): Glucose = newSuspendedTransaction(Dispatchers.IO) {
        Glucose.new {
            this.value = glucoseDTO.value
            this.date = glucoseDTO.date
        }
    }

    override suspend fun addRoleToUser(userId: Int, roleId: Int) = newSuspendedTransaction(Dispatchers.IO) {
        val user = User.findById(userId)
        val role = Role.findById(roleId)
        user?.role = role!!
    }
}