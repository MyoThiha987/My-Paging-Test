package com.cloudninedevelopers.mypagingtest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E> : ViewModel() {
    //emit as an event
    private val _shareFlow = MutableSharedFlow<E>()
    val eventLD: LiveData<E> = _shareFlow.asLiveData()

    /**
     * emit one time events to the view
     * */
    protected fun emit(event: E) {

        if (shouldEmit(event)) {
            viewModelScope.launch {
                _shareFlow.emit(event)
            }
        }
    }

    /**
     * override this to skip a particular event type
     */
    protected open fun shouldEmit(event: E): Boolean {
        return true
    }
}