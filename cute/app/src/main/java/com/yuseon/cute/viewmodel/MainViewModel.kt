package com.yuseon.cute.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuseon.cute.model.FireBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val repoFB: FireBaseRepository = FireBaseRepository()
    private var _totalPage: Int = 0
    private var _currPage: Int = 0

    var liveTest  = MutableLiveData<String>()

    private val _urlList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val urlList: StateFlow<List<String>?> = _urlList.asStateFlow()

    private val _bookmarkList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val bookmarkList: StateFlow<List<String>?> = _bookmarkList.asStateFlow()


    init {
        repoFB.init()
        viewModelScope.launch(Dispatchers.IO) {
            repoFB.fetch {
                _totalPage = repoFB.getPageCount()
                _urlList.value = repoFB.getUrlList(_currPage)
            }
        }

        liveTest.value = "init"
    }

    fun loadMore() {
        if (_totalPage > _currPage) {
            _currPage++
            repoFB.getUrlList(_currPage)
        } else {
            repoFB.fetch {
                val newPageCount = repoFB.getPageCount()
                val retryLoadMore = newPageCount > _totalPage
                _totalPage = newPageCount
                if (retryLoadMore) {
                    loadMore()
                }
            }
        }
    }

    fun getTabList(): List<String> {
        return listOf("Cutie", "Bookmark")
    }
}