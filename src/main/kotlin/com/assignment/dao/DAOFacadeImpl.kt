package com.assignment.dao

import com.assignment.models.User
import kotlinx.datetime.LocalDateTime

class DAOFacadeImpl : DAOFacade {
    override suspend fun allUsers(): List<User>  {
        TODO("Not yet implemented")
    }

    override suspend fun user(id: Int): User?  {
        TODO("Not yet implemented")
    }

    override suspend fun addNewUser(name: String, age: Int, job: String, pet: String, date: LocalDateTime): User {
        val user = User.new {
            this.name = name
            this.age = age
            this.job = job
            this.pet = pet
            this.date = date
        }
        return user
    }

    override suspend fun editUser(name: String, age: Int, job: String, pet: String, date: LocalDateTime): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}