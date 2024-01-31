package dev.maxsiomin.testdoorscameras.data.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CameraEntity : RealmObject {

    var name: String = ""
    var snapshot: String = ""
    var room: String? = null
    @PrimaryKey
    var id: Int = -1
    var favorites: Boolean = false
    var rec: Boolean = false

}
