package com.example.Mandi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class form (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val mail:String,
    val image:String
    )
