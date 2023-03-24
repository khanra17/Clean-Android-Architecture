package com.khanra17.cleanandroidarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khanra17.cleanandroidarchitecture.list.PostListScreen
import com.khanra17.cleanandroidarchitecture.presentaion_user.single.UserScreen
import com.khanra17.cleanandroidarchitecture.presentation_common.navigation.NavRoutes
import com.khanra17.cleanandroidarchitecture.single.PostScreen
import com.khanra17.cleanandroidarchitecture.ui.theme.CleanAndroidArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanAndroidArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Posts.route) {
        composable(route = NavRoutes.Posts.route) {
            PostListScreen(hiltViewModel(), navController)
        }
        composable(
            route = NavRoutes.Post.route, arguments = NavRoutes.Post.arguments
        ) {
            PostScreen(
                hiltViewModel(), NavRoutes.Post.fromEntry(it)
            )
        }
        composable(
            route = NavRoutes.User.route, arguments = NavRoutes.User.arguments
        ) {
            UserScreen(
                hiltViewModel(), NavRoutes.User.fromEntry(it)
            )
        }
    }
}