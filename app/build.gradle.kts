plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.realm)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinSerialization)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "dev.maxsiomin.testdoorscameras"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.maxsiomin.testdoorscameras"
        minSdk = 24
        targetSdk = 34
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)

    // If once I decide to write some tests, something seems to be wrong with these dependencies, better examine
    testImplementation(libs.junit)
    debugImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Realm
    implementation(libs.library.base)
    //implementation("io.realm.kotlin:library-sync:1.10.0")// If using Device Sync
    implementation(libs.kotlinx.coroutines.core) // If using coroutines with the SDK

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.ktor.client.core)
    // HTTP engine: The HTTP client used to perform network requests.
    implementation(libs.ktor.client.android)
    // The serialization engine used to convert objects to and from JSON.
    implementation(libs.ktor.client.serialization)
    // Logging
    implementation(libs.ktor.client.logging)

    implementation(libs.timber)

    // Tabs
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.kotlinx.serialization.json)

    // Image loading
    // Updating to 2.5.0 caused crashes
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Pull to refresh
    implementation(libs.accompanist.swiperefresh)
}