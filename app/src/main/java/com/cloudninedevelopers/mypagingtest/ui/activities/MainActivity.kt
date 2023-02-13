package com.cloudninedevelopers.mypagingtest.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.flatMap
import androidx.paging.map


import com.cloudninedevelopers.mypagingtest.paging.adapters.ArticlePagingAdapter
import com.cloudninedevelopers.mypagingtest.ui.viewmodel.MainViewModel
import com.cloudninedevelopers.mypagingtest.R
import com.cloudninedevelopers.mypagingtest.base.BaseActivity
import com.cloudninedevelopers.mypagingtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel : MainViewModel by viewModels()
    private lateinit var adapter: ArticlePagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpUi()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.pagingFlow.collectLatest {
                    adapter.submitData(it)
                }
            }

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

    }

    private fun setUpUi(){
        binding.apply {
            adapter = ArticlePagingAdapter()
            recyclerview.adapter = adapter
            recyclerview.setHasFixedSize(true)

        }

    }


}