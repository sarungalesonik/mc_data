package com.example.mydbflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataModelViewModel(
    private val dao: DataModelDao
): ViewModel() {
    fun addData() {
        viewModelScope.launch {
            (0..10).forEach { _ ->
                val newDataToAdd = DataModel(
                    text = "Task ${(0..10).random()}",
                    priority = (0..100).random()
                )
                dao.upsertDataModel(newDataToAdd)
            }
        }
    }

    fun getAllRecords(): Flow<List<DataModel>> {
        return dao.getAllRecords()
    }

    fun sortBy(sort: Sort): Flow<List<DataModel>> {
        if (sort == Sort.TEXT) {
            return dao.sortByText()
        }

        return dao.sortByPriority()
    }

    fun filterPriority(operator: Operator, number: Int): Flow<List<DataModel>> {
        if (operator == Operator.LESS_THAN) {
            return dao.filterPriorityLessThan(number)
        }
        return dao.filterPriorityGreaterThan(number)
    }


    fun query(
        tableName: String = "datamodel",
        filter: String? = null,
        sort: Sort? = null,
        order: Order = Order.ASC
    ): Flow<List<DataModel>> {
        var queryText = "SELECT * FROM $tableName"

        if (filter != null) {
            queryText += " WHERE $filter"
        }

        if (sort != null) {
            queryText += " ORDER BY ${sort.name}"
            queryText += " ${order.name}"
        }

        return dao.query(SimpleSQLiteQuery(queryText))
    }
}