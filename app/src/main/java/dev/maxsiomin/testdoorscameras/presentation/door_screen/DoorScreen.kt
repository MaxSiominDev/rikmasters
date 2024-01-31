package dev.maxsiomin.testdoorscameras.presentation.door_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.maxsiomin.testdoorscameras.domain.DoorModel
import dev.maxsiomin.testdoorscameras.ui.theme.backgroundColor
import dev.maxsiomin.testdoorscameras.util.Dp

@Composable
fun DoorScreen(
    cards: List<DoorModel>,
    revealedCardIds: List<Int>,
    onEvent: (DoorViewModel.Event) -> Unit,
) {

    var dialogState by remember {
        mutableStateOf(DialogState())
    }

    if (dialogState.showDialog && dialogState.door != null) {
        EditDoorDialog(
            onNewName = {
            onEvent(DoorViewModel.Event.EditDoorName(
                door = dialogState.door!!,
                newName = it
            ))
        }, dismissDialog = {
            dialogState = dialogState.copy(showDialog = false)
        })
    }

    Box(modifier = Modifier
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White,
                    backgroundColor,
                )
            )
        )
        .padding(vertical = 8.dp)
    ) {
        Scaffold(backgroundColor = Color.Transparent) { paddingValues ->
            LazyColumn(Modifier.padding(paddingValues)) {
                items(cards, DoorModel::id) { door ->
                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DoorAction(
                                door,
                                onEdit = {
                                    dialogState = dialogState.copy(
                                        showDialog = true,
                                        door = it
                                    )
                                },
                                onFavorites = {
                                    onEvent(DoorViewModel.Event.Favorites(door))
                                },
                            )

                            DoorCard(
                                door = door,
                                isRevealed = revealedCardIds.contains(door.id),
                                cardOffset = CARD_OFFSET.Dp(),
                                onExpand = { onEvent(DoorViewModel.Event.ItemExpanded(door.id)) },
                                onCollapse = { onEvent(DoorViewModel.Event.ItemCollapsed(door.id)) },
                            )
                        }
                    }
                }
            }
        }
    }

}

data class DialogState(
    val showDialog: Boolean = false,
    val door: DoorModel? = null,
)

private const val CARD_OFFSET = -110f
