package dev.maxsiomin.testdoorscameras.presentation.tabs_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PullRefresher {

    var isLoading: State<Boolean>

    fun refresh()

}

class FakePullRefresherViewModel : ViewModel(), PullRefresher {
    override var isLoading: State<Boolean> = mutableStateOf(true)

    override fun refresh() {

    }
}
