package dev.maxsiomin.testdoorscameras.domain

data class DoorModel(
    val isFavorites: Boolean,
    val room: String?,
    val snapshot: String?,
    val name: String,
    val id: Int,
)
