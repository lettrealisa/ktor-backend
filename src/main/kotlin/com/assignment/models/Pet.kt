package com.assignment.models

import com.assignment.dto.PetDTO
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

object Pets : IntIdTable() {
    val name: Column<String> = varchar("name", 255)
}

class Pet(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Pet>(Pets)
    var name by Pets.name

    fun toPetsDTO() = PetDTO(name)
}
