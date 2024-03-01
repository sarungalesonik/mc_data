package com.example.mydbflow

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

//Data Access Object
@Dao
interface DataModelDao {
    // Upsert means that this will either insert the object if it doesn't exist in the database
    // or update the object if it does already exists in the database
    @Upsert
    suspend fun upsertDataModel(dataModel: DataModel)

    @Query("SELECT * FROM datamodel")
    fun getAllRecords(): Flow<List<DataModel>>

    @Query("SELECT * FROM datamodel ORDER BY priority ASC")
    fun sortByPriority(): Flow<List<DataModel>>

    @Query("SELECT * FROM datamodel ORDER BY text ASC")
    fun sortByText(): Flow<List<DataModel>>

    @Query("SELECT * FROM datamodel WHERE priority < :number")
    fun filterPriorityLessThan(number: Int): Flow<List<DataModel>>

    @Query("SELECT * FROM datamodel WHERE priority > :number")
    fun filterPriorityGreaterThan(number: Int): Flow<List<DataModel>>

    @RawQuery(observedEntities = [DataModel::class])
    fun query(query: SupportSQLiteQuery): Flow<List<DataModel>>
}
