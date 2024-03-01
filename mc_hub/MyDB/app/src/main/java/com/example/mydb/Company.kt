package com.example.mydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "company_name") val company_name: String
)