package dev.maxsiomin.testdoorscameras.presentation

sealed class Screen(val route: String) {

    //object CameraScreen : Screen("camera_screen")
    //object DoorScreen : Screen("door_screen")
    object TabsScreen : Screen("tabs_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append(arg)
            }
        }
    }

}