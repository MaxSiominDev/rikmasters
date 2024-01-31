package dev.maxsiomin.testdoorscameras.data.mapper

import dev.maxsiomin.testdoorscameras.data.local.CameraEntity
import dev.maxsiomin.testdoorscameras.data.local.DoorEntity
import dev.maxsiomin.testdoorscameras.data.remote.dto.Camera
import dev.maxsiomin.testdoorscameras.data.remote.dto.DoorData
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.domain.DoorModel

fun DoorData.toDoorModel(): DoorModel {
    return DoorModel(
        room = room,
        isFavorites = favorites,
        name = name,
        id = id,
        snapshot = snapshot,
    )
}

fun DoorModel.toDoorEntity(): DoorEntity {
    return DoorEntity().apply {
        this.id = this@toDoorEntity.id
        this.favorites = this@toDoorEntity.isFavorites
        this.name = this@toDoorEntity.name
        this.room = this@toDoorEntity.room ?: ""
        this.snapshot = this@toDoorEntity.snapshot
    }
}

fun DoorEntity.toDoorModel(): DoorModel {
    return DoorModel(
        room = room,
        isFavorites = favorites,
        name = name,
        id = id,
        snapshot = snapshot
    )
}
