package com.yuseon.cute.view.content

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import Pictures
import PicturesForBookmark
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Content(selectedTab: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (selectedTab == 0) {
            Pictures()
        } else {
            PicturesForBookmark()
        }

    }
}