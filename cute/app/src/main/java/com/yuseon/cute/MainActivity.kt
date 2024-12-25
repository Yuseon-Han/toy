package com.yuseon.cute

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.whenStarted
import com.yuseon.cute.ui.theme.CuteTheme
import com.yuseon.cute.view.MainPageTab
import com.yuseon.cute.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val vm = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .systemBarsPadding()
                    .navigationBarsPadding()
            ) {
                CuteTheme {
                    MainPageTab()
                }
            }
        }
        test()
    }

    override fun onStart() {
        println("purin,, onStart")
        vm.liveTest.value = "onstart";
        super.onStart()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    private fun test() {
        vm.liveTest.observe(this

        ) { value ->
            println("purin,, live observe $value")
        }
    }
}

