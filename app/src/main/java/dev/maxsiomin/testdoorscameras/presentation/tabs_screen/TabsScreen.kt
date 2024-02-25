package dev.maxsiomin.testdoorscameras.presentation.tabs_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TabRow
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dev.maxsiomin.testdoorscameras.R
import dev.maxsiomin.testdoorscameras.presentation.camera_screen.CameraScreen
import dev.maxsiomin.testdoorscameras.presentation.camera_screen.CameraViewModel
import dev.maxsiomin.testdoorscameras.presentation.door_screen.DoorScreen
import dev.maxsiomin.testdoorscameras.presentation.door_screen.DoorViewModel
import dev.maxsiomin.testdoorscameras.ui.theme.SelectedTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun TabScreen() {

    val isPreview = LocalInspectionMode.current

    var tabIndex by remember { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    val tabs = listOf(R.string.cameras, R.string.doors).map { stringId ->
        stringResource(id = stringId)
    }

    val pagerState = rememberPagerState(pageCount = tabs.size)

    val viewModel = when {

        isPreview -> viewModel<FakePullRefresherViewModel>()

        tabIndex == 0 -> {
            hiltViewModel<CameraViewModel>()
        }

        tabIndex == 1 -> {
            hiltViewModel<DoorViewModel>()
        }

        else -> throw IllegalStateException("tabIndex")
    }

    val refreshState = rememberPullRefreshState(
        refreshing = viewModel.isLoading.value,
        onRefresh = { viewModel.refresh() }
    )

    Box {
        PullRefreshIndicator(viewModel.isLoading.value, refreshState, Modifier.align(Alignment.TopCenter))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.my_home),
                fontSize = 21.sp,
            )

            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = Color.Transparent,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier.pagerTabIndicatorOffset(pagerState, it),
                        color = SelectedTab,
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                fontSize = 17.sp
                            )
                        },
                        selected = tabIndex == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                            tabIndex = index
                        }
                    )
                }
            }

            if (isPreview) return@Column
            when (tabIndex) {

                0 -> {
                    viewModel as CameraViewModel
                    val cards by viewModel.cameras.collectAsStateWithLifecycle()
                    val revealedCardIds by viewModel.revealedCamerasIdsList.collectAsStateWithLifecycle()

                    CameraScreen(
                        cards = cards,
                        revealedCardIds = revealedCardIds,
                        onEvent = viewModel::onEvent
                    )
                }

                1 -> {
                    viewModel as DoorViewModel
                    val cards by viewModel.doors.collectAsStateWithLifecycle()
                    val revealedCardIds by viewModel.revealedDoorsIdsList.collectAsStateWithLifecycle()

                    DoorScreen(
                        cards = cards,
                        revealedCardIds = revealedCardIds,
                        onEvent = viewModel::onEvent
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun TabsPreview() {
    TabScreen()
}
