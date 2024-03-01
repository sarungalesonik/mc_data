package com.example.mydbflow

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataModel::class], version = 1)
abstract class DataModelDatabase: RoomDatabase() {
    abstract val dao: DataModelDao
}