package dev.maxsiomin.testdoorscameras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.testdoorscameras.presentation.Navigation
import dev.maxsiomin.testdoorscameras.ui.theme.TestDoorsCamerasTheme
import dev.maxsiomin.testdoorscameras.ui.theme.backgroundColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestDoorsCamerasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    Navigation()
                }
            }
        }
    }
}
