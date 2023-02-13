package com.cloudninedevelopers.mypagingtest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }



}

abstract class BaseActivityWithState<VB : ViewBinding, VM : BaseViewModel<E>, E> :
    BaseActivity<VB>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getOrCreateViewModel().eventLD.observe(this, ::renderEvent)
    }

    abstract fun getOrCreateViewModel(): VM

    abstract fun renderEvent(event: E)
}