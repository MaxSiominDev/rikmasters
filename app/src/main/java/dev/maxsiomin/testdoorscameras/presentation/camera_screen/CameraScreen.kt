package dev.maxsiomin.testdoorscameras.presentation.camera_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.ui.theme.backgroundColor
import dev.maxsiomin.testdoorscameras.util.Dp

@Composable
fun CameraScreen(
    cards: List<CameraModel>,
    revealedCardIds: List<Int>,
    onEvent: (CameraViewModel.Event) -> Unit,
) {

    Box(modifier = Modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                Color.White,
                backgroundColor,
            )
        )
    ).padding(vertical = 8.dp)) {
        Scaffold(backgroundColor = Color.Transparent) { paddingValues ->
            LazyColumn(Modifier.padding(paddingValues)) {
                items(cards, CameraModel::id) { camera ->
                    Column {
                        camera.room?.let {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                text = camera.room,
                                fontSize = 20.sp
                            )
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CameraAction(camera.isFavorites, onFavorites = {
                                onEvent(CameraViewModel.Event.Favorites(camera))
                            })

                            CameraCard(
                                camera = camera,
                                isRevealed = revealedCardIds.contains(camera.id),
                                cardOffset = CARD_OFFSET.Dp(),
                                onExpand = { onEvent(CameraViewModel.Event.ItemExpanded(camera.id)) },
                                onCollapse = { onEvent(CameraViewModel.Event.ItemCollapsed(camera.id)) },
                            )
                        }
                    }
                }
            }
        }
    }


}

private const val CARD_OFFSET = -60f
