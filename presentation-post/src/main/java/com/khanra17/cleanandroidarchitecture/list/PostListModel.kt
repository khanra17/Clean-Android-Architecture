package com.khanra17.cleanandroidarchitecture.list

import com.khanra17.cleanandroidarchitecture.domain.entity.Interaction

data class PostListModel(
    val headerText: String = "",
    val items: List<PostListItemModel> = listOf(),
    val interaction: Interaction
)

data class PostListItemModel(
    val id: Long,
    val userId: Long,
    val authorName: String,
    val title: String
)