package com.assignment.dao

import com.assignment.dto.UserDTO
import com.assignment.models.User
import kotlinx.datetime.LocalDateTime

class DAOFacadeImpl : DAOFacade {
    override suspend fun allUsers(): List<UserDTO>  {
        val users = User.all()
        return users.map { it.toUsersDTO() }
    }

    override suspend fun user(id: Int): User?  {
        val user = User.findById(id)
        return user
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
}