package com.assignment.models

import com.assignment.dto.UserDTO
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
//import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Users : IntIdTable() {
    val name: Column<String> = varchar("name", 255)
    val age: Column<Int> = integer("age")
    val job: Column<String> = varchar("job", 255)
    val pet: Column<String> = varchar("pet", 255)
    val date: Column<LocalDateTime> = datetime("date")
}

class User(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var name by Users.name
    var age by Users.age
    var job by Users.job
    var pet by Users.pet
    var date by Users.date

    fun toUsersDTO() = UserDTO(name, age, job, pet, date)
}
