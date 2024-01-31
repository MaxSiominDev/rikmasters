package dev.maxsiomin.testdoorscameras.presentation.door_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ModeEditOutline
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.maxsiomin.testdoorscameras.domain.DoorModel

@Composable
fun BoxScope.DoorAction(
    door: DoorModel,
    onEdit: (DoorModel) -> Unit,
    onFavorites: () -> Unit,
) {

    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.align(Alignment.CenterEnd)) {
        OutlinedButton(onClick = { onEdit(door) },
            modifier = Modifier.size(40.dp),  //avoid the oval shape
            shape = CircleShape,
            border= BorderStroke(1.dp, Color(0xFFE6E6E6)),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
        ) {
            val icon = Icons.Outlined.ModeEditOutline
            Icon(
                icon,
                tint = Color(0xFF03A9F4),
                contentDescription = "content description",
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(onClick = { onFavorites() },
            modifier = Modifier.size(40.dp),  //avoid the oval shape
            shape = CircleShape,
            border= BorderStroke(1.dp, Color(0xFFE6E6E6)),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
        ) {
            val icon = if (door.isFavorites) Icons.Outlined.Star else Icons.Outlined.StarOutline
            Icon(
                icon,
                tint = Color(0xFFE4C859),
                contentDescription = "content description",
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
    }

}

@Preview
@Composable
fun DoorActionPreview() {
    Box {
       DoorAction(DoorModel(
           isFavorites = true,
           id = 1,
           name = "",
           snapshot = "",
           room = "",
       ), {}, {})
    }
}
