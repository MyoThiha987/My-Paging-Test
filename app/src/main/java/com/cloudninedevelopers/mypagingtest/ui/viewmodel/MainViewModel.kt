package com.cloudninedevelopers.mypagingtest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cloudninedevelopers.mypagingtest.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: MainRepository
) : ViewModel() {

    var pagingFlow = repository.fetchArticleList().cachedIn(viewModelScope)


}