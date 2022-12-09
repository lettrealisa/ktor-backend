package com.assignment.dao

import com.assignment.dto.PetDTO
import com.assignment.dto.UserDTO
import com.assignment.models.Pet
import com.assignment.models.Pets
import com.assignment.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DAOFacadeImpl : DAOFacade {
    override suspend fun allUsers(): List<UserDTO> = newSuspendedTransaction(Dispatchers.IO)  {
        val users = User.all()
        users.map { it.toUsersDTO() }
    }

    override suspend fun user(id: Int): User?  {
        val user = User.findById(id)
        return user
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
}