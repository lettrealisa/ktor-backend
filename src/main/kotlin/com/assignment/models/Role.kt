package com.assignment.models

import com.assignment.dto.FoodDTO
import com.assignment.dto.RoleDTO
import com.assignment.dto.UserDTO
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Roles : IntIdTable() {
    val name: Column<String> = varchar("name", 255)
}

class Role(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Role>(Roles)
    var name by Roles.name

    fun toRolesDTO() = RoleDTO(name)
}