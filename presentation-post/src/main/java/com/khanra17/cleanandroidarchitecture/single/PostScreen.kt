package com.khanra17.cleanandroidarchitecture.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khanra17.cleanandroidarchitecture.presentation_common.navigation.PostInput
import com.khanra17.cleanandroidarchitecture.presentation_common.state.CommonScreen

@Composable
fun PostScreen(
    viewModel: PostViewModel, postInput: PostInput
) {
    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(result) { postModel ->
            Post(postModel)
        }
    }
    LaunchedEffect(postInput.postId) {
        viewModel.submitAction(PostUiAction.Load(postInput.postId))
    }
}

@Composable
fun Post(postModel: PostModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = postModel.title)
        Text(text = postModel.body)
    }
}