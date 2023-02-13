package com.cloudninedevelopers.mypagingtest.paging.viewholders

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cloudninedevelopers.mypagingtest.R
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.base.BaseViewHolder
import com.cloudninedevelopers.mypagingtest.databinding.LayoutItemArticlesBinding

class ArticlePagingViewHolder(private val binding: LayoutItemArticlesBinding) :
    BaseViewHolder<ArticleVO>(binding.root) {
    override fun bind(item: ArticleVO) {
        super.bind(item)
        binding.apply {
            binding.sourceName.text = item.user?.name
            binding.title.text = item.description
            Glide.with(itemView)
                .load(item.urls?.full)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .into(newsImage)
        }
    }

}