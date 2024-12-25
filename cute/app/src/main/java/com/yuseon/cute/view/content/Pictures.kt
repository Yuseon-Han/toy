import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.toy.cute.R
import com.yuseon.cute.ui.theme.Pink40
import com.yuseon.cute.ui.theme.White50
import com.yuseon.cute.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pictures(viewModel: MainViewModel = viewModel()) {
    val dataList = viewModel.urlList.collectAsState()
    if (dataList.value.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
    } else {

        val count: Int = viewModel.urlList.value?.size ?: 0

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxSize()
        ) {
            items(count) { item ->
                Banner(dataList.value!![item])
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PicturesForBookmark(viewModel: MainViewModel = viewModel()) {
    val dataList = viewModel.bookmarkList.collectAsState()
    if (dataList.value.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No bookmark yet\nAdd your favorite picture :)")
        }
    } else {

        val count: Int = viewModel.urlList.value?.size ?: 0

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxSize()
        ) {
            items(count) { item ->
                Banner(dataList.value!![item])
            }
        }
    }
}

@Composable
fun Banner(url: String, mainViewModel: MainViewModel = viewModel()) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .border(
                    width = 2.dp, // 테두리 두께
                    color = Pink40, // 테두리 색상
                    shape = RoundedCornerShape(16.dp) // 둥근 모서리
                )
                .padding(10.dp)
        ) {
            AsyncImage(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                model = url,
                contentDescription = "Banner image",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .padding(25.dp)
                .size(40.dp)
                .align(Alignment.BottomEnd)
                .background(
                    color = White, // 배경색
                    shape = RoundedCornerShape(5.dp) // 둥근 모서리
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_heart), // 이미지 리소스
                contentDescription = "Example Image", // 접근성 설명
                modifier = Modifier.size(100.dp) // 이미지 크기
            )
        }
    }
}
