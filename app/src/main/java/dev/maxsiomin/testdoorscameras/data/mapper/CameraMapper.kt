package dev.maxsiomin.testdoorscameras.data.mapper

import dev.maxsiomin.testdoorscameras.data.local.CameraEntity
import dev.maxsiomin.testdoorscameras.data.remote.dto.Camera
import dev.maxsiomin.testdoorscameras.domain.CameraModel

fun Camera.toCameraModel(): CameraModel {
    return CameraModel(
        rec = rec,
        room = room,
        isFavorites = favorites,
        name = name,
        id = id,
        snapshot = snapshot,
    )
}

fun CameraModel.toCameraEntity(): CameraEntity {
    return CameraEntity().apply {
        this.rec = this@toCameraEntity.rec
        this.id = this@toCameraEntity.id
        this.favorites = this@toCameraEntity.isFavorites
        this.name = this@toCameraEntity.name
        this.room = this@toCameraEntity.room ?: ""
        this.snapshot = this@toCameraEntity.snapshot
    }
}

fun CameraEntity.toCameraModel(): CameraModel {
    return CameraModel(
        rec = rec,
        room = room,
        isFavorites = favorites,
        name = name,
        id = id,
        snapshot = snapshot,
    )
}
