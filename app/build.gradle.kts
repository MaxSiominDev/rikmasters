plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Realm
    implementation("io.realm.kotlin:library-base:1.10.2")
    //implementation("io.realm.kotlin:library-sync:1.10.0")// If using Device Sync
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // If using coroutines with the SDK

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    val ktorVersion = "1.6.4"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    // HTTP engine: The HTTP client used to perform network requests.
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    // The serialization engine used to convert objects to and from JSON.
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    // Logging
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    implementation("com.jakewharton.timber:timber:5.0.1")

    // Tabs
    implementation("com.google.accompanist:accompanist-pager:0.17.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.17.0")

    // Fonts
    //implementation("androidx.compose.ui:ui-text-google-fonts:1.5.0")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-alpha01")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Image loading
    implementation("io.coil-kt:coil:2.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Pull to refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.33.1-alpha")
}