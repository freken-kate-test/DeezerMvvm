package ru.test.lightdeezer.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Playlist(
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val picture_small: String,
    val picture_medium: String) {
}