package dev.maxsiomin.testdoorscameras.presentation.camera_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.maxsiomin.testdoorscameras.domain.CameraModel

@Composable
fun CameraItemContent(camera: CameraModel) {

    Column {
        Box {
            val snapshot = camera.snapshot
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                model = camera.snapshot,
                contentDescription = "${camera.name} snapshot",
            )

            if (camera.isFavorites) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(20.dp),
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color(0xFFE4C859),
                )
            }

            if (camera.rec) {
                DotAndRec()
            }

        }

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            text = camera.name,
            fontSize = 19.sp,
        )
    }


}

@Composable
fun DotAndRec() {
    Row(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
        val color = Color(0xFFE52C2C)
        Box(
            Modifier
                .background(color = color, shape = CircleShape)
                .size(4.dp)
        ) {
            Text(text = "")
        }

        Text(text = "REC", color = color)
    }
}

@Preview
@Composable
fun CameraItemContentPreview() {

    CameraItemContent(camera = CameraModel(
        rec = true,
        isFavorites = true,
        room = "Room name",
        snapshot = "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png",
        id = 1,
        name = "Camera name"
    ))

}
