package dev.maxsiomin.testdoorscameras.util

import dev.maxsiomin.testdoorscameras.BuildConfig

fun isDebug() = BuildConfig.DEBUG

fun isRelease() = !isDebug()
