package dev.maxsiomin.testdoorscameras.data.remote

import dev.maxsiomin.testdoorscameras.data.remote.dto.CameraResponse
import dev.maxsiomin.testdoorscameras.data.remote.dto.DoorResponse
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.util.Resource

interface CamerasApi {

    suspend fun getCameras(): ResponseWithMessage<CameraResponse, Exception>

    suspend fun getDoors(): ResponseWithMessage<DoorResponse, Exception>

}

data class ResponseWithMessage<T, E : Exception> (
    val response: T?,
    val error: E?,
)
