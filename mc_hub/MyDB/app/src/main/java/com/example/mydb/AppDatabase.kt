package com.example.mydb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Company::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDAO
}