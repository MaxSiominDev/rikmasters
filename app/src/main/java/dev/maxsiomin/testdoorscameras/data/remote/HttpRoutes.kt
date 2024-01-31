package dev.maxsiomin.testdoorscameras.data.remote

@Suppress("SpellCheckingInspection")
object HttpRoutes {

    private const val BASE_URL = "http://cars.cprogroup.ru/api"

    const val GET_CAMERAS = "$BASE_URL/rubetek/cameras/"
    const val GET_DOORS = "$BASE_URL/rubetek/doors/"

}
