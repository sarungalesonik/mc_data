package com.example.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface CompanyDAO {
    @Query("SELECT * FROM Company")
    fun getAll(): Flow<List<Company>>

    @Query("SELECT * FROM Company WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<Company>>

    @Query("SELECT * FROM Company WHERE company_name LIKE :first")
    fun findByName(first: String): Company

    @Upsert
    suspend fun insertAll(users: Company)

    @Delete
    fun delete(user: Company)
}