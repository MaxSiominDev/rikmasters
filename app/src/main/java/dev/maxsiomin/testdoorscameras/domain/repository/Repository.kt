package dev.maxsiomin.testdoorscameras.domain.repository

import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.domain.DoorModel
import dev.maxsiomin.testdoorscameras.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCameras(fetchFromRemote: Boolean): Flow<Resource<List<CameraModel>>>

    suspend fun getDoors(fetchFromRemote: Boolean): Flow<Resource<List<DoorModel>>>
    
    suspend fun updateCamera(camera: CameraModel, callback: () -> Unit)

    suspend fun updateDoor(door: DoorModel, callback: () -> Unit)

}