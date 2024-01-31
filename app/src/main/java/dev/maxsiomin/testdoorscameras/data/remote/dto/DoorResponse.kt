package dev.maxsiomin.testdoorscameras.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DoorResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val `data`: List<DoorData>
)

@Keep
@Serializable
data class DoorData(
    @SerialName("name")
    val name: String,
    @SerialName("room")
    val room: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("favorites")
    val favorites: Boolean,
    @SerialName("snapshot")
    val snapshot: String? = null,
)
