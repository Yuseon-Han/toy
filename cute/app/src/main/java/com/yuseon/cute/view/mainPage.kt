package com.yuseon.cute.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yuseon.cute.ui.theme.Pink100
import com.yuseon.cute.ui.theme.Pink40
import com.yuseon.cute.ui.theme.Pink80
import com.yuseon.cute.view.content.Content
import com.yuseon.cute.viewmodel.MainViewModel


@Composable
fun MainPageTab(mainViewModel: MainViewModel = viewModel()) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles: List<String> = mainViewModel.getTabList()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Pink100
                )
            },
            divider = {
                Box(modifier = Modifier
                    .height(1.dp)
                    .background(color = Pink40))
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            title.uppercase(),
                            color = Pink100,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Content(selectedTabIndex)
        }
    }
}

