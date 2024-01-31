package dev.maxsiomin.testdoorscameras.util

import android.content.res.Resources

fun Float.Dp(): Float = this * density + 0.5f

val density: Float
    get() = Resources.getSystem().displayMetrics.density
