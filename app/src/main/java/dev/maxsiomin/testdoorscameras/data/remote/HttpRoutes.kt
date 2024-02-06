package dev.maxsiomin.testdoorscameras.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://maxsiomin.dev/api/apps/rikmasters/"

    const val GET_CAMERAS = "$BASE_URL/cameras.json"
    const val GET_DOORS = "$BASE_URL/doors.json"

}
