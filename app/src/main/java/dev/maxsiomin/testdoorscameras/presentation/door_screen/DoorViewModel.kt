package dev.maxsiomin.testdoorscameras.presentation.door_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.testdoorscameras.domain.DoorModel
import dev.maxsiomin.testdoorscameras.domain.repository.Repository
import dev.maxsiomin.testdoorscameras.presentation.tabs_screen.PullRefresher
import dev.maxsiomin.testdoorscameras.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoorViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel(), PullRefresher {

    private var _isLoading = mutableStateOf(false)
    override var isLoading: State<Boolean> = _isLoading

    sealed class Event {
        data class Favorites(val cameraModel: DoorModel) : Event()
        data class ItemCollapsed(val cardId: Int) : Event()
        data class ItemExpanded(val cardId: Int) : Event()
        data class EditDoorName(val door: DoorModel, val newName: String) : Event()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.Favorites -> onFavorites(event.cameraModel)
            is Event.ItemCollapsed -> onItemCollapsed(event.cardId)
            is Event.ItemExpanded -> onItemExpanded(event.cardId)
            is Event.EditDoorName -> onEditDoorName(event.door, event.newName)
        }
    }

    private fun onFavorites(door: DoorModel) {
        val newDoor = door.copy(isFavorites = !door.isFavorites)
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateDoor(newDoor) {
                this@DoorViewModel.getDoors(fetchRemote = false)
            }
        }
    }

    private val _doors = MutableStateFlow(listOf<DoorModel>())
    val doors: StateFlow<List<DoorModel>> get() = _doors

    private val _revealedDoorsIdsList = MutableStateFlow(listOf<Int>())
    val revealedDoorsIdsList: StateFlow<List<Int>> get() = _revealedDoorsIdsList

    init {
        getDoors()
    }

    private fun getDoors(fetchRemote: Boolean = false) {
        viewModelScope.launch {
            repo.getDoors(fetchRemote).collect {
                when (it) {
                    is Resource.Success -> {
                        _doors.emit(it.data!!)
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> {
                        if (isLoading.value) {
                            _isLoading.value = false
                        }
                    }
                }
            }
        }
    }

    private fun onItemExpanded(cardId: Int) {
        if (_revealedDoorsIdsList.value.contains(cardId)) return
        _revealedDoorsIdsList.value = _revealedDoorsIdsList.value.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    private fun onItemCollapsed(cardId: Int) {
        if (!_revealedDoorsIdsList.value.contains(cardId)) return
        _revealedDoorsIdsList.value = _revealedDoorsIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
        }
    }

    override fun refresh() {
        getDoors(fetchRemote = true)
    }

    private fun onEditDoorName(door: DoorModel, newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateDoor(door.copy(name = newName)) {
                getDoors(fetchRemote = false)
            }
        }
    }

}