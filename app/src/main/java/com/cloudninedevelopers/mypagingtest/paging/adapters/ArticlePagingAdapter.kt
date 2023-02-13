package com.cloudninedevelopers.mypagingtest.paging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.cloudninedevelopers.mypagingtest.paging.viewholders.ArticlePagingViewHolder
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.base.BaseDiffUtil
import com.cloudninedevelopers.mypagingtest.databinding.LayoutItemArticlesBinding

class ArticlePagingAdapter :
    PagingDataAdapter<ArticleVO, ArticlePagingViewHolder>(
        BaseDiffUtil(
        areItemTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )) {
    override fun onBindViewHolder(holder: ArticlePagingViewHolder, position: Int) {
        val item = getItem(position)
        if(item !=null) holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlePagingViewHolder {
        val binding = LayoutItemArticlesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticlePagingViewHolder(binding = binding)
    }
}