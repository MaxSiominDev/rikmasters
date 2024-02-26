package dev.maxsiomin.testdoorscameras.presentation.door_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.maxsiomin.testdoorscameras.domain.DoorModel

@Composable
fun DoorItemContent(door: DoorModel) {

    Column {
        door.snapshot?.let { snapshot ->
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                model = door.snapshot,
                contentScale = ContentScale.FillBounds,
                contentDescription = "${door.name} snapshot",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                text = door.name,
                fontSize = 19.sp,
            )

            //Spacer(Modifier.weight(1f).fillMaxHeight().background(Color.Green))

            Icon(
                modifier = Modifier.padding(horizontal = 30.dp),
                imageVector = Icons.Outlined.Lock,
                tint = Color(0xFF03A9F4),
                contentDescription = "",
            )
        }
    }

}

@Preview
@Composable
fun CameraItemContentPreview() {

    DoorItemContent(door = DoorModel(
        isFavorites = true,
        room = "Room name",
        snapshot = "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png",
        id = 1,
        name = "Camera name"
    )
    )

}