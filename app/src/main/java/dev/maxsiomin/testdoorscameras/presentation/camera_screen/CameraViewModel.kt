package dev.maxsiomin.testdoorscameras.presentation.camera_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.maxsiomin.testdoorscameras.domain.CameraModel
import dev.maxsiomin.testdoorscameras.domain.repository.Repository
import dev.maxsiomin.testdoorscameras.presentation.tabs_screen.PullRefresher
import dev.maxsiomin.testdoorscameras.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel(), PullRefresher {

    private var _isLoading = mutableStateOf(false)
    override var isLoading: State<Boolean> = _isLoading

    sealed class Event {
        data class Favorites(val cameraModel: CameraModel) : Event()
        data class ItemCollapsed(val cardId: Int) : Event()
        data class ItemExpanded(val cardId: Int) : Event()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.Favorites -> onFavorites(event.cameraModel)
            is Event.ItemCollapsed -> onItemCollapsed(event.cardId)
            is Event.ItemExpanded -> onItemExpanded(event.cardId)
        }
    }

    private fun onFavorites(camera: CameraModel) {
        val newCamera = camera.copy(isFavorites = !camera.isFavorites)
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateCamera(newCamera) {
                this@CameraViewModel.getCameras(fetchRemote = false)
            }
        }
    }

    private val _cameras = MutableStateFlow(listOf<CameraModel>())
    val cameras: StateFlow<List<CameraModel>> get() = _cameras

    private val _revealedCamerasIdsList = MutableStateFlow(listOf<Int>())
    val revealedCamerasIdsList: StateFlow<List<Int>> get() = _revealedCamerasIdsList

    init {
        getCameras()
    }

    private fun getCameras(fetchRemote: Boolean = false) {
        viewModelScope.launch {
            repo.getCameras(fetchRemote).collect {
                when (it) {
                    is Resource.Success -> _cameras.emit(it.data!!)
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
        if (_revealedCamerasIdsList.value.contains(cardId)) return
        _revealedCamerasIdsList.value = _revealedCamerasIdsList.value.toMutableList().also { list ->
            list.add(cardId)
        }
    }

    private fun onItemCollapsed(cardId: Int) {
        if (!_revealedCamerasIdsList.value.contains(cardId)) return
        _revealedCamerasIdsList.value = _revealedCamerasIdsList.value.toMutableList().also { list ->
            list.remove(cardId)
        }
    }

    override fun refresh() {
        getCameras(fetchRemote = true)
    }
}
