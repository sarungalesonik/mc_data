package com.example.mydbflow


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Adding @Entity here tells Room that this will be a table in our database
@Entity
data class DataModel(
    // We can add attributes to the fields of our database using the @PrimaryKey annotation
    // the PrimaryKey annotation here tells our database that this column the primary key
    // and is therefore guaranteed to be unique
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val priority: Int
)

enum class Operator {
    GREATER_THAN,
    LESS_THAN
}

// the fields in our table that we may want to sort on
enum class Sort {
    TEXT,
    PRIORITY
}

// should we sort in ascending or descending order
enum class Order {
    ASC,
    DESC
}