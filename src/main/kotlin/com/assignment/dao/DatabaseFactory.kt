package com.assignment.dao

import com.assignment.models.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://db.mebwagkgajmsdwofdlbi.supabase.co:5432/postgres"
        val database = Database.connect(jdbcURL, driverClassName)

        transaction {
            SchemaUtils.create(Users)
        }
    }
}