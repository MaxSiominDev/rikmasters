package dev.maxsiomin.testdoorscameras.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class CameraResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val `data`: Data
)

@Keep
@Serializable
data class Data(
    @SerialName("room")
    val room: List<String>,
    @SerialName("cameras")
    val cameras: List<Camera>
)

@Keep
@Serializable
data class Camera(
    @SerialName("name")
    val name: String,
    @SerialName("snapshot")
    val snapshot: String,
    @SerialName("room")
    val room: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("favorites")
    val favorites: Boolean,
    @SerialName("rec")
    val rec: Boolean
)