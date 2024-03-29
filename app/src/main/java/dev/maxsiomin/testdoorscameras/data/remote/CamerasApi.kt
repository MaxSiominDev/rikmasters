package dev.maxsiomin.testdoorscameras.data.remote

import dev.maxsiomin.testdoorscameras.data.remote.dto.CameraResponse
import dev.maxsiomin.testdoorscameras.data.remote.dto.DoorResponse

interface CamerasApi {

    suspend fun getCameras(): ResponseWithMessage<CameraResponse, Exception>

    suspend fun getDoors(): ResponseWithMessage<DoorResponse, Exception>

}

data class ResponseWithMessage<T, E : Exception> (
    val response: T?,
    val error: E?,
)
