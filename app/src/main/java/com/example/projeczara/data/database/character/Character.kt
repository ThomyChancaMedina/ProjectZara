package com.example.projeczara.data.database.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projeczara.data.domain.Location
import com.example.projeczara.data.domain.Origin


@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @ColumnInfo(name = "origin")
    val origin: Origin,
    @ColumnInfo(name = "location")
    val location: Location,
    val image: String,
    @ColumnInfo(name = "episodes")
    val episode: List<String>,
    val url: String,
    val created: String
)
