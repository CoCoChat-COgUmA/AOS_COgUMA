package com.CCC.CoCoChat.Data.Repository

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: String,
    val name: String,
)
